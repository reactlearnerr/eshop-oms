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
public class EditProductRequest {
	
	private String productName;
	private String description;
	private BigDecimal price;
	private Integer stock;
	private String partNumber;
}
