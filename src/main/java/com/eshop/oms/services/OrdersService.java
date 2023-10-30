package com.eshop.oms.services;

import java.util.List;

import com.eshop.oms.domain.OrderRequest;
import com.eshop.oms.domain.OrdersDTO;

public interface OrdersService {
	public OrdersDTO addOrder(OrderRequest orders);
	public void removeOrderByOrderId(String orderId);
	public OrdersDTO cancelOrderByOrderId(String orderId);
	public OrdersDTO getOrder(String orderId);
	public List<OrdersDTO> getOrdersByUserId(String userId);
	public List<OrdersDTO> getAllOrders();
	

}
