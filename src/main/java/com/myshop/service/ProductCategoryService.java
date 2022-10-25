package com.myshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myshop.dao.ProductCategoryDao;
import com.myshop.model.ProductCategory;
import com.myshop.pojos.Message;

@Service
public class ProductCategoryService {

	@Autowired
	ProductCategoryDao dao;

	Message m = new Message();

	public ProductCategory findCategory(long id) {
		return dao.findById(id).get();
	}

	public List<ProductCategory> findAllCategories() {
		return dao.findAll();
	}

	public Message registerCategory( String category) {
		ProductCategory pc = new ProductCategory();
		pc.setCategoryName(category);
		try {
			dao.save(pc);
			m.setInfo("Product category added ");
		} catch(Exception ex) {
			m.setInfo("Error:  "+ex);
		}
		return m;
	}

	public Message updateCategory( long id, String catName) {
		ProductCategory pc = (ProductCategory) dao.findById(id).get();
		pc.setCategoryName(catName);
		try {
			dao.save(pc);
			m.setInfo("Product category updated ");
		} catch(Exception ex) {
			m.setInfo("Error:  "+ex);
		}
		return m;
	}

	public String deleteProductCategory(long id) {
		return deleteById(id);
	}
	private String deleteById(long id) {

		try {
			dao.deleteById(id);
			return "Category Deleted";

		} catch(Exception ex) {
			return "Error: "+ex;
		}
	}

}