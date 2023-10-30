package com.eshop.oms.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderRequest {

	private Integer customerId;
	
	private Timestamp orderDate;
	
	private BigDecimal totalAmount;
	
	private AddOrderDetailRequest addOrderDetailRequest;
	
}
