package com.myshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myshop.model.Product;

public interface ProductDao extends JpaRepository<Product, Long>{
	
	
	
}
