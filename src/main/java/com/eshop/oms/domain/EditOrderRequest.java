package com.eshop.oms.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EditOrderRequest {

	private Integer customerId;
	
	private Timestamp orderDate;
	
	private BigDecimal totalAmount;
	
	private String status;
	
	private Set<OrderDetailsDTO> details;
	
}
