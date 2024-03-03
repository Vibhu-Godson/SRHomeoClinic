package com.srhomoeo.clinic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srhomoeo.clinic.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{
	

}
