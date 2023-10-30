package com.eshop.oms.services;

import com.eshop.oms.domain.AddCustomerRequest;
import com.eshop.oms.domain.CustomerDTO;
import com.eshop.oms.domain.EditCustomerRequest;

public interface CustomerService {

	CustomerDTO addCustomer(AddCustomerRequest customerRequest);

	CustomerDTO editCustomer(EditCustomerRequest customerRequest);

	void removeCustomer(String id);

	CustomerDTO getCustomerDetailsById(String id);

}
