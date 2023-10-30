package com.eshop.oms.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.oms.OMSConstants;
import com.eshop.oms.dao.OrderDetailsRepository;
import com.eshop.oms.dao.OrdersRepository;
import com.eshop.oms.domain.AddOrderDetailRequest;
import com.eshop.oms.domain.OrderDetailsDTO;
import com.eshop.oms.domain.OrderRequest;
import com.eshop.oms.domain.OrdersDTO;
import com.eshop.oms.models.OrderDetails;
import com.eshop.oms.models.Orders;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrdersServiceImpl implements OrdersService{
	
	@Autowired
	OrdersRepository ordersRepository;
	
	@Autowired
	OrderDetailsRepository orderDetailsRepository;

	@Override
	public OrdersDTO addOrder(OrderRequest orderRequest) {
		AddOrderDetailRequest addOrderDetailRequest = orderRequest.getAddOrderDetailRequest();
		OrderDetails savedOrderDetails = OrderDetails.builder()
				.orderPrice(addOrderDetailRequest.getOrderPrice())
				.productId(addOrderDetailRequest.getProductId())
				.status(OMSConstants.PENDING_STATUS)
				.qty(addOrderDetailRequest.getQty())
				.build();
		OrderDetails orderDetailSaved = orderDetailsRepository.saveAndFlush(savedOrderDetails);
		Set<OrderDetails> orderDetails = new HashSet<>();
		orderDetails.add(orderDetailSaved);
		
		Orders savedOrder = ordersRepository.saveAndFlush(Orders.builder()
				.customerId(orderRequest.getCustomerId())
				.orderDate(orderRequest.getOrderDate())
				.status(OMSConstants.PENDING_STATUS)
				.details(orderDetails)
				.totalAmount(orderRequest.getTotalAmount())
				.build());
		
		Set<OrderDetailsDTO> orderDetailSet = orderDetails.stream().map(orderDetail-> OrderDetailsDTO.builder()
				.productId(orderDetail.getProductId())
				.qty(orderDetail.getQty())
				.orderPrice(orderDetail.getOrderPrice())
				.status(orderDetail.getStatus())
				.orderDetailId(orderDetail.getOrderDetailId())
				.build()).collect(Collectors.toSet());
		
		return ordersToOrdersDTO(savedOrder, orderDetailSet);
	}

	@Override
	public OrdersDTO cancelOrderByOrderId(String orderId) {
		Optional<Orders> order = ordersRepository.findById(Integer.valueOf(orderId));
		if(order.isEmpty()) throw new EntityNotFoundException("Order not found in DB");
		Orders cancelledOrder = order.get();
		cancelledOrder.setStatus(OMSConstants.CANCELLED_STATUS);
		cancelledOrder = ordersRepository.saveAndFlush(cancelledOrder);
		
		Set<OrderDetails> details = cancelledOrder.getDetails().stream().map(oiDetail -> {
			oiDetail.setStatus(OMSConstants.CANCELLED_STATUS);
			return orderDetailsRepository.saveAndFlush(oiDetail);
		}).collect(Collectors.toSet());
		
		Set<OrderDetailsDTO> orderDetailSet = details.stream().map(orderDetail-> OrderDetailsDTO.builder()
				.productId(orderDetail.getProductId())
				.qty(orderDetail.getQty())
				.orderPrice(orderDetail.getOrderPrice())
				.orderId(Integer.valueOf(orderId))
				.status(orderDetail.getStatus())
				.orderDetailId(orderDetail.getOrderDetailId())
				.build()).collect(Collectors.toSet());

		return ordersToOrdersDTO(cancelledOrder, orderDetailSet);
	}

	@Override
	public void removeOrderByOrderId(String orderId) {
		Optional<Orders> order = ordersRepository.findById(Integer.valueOf(orderId));
		if(order.isEmpty()) throw new EntityNotFoundException("Order not found in DB");
		ordersRepository.delete(order.get());
	}

	@Override
	public OrdersDTO getOrder(String orderId) {
		Optional<Orders> order = ordersRepository.findById(Integer.valueOf(orderId));
		if(order.isEmpty()) throw new EntityNotFoundException("Order not found in DB");
		
		Set<OrderDetails> details = order.get().getDetails();

		Set<OrderDetailsDTO> orderDetailSet = details.stream().map(oDetail-> OrderDetailsDTO.builder()
				.productId(oDetail.getProductId())
				.qty(oDetail.getQty())
				.orderPrice(oDetail.getOrderPrice())
				.orderId(Integer.valueOf(orderId))
				.status(oDetail.getStatus())
				.orderDetailId(oDetail.getOrderDetailId())
				.build()).collect(Collectors.toSet());

		return OrdersDTO.builder()
				.orderDate(order.get().getOrderDate())
				.orderId(order.get().getOrderId())
				.orderDate(order.get().getOrderDate())
				.customerId(order.get().getCustomerId())
				.status(order.get().getStatus())
				.details(orderDetailSet)
				.totalAmount(order.get().getTotalAmount())
				.build();
	}

	@Override
	public List<OrdersDTO> getOrdersByUserId(String userId) {
		List<Orders> orders = ordersRepository.findOrdersByUserId(Integer.valueOf(userId));
		if(orders.isEmpty()) throw new EntityNotFoundException("Orders not found in DB for the userId "+userId);
		
		return orders.stream().map(order -> {
			Set<OrderDetailsDTO> orderDetailSet = order.getDetails().stream().map(oDetail-> OrderDetailsDTO.builder()
					.productId(oDetail.getProductId())
					.qty(oDetail.getQty())
					.orderPrice(oDetail.getOrderPrice())
					.status(oDetail.getStatus())
					.orderId(order.getOrderId())
					.orderDetailId(oDetail.getOrderDetailId())
					.build()).collect(Collectors.toSet());
			
			return ordersToOrdersDTO(order,orderDetailSet);
		}).toList();
	}

	@Override
	public List<OrdersDTO> getAllOrders() {
		List<Orders> orders = ordersRepository.findAll();
		if(orders.isEmpty()) throw new EntityNotFoundException("Orders not found in DB for the store");
		
		return orders.stream().map(order -> {
			Set<OrderDetailsDTO> orderDetailSet = order.getDetails().stream().map(oDetail-> OrderDetailsDTO.builder()
					.productId(oDetail.getProductId())
					.qty(oDetail.getQty())
					.orderId(order.getOrderId())
					.orderPrice(oDetail.getOrderPrice())
					.status(oDetail.getStatus())
					.orderDetailId(oDetail.getOrderDetailId())
					.build()).collect(Collectors.toSet());
			
			return ordersToOrdersDTO(order,orderDetailSet);
		}).toList();
		
	}
	
	private OrdersDTO ordersToOrdersDTO(Orders cancelledOrder, Set<OrderDetailsDTO> orderDetailSet) {
		return OrdersDTO.builder()
				.customerId(cancelledOrder.getCustomerId())
				.orderDate(cancelledOrder.getOrderDate())
				.orderId(cancelledOrder.getOrderId())
				.status(cancelledOrder.getStatus())
				.details(orderDetailSet)
				.totalAmount(cancelledOrder.getTotalAmount())
				.build();
	}
}
