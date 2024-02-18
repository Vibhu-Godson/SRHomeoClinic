package com.srhomoeo.clinic.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srhomoeo.clinic.entities.Category;
import com.srhomoeo.clinic.entities.Post;
import com.srhomoeo.clinic.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
}
