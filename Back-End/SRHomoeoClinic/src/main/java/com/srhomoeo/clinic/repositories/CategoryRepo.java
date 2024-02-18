package com.srhomoeo.clinic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srhomoeo.clinic.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
