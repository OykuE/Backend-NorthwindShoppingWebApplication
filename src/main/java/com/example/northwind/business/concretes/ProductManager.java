package com.example.northwind.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.northwind.business.abstracts.IProductService;
import com.example.northwind.entities.concretes.Product;
import com.example.northwind.dataaccess.concretes.ProductRepository;

@Service
public class ProductManager implements IProductService{

	@Autowired
	ProductRepository productRepository;

	@Override
	public Product getProductById(Integer id) throws Exception {
		return productRepository.findById(id).orElseThrow(() -> new Exception("No product with id " + id));

	}

	@Override
	public Product addProduct(Product product) throws Exception { 
		if(product.getProductName().length()<=2) throw new Exception("Ürün ismi minimum iki karakter olmalı!");
		if(productRepository.countByCategoryId(product.getCategoryId()) <= 9) 
			return productRepository.save(product);
		return null;
	}

	@Override
	public void deleteProduct(Product product) {
		productRepository.delete(product);
	}

	@Override
	public Product updateProduct(Integer id, Product product) {
		Product productToUpdate;
		try {
			productToUpdate = productRepository.findById(id).orElseThrow(() -> new Exception("No Product with id " + id));
			productToUpdate.setProductName(product.getProductName());
			productToUpdate.setQuantityPerUnit(product.getQuantityPerUnit());
			productToUpdate.setUnitPrice(product.getUnitPrice());
			productToUpdate.setCategoryId(product.getCategoryId());
			return productRepository.save(productToUpdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Product> getAll() {
		return productRepository.findAll();
	}

}
