package com.myshop.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myshop.model.Product;
import com.myshop.model.ProductCategory;
import com.myshop.pojos.ProductDetails;
import com.myshop.service.ProductCategoryService;
import com.myshop.service.ProductService;

@RestController 
@RequestMapping("/ecommerce") 
@CrossOrigin("http://localhost:4200")
public class MainController {

	@Autowired
	private ProductService pService;
	@Autowired
	private ProductCategoryService pcService; 

	@GetMapping("/products")
	public Iterable<Product> findAllProducts(){
		return pService.findAllProducts();
	} 

	@GetMapping("/products/{id}")
	public Product findProduct(@PathVariable long id){
		return pService.findProduct(id);
	} 
 
	@GetMapping("/products/params") 
	public @ResponseBody Product findProductWithParams(@RequestParam("Id") Optional<Integer> id) {
		return pService.findProduct(id.get());
	} 

	@PostMapping("/products")
	public void addProduct(@RequestBody ProductDetails pd) {
		pService.registerProduct(pd);
	} 

	@PutMapping("/products/{id}")
	public void updateProduct(ProductDetails pd, long id){
		pd.setId(id);
		pService.registerProduct(pd);
	}
	
	@DeleteMapping("/products/delete/{id}")
	public void deleteProduct(@PathVariable long id) {
		pService.deleteProduct(id);
	} 

	@GetMapping("/categories")
	public Iterable<ProductCategory> findAllCategories(){
		return pcService.findAllCategories();
	} 

	@GetMapping("/category/{id}")
	public ProductCategory findCategory(@PathVariable long id){
		return pcService.findCategory(id);
	} 

	@DeleteMapping("/category/{id}")
	public void deleteProductCategory(@PathVariable long id) {
		pcService.deleteProductCategory(id);
	} 
}