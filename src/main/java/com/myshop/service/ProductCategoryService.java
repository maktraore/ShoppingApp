package com.myshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myshop.dao.ProductCategoryDao;
import com.myshop.dao.ProductDao;
import com.myshop.model.ProductCategory;
import com.myshop.pojos.Message;

@Service
public class ProductCategoryService {

	@Autowired
	ProductCategoryDao pcdao;
	
	@Autowired
	ProductDao pdao;

	Message m = new Message();

	public ProductCategory findCategory(long id) {
		return pcdao.findById(id).get();
	}

	public List<ProductCategory> findAllCategories() {
		System.out.println("in pc service");
		return pcdao.findAll();
	}

	public Message registerCategory( String category) {
		ProductCategory pc = new ProductCategory();
		pc.setCategoryName(category);
		try {
			pcdao.save(pc);
			m.setInfo("Product category added ");
		} catch(Exception ex) {
			m.setInfo("Error:  "+ex);
		}
		return m;
	}

	public Message updateCategory( long id, String catName) {
		ProductCategory pc = pcdao.findById(id).orElse(new ProductCategory());
		pc.setCategoryName(catName);
		try {
			pcdao.save(pc);
			m.setInfo("Product category updated ");
		} catch(Exception ex) {
			m.setInfo("Error:  "+ex);
		}
		return m;
	}

	public String deleteProductCategory(long id) {
		 pdao.deleteAllProductsByProductCategoryId(id);
		 return deleteById(id);
	}
	private String deleteById(long id) {

		try {
			pcdao.deleteById(id);
			return "Category Deleted";

		} catch(Exception ex) {
			return "Error: "+ex;
		}
	}

}