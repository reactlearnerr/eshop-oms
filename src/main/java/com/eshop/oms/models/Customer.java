package com.eshop.oms.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="customers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Customer {

	@Id
	@Column(name="customer_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	
	private String customerName;
	
	private String email;
	
	private String shippingAddress;
}
