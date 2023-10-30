package com.eshop.oms.services;

import java.util.List;

import com.eshop.oms.domain.EditOrderDetailRequest;
import com.eshop.oms.domain.OrderDetailsDTO;
import com.eshop.oms.domain.OrderRequest;

public interface OrderDetailsService {

	OrderDetailsDTO addOrderDetails(OrderRequest orderRequest);

	OrderDetailsDTO editOrderDetailsByOrderId(String orderId, EditOrderDetailRequest editOrderRequest);

	void removeOrderDetailsByOrderId(String orderId);

	OrderDetailsDTO getDetails(String orderId);

	List<OrderDetailsDTO> getDetailsByUserId(String userId);

	List<OrderDetailsDTO> getAllDetails();
}
