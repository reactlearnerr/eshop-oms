package com.eshop.oms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerDTO {

	private Integer customerId;
	
	private String customerName;
	
	private String email;
	
	private String shippingAddress;
}
