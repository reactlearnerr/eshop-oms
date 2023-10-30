package com.eshop.oms.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eshop.oms.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	public Optional<Product> findByPartNumber(String partNumber);
	public void deleteByPartNumber(String partNumber);
}
