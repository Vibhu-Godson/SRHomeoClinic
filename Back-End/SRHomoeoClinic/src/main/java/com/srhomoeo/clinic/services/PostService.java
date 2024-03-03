package com.srhomoeo.clinic.services;

import java.util.List;

import com.srhomoeo.clinic.entities.Post;
import com.srhomoeo.clinic.payloads.PostDto;
import com.srhomoeo.clinic.payloads.PostResponse;

public interface PostService {

	// create
	PostDto createPost(PostDto postDto,Integer userId, Integer categoryId);
	
	// update 
	PostDto updatePost(PostDto postDto, Integer postId);
	
	// delete
	void deletePost(Integer postId);
	
	// get All Posts 
	PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy,boolean isAssending);
	
	//get All Post applying Paging
	List<PostDto> getAllPostsPage(Integer pageNumber,Integer pageSize);
	
	
	// get post by id
	PostDto getPostById(Integer postId);
	
	// get all Post By Category
	PostResponse getPostsByCategory(Integer categoryId, Integer pageNumber, Integer pageSize, String sortBy, boolean isAssending);
	
	// get All Post by User
	PostResponse getPostByUser(Integer userId, Integer pageNumber, Integer pageSize, String sortBy, boolean isAssending);
	
	// Search Posts by title 
	PostResponse getPostByTitleContaining(String keyword, Integer pageNumber, Integer pageSize, String sortBy, boolean isAssending);
	
	// Search Post by content
	PostResponse getPostByContentContaining(String keyword, Integer pageNumber, Integer pageSize, String sortBy, boolean isAssending);
	
}
