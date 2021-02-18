package com.example.northwind.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.northwind.business.abstracts.ICategoryService;
import com.example.northwind.dataaccess.concretes.CategoryRepository;
import com.example.northwind.entities.concretes.Category;

@Service
public class CategoryManager implements ICategoryService{

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public Category getCategoryById(Integer id) throws Exception {
		return categoryRepository.findById(id).orElseThrow(() -> new Exception("No category with id " + id));
	}

	@Override
	public Category addCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public void deleteCategory(Category category) {
		categoryRepository.delete(category);
	}

	@Override
	public Category updateCategory(Integer id, Category category) {
		Category categoryToUpdate;
		try {
			categoryToUpdate = categoryRepository.findById(id).orElseThrow(() -> new Exception("No category with id " + id));
			categoryToUpdate.setCategoryName(category.getCategoryName());
			categoryToUpdate.setDescription(category.getDescription());
			return categoryRepository.save(categoryToUpdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Category> getAll() {
		return categoryRepository.findAll();
	}

}
