package com.srhomoeo.clinic.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.srhomoeo.clinic.entities.Category;
import com.srhomoeo.clinic.entities.Post;
import com.srhomoeo.clinic.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
	Page<Post> findByUser(User user, PageRequest pageRequest);
	
	Page<Post> findByCategory(Category category,PageRequest pageRequest);
	
//	Page<Post>findPostByTitleContaining(String keyword);
	@Query("SELECT p FROM Post p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    Page<Post> findPostByTitleContaining(@Param("title") String title, PageRequest pageRequest);
	
	@Query("SELECT p FROM Post p WHERE LOWER(p.content) LIKE LOWER(CONCAT('%', :content, '%'))")
    Page<Post> findPostByContentContaining(@Param("content") String content, PageRequest pageRequest);
	
}
