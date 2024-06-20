package com.example.Order_Service.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Order_Service.Entity.OrderEntity;


@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

	Optional<OrderEntity> findByIdAndIsDeleted(Integer id, Boolean isDeleted);
	List<OrderEntity> findByUserIdAndIsDeleted(Integer userId, Boolean isDeleted);


	
	
}
