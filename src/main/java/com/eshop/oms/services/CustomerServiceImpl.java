package com.eshop.oms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.oms.dao.CustomerRepository;
import com.eshop.oms.domain.AddCustomerRequest;
import com.eshop.oms.domain.CustomerDTO;
import com.eshop.oms.domain.EditCustomerRequest;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public CustomerDTO addCustomer(AddCustomerRequest customerRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerDTO editCustomer(EditCustomerRequest customerRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeCustomer(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CustomerDTO getCustomerDetailsById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
