package com.myshop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myshop.model.Product;
 
 public interface ProductDao extends JpaRepository<Product, Long>{
       @Query(value = "SELECT * FROM product WHERE category_id = ?1", nativeQuery = true)
       List<Product> findProductsByProductCategoryId(int categoryId);
       
       @Query(value = "DELETE * FROM product WHERE category_id = ?1", nativeQuery = true)
       void deleteAllProductsByProductCategoryId(int categoryId);
       
       List<Product> findByDescriptionContaining(String desc);
        
       @Query(value = "SELECT p FROM product p WHERE p.description LIKE %:desc%", nativeQuery = true)
       List<Product> customFindByTitleContaining(@Param("desc") String desc);
        
        
 }
