package com.example.User_Service.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.User_Service.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	public Optional<User> findByIdAndIsDeleted(Integer id, Boolean isDeleted);
}
