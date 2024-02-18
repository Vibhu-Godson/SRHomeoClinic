package com.srhomoeo.clinic.services;

import java.util.List;

import com.srhomoeo.clinic.payloads.CategoryDto;

public interface CategoryService {
//	Create
	CategoryDto createCategory(CategoryDto categoryDto);
	
	// Update 
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	// Delete
	void deleteCategory(Integer categoryId);
	
	// get
	CategoryDto getCategory(Integer CategoryId);
	
	// Get All
	List<CategoryDto> getAllCategory();
}
