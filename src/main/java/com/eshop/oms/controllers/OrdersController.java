package com.eshop.oms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshop.oms.domain.OrderRequest;
import com.eshop.oms.domain.OrdersDTO;
import com.eshop.oms.services.OrdersService;

@RestController
@RequestMapping(path = "/orders/api/v1")
public class OrdersController {
	@Autowired
	OrdersService ordersService;
	
	@PostMapping("")
	public ResponseEntity<OrdersDTO> placeOrder(@RequestBody OrderRequest orderRequest){
		OrdersDTO ordersDTO = ordersService.addOrder(orderRequest);
		return new ResponseEntity<>(ordersDTO, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{orderId}")
	public ResponseEntity<HttpStatus> removeOrder(@PathVariable String orderId){
		ordersService.removeOrderByOrderId(orderId);
		return ResponseEntity.ok(HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/cancel/{orderId}")
	public ResponseEntity<OrdersDTO> cancelOrderByOrderId(@PathVariable String orderId){
		OrdersDTO orderDTO = ordersService.cancelOrderByOrderId(orderId);
		return new ResponseEntity<>(orderDTO, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{orderId}")
	public ResponseEntity<OrdersDTO> getOrder(@PathVariable String orderId){
		OrdersDTO ordersDTOO = ordersService.getOrder(orderId);
		return new ResponseEntity<>(ordersDTOO, HttpStatus.OK);
	}
	
	@GetMapping("/userId/{userId}")
	public ResponseEntity<List<OrdersDTO>> getOrdersByUserId(@PathVariable String userId){
		List<OrdersDTO> orders = ordersService.getOrdersByUserId(userId);
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
	
	@GetMapping("")
	public ResponseEntity<List<OrdersDTO>> getAllOrders(){
		List<OrdersDTO> orders = ordersService.getAllOrders();
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
}
