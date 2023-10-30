package com.eshop.oms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eshop.oms.models.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

	@Query(name = "findOrdersByUserId", value="select o from Orders o where o.customerId = :customerId")
	List<Orders> findOrdersByUserId(Integer customerId);

}
