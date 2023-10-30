package com.eshop.oms.controllers;

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

import com.eshop.oms.domain.AddCustomerRequest;
import com.eshop.oms.domain.CustomerDTO;
import com.eshop.oms.domain.EditCustomerRequest;
import com.eshop.oms.services.CustomerService;

@RestController
@RequestMapping(path = "/customer/api/v1")
public class CustomerController {

	
	@Autowired
	CustomerService customerService;
	
	@PostMapping("")
	public ResponseEntity<CustomerDTO> addProduct(@RequestBody AddCustomerRequest customerRequest){
		CustomerDTO customerDTO = customerService.addCustomer(customerRequest);
		return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);
	}
	
	@PutMapping("")
	public ResponseEntity<CustomerDTO> editProduct(@RequestBody EditCustomerRequest customerRequest){
		CustomerDTO customerDTO = customerService.editCustomer(customerRequest);
		return new ResponseEntity<>(customerDTO, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> removeCustomer(@PathVariable String id){
		customerService.removeCustomer(id);
		return ResponseEntity.ok(HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomerDTO> getCustomerDetailsById(@PathVariable String id){
		CustomerDTO customerDTO = customerService.getCustomerDetailsById(id);
		return new ResponseEntity<>(customerDTO, HttpStatus.OK);
	}
}
