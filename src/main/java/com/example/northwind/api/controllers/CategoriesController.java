package com.example.northwind.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.northwind.business.abstracts.ICategoryService;
import com.example.northwind.entities.concretes.Category;


@RestController
@RequestMapping("/api/v1/categories")
public class CategoriesController {
	
		
		@Autowired
		ICategoryService categoryService;
		
		@GetMapping("/")
		public List<Category> getAll(){
			return categoryService.getAll();
		}
			
		@DeleteMapping("/{id}")
		public Map<String,Boolean> delete(@PathVariable(value="id")Integer categoryId) throws Exception{
			Category categoryDelete = categoryService.getCategoryById(categoryId);		
			categoryService.deleteCategory(categoryDelete);
			Map<String,Boolean> response = new HashMap<>();
			response.put("Silindi", Boolean.TRUE);
			return response;
		}
		
		@PostMapping("/new")
		public Category add(@RequestBody Category category) throws Exception{
			return categoryService.addCategory(category);	
		}
		
		@PutMapping("/{id}")
		public ResponseEntity<Category> update(@PathVariable(value="id") Integer categoryId, @RequestBody Category category){
			return ResponseEntity.ok(categoryService.updateCategory(categoryId, category));	
		}
		
	}


