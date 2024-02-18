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
	PostResponse getAllPosts(Integer pageNumber,Integer pageSize);
	
	//get All Post applying Paging
	List<PostDto> getAllPostsPage(Integer pageNumber,Integer pageSize);
	
	
	// get post by id
	PostDto getPostById(Integer postId);
	
	// get all Post By Category
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	// get All Post by User
	List<PostDto> getPostByUser(Integer userId);
	
	// Search Posts by Keywords 
	List<PostDto> searchPostByKeyword(String keyword);
}
