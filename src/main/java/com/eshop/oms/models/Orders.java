package com.eshop.oms.models;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	
	private Integer customerId;
	
	private Timestamp orderDate;
	
	private BigDecimal totalAmount;
	
	private String status;
	
	 @OneToMany(cascade=CascadeType.ALL)
	 @JoinColumn(name = "orderId")
	 private Set<OrderDetails> details;
	
}
