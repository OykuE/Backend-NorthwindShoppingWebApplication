package com.example.northwind.business.abstracts;

import java.util.List;

import com.example.northwind.entities.concretes.Product;

public interface IProductService {
	
	List<Product> getAll();
	Product getProductById(Integer id) throws Exception;
	Product addProduct(Product product) throws Exception;
	void deleteProduct(Product product);
	Product updateProduct(Integer id, Product product);
}
