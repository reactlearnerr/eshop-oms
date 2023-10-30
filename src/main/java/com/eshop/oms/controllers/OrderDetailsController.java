package com.eshop.oms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshop.oms.domain.EditOrderDetailRequest;
import com.eshop.oms.domain.OrderDetailsDTO;
import com.eshop.oms.domain.OrderRequest;
import com.eshop.oms.services.OrderDetailsService;

@RestController
@RequestMapping(path = "/orderdetails/api/v1")
public class OrderDetailsController {

	@Autowired
	OrderDetailsService orderDetailService;
	
	@PostMapping("")
	public ResponseEntity<OrderDetailsDTO> placeOrder(@RequestBody OrderRequest orderRequest){
		OrderDetailsDTO orderDetailsDTO = orderDetailService.addOrderDetails(orderRequest);
		return new ResponseEntity<>(orderDetailsDTO, HttpStatus.CREATED);
	}
	
	@PutMapping("/{orderId}")
	public ResponseEntity<OrderDetailsDTO> editOrder(@PathVariable String orderId, @RequestBody EditOrderDetailRequest editOrderRequest){
		OrderDetailsDTO orderDTO = orderDetailService.editOrderDetailsByOrderId(orderId, editOrderRequest);
		return new ResponseEntity<>(orderDTO, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{orderId}")
	public ResponseEntity<HttpStatus> removeOrder(@PathVariable String orderId){
		orderDetailService.removeOrderDetailsByOrderId(orderId);
		return ResponseEntity.ok(HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/{orderId}")
	public ResponseEntity<OrderDetailsDTO> getOrder(@PathVariable String orderId){
		OrderDetailsDTO orderDetailsDTOO = orderDetailService.getDetails(orderId);
		return new ResponseEntity<>(orderDetailsDTOO, HttpStatus.OK);
	}
	
	@GetMapping("/userId/{userId}")
	public ResponseEntity<List<OrderDetailsDTO>> getOrdersByUserId(@PathVariable String userId){
		List<OrderDetailsDTO> orderDetails = orderDetailService.getDetailsByUserId(userId);
		return new ResponseEntity<>(orderDetails, HttpStatus.OK);
	}
	
	@GetMapping("")
	public ResponseEntity<List<OrderDetailsDTO>> getAllOrders(){
		List<OrderDetailsDTO> orderDetails = orderDetailService.getAllDetails();
		return new ResponseEntity<>(orderDetails, HttpStatus.OK);
	}

}
