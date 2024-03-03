package com.srhomoeo.clinic.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srhomoeo.clinic.payloads.APIResponse;
import com.srhomoeo.clinic.payloads.CategoryDto;
import com.srhomoeo.clinic.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	// create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> CreateCategory(@RequestBody CategoryDto categoryDto){
		CategoryDto savedCategoryDto = this.categoryService.createCategory(categoryDto);
		return ResponseEntity.ok(savedCategoryDto);
	}
	
	// update
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> UpdateCategory(@RequestBody CategoryDto categoryDto, @PathVariable Integer categoryID){
		CategoryDto updatedCategoryDto = this.categoryService.updateCategory(categoryDto, categoryID);
		return ResponseEntity.ok(updatedCategoryDto);
	}

	// delete
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<APIResponse> DeleteCategory(@PathVariable Integer categoryId){
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity(new APIResponse("Category Deleted Successfully",true),HttpStatus.OK);
	}
	
	// Get
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> GetCategory(@PathVariable Integer categoryId){
		CategoryDto categoryDto = this.categoryService.getCategory(categoryId);
		return ResponseEntity.ok(categoryDto);
	}
	
	// GetAll
	@GetMapping("/all")
	public ResponseEntity<List<CategoryDto>> GetAllCategory(){
		List<CategoryDto> categoryDtos = this.categoryService.getAllCategory();
		return ResponseEntity.ok(categoryDtos);
	}
	
}
