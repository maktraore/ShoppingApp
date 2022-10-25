package com.myshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myshop.dao.ProductCategoryDao;
import com.myshop.dao.ProductDao;
import com.myshop.model.Product;
import com.myshop.model.ProductCategory;
import com.myshop.pojos.Message;
import com.myshop.pojos.ProductDetails;

@Service
public class ProductService {

	@Autowired
	ProductDao pdao;        
	@Autowired
	ProductCategoryDao pcdao;
	Message m = new Message();

	public List<Product> findAllProducts() {
		return pdao.findAll();
	}
	public Product findProduct(long id) {
		return pdao.findById(id).get();
	}

	public List<Product> findProductsByProductCategoryId(int categoryId){
		return pdao.findProductsByProductCategoryId(categoryId);
	}

	public List<Product> findByTitleContaining(String desc){
		return pdao.findByDescriptionContaining(desc);
	}

	public List<Product> customFindByTitleContaining(String desc){
		return pdao.customFindByTitleContaining(desc);
	}

	public Message deleteProduct(long id) {
		return deleteProductById(id);
	}

	private Message deleteProductById(long id) {
		try {
			pdao.deleteById(id);
			m.setInfo("Product Deleted");
		}catch(Exception ex) {
			m.setInfo("Error: "+ex);
		}
		return m;
		               
	}

	public void deleteAllProductsByProductCategoryId(int categoryId){
		try {
			pdao.deleteAllProductsByProductCategoryId(categoryId);
		} catch(Exception ex) {
			m.setInfo("Error: "+ex);
		}
	}


	public Message registerProduct( ProductDetails pd) {
		Product p= new Product();
		ProductCategory pc = new ProductCategory();
		if(pd != null) {
			p.setId(pd.getId());
			p.setDateCreated(pd.getDateCreated());
			p.setDescription(pd.getDescription());
			p.setImageUrl(pd.getImageUrl());
			p.setLastUpdated(pd.getLastUpdated());
			p.setSku(pd.getSku());
			p.setName(pd.getName());
			p.setUnitPrice(pd.getUnitPrice());
			p.setUnitsInStock(pd.getUnitsInStock());
			p.setActive(pd.isActive());
			pc.setCategoryName(pd.getCategoryName());
			pc.setId(pd.getCategoryId());
			p.setCategory(pc);
			return addProduct(p);
		}else {
			m.setInfo("Product Info added");
		}
		return m;
	}

	private Message addProduct(Product p) {
		System.out.println("Saving product and category");
		try {
			pcdao.save(p.getCategory());            
			pdao.save(p);
			m.setInfo("Product Info added");
		} catch(Exception ex) {
			System.out.println(ex);
			System.out.println(p.toString());
			m.setInfo("Error:  "+ex);
		}
		return m;
	}

}
