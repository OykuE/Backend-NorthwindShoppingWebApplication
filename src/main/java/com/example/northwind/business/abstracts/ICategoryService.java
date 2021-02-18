package com.example.northwind.business.abstracts;

import java.util.List;

import com.example.northwind.entities.concretes.Category;


public interface ICategoryService {
		List<Category> getAll();
		Category getCategoryById(Integer id) throws Exception;
		Category addCategory(Category category);
		void deleteCategory(Category category);
		Category updateCategory(Integer id, Category category);

}
