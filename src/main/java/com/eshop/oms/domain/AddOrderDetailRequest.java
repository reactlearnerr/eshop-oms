package com.eshop.oms.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AddOrderDetailRequest {

	private Integer productId;
	
	private Integer qty;
	
	private BigDecimal orderPrice;
}
