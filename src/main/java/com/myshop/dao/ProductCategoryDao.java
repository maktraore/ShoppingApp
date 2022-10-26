package com.myshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myshop.model.ProductCategory;

public interface ProductCategoryDao extends JpaRepository<ProductCategory, Long> {
	

}
