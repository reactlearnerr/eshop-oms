package com.eshop.oms.models;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderDetailId;
	
	private Integer productId;
	
	private Integer qty;
	
	private BigDecimal orderPrice;
	
	private String status;
}
