package com.eshop.oms.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class ProductDTO {
	
	private Integer productId;
	private String productName;
	private String description;
	private BigDecimal price;
	private Integer stock;
	private String partNumber;
}
