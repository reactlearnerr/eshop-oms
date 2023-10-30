package com.eshop.oms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.oms.dao.OrderDetailsRepository;
import com.eshop.oms.domain.EditOrderDetailRequest;
import com.eshop.oms.domain.OrderDetailsDTO;
import com.eshop.oms.domain.OrderRequest;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService{

	@Autowired
	OrderDetailsRepository orderDetailsRepository;

	@Override
	public OrderDetailsDTO addOrderDetails(OrderRequest orderRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDetailsDTO editOrderDetailsByOrderId(String orderId, EditOrderDetailRequest editOrderRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeOrderDetailsByOrderId(String orderId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public OrderDetailsDTO getDetails(String orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDetailsDTO> getDetailsByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDetailsDTO> getAllDetails() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
