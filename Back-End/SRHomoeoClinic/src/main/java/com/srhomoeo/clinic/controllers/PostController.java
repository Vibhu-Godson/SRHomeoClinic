package com.srhomoeo.clinic.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srhomoeo.clinic.entities.Post;
import com.srhomoeo.clinic.payloads.APIResponse;
import com.srhomoeo.clinic.payloads.PostDto;
import com.srhomoeo.clinic.payloads.PostResponse;
import com.srhomoeo.clinic.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;
	// create 
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable Integer userId, 
			@PathVariable Integer categoryId){
		PostDto savePostDto = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(savePostDto,HttpStatus.CREATED);
	}
	
	
	@PostMapping("/posts/update-post/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
		PostDto updatedPost = this.postService.updatePost(postDto, postId);
		return ResponseEntity.ok(updatedPost);
	}
	
	@DeleteMapping("/posts/delete-post/{postId}")
	public ResponseEntity<APIResponse> deletePost(@PathVariable Integer postId){
		this.postService.deletePost(postId);
		return ResponseEntity.ok(new APIResponse("Post Deleted Successfully",true));
	}
	
	@GetMapping("/posts/all-posts/")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value="pageNumber",defaultValue="0",required=false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue="10",required=false) Integer pageSize
			){
		
		PostResponse allPosts = this.postService.getAllPosts(pageNumber,pageSize);
		return ResponseEntity.ok(allPosts);
	}
	
	@GetMapping("/posts/all-post/page/{pageNumber}/size/{pageSize}")
	public ResponseEntity<List<PostDto>> getAllPostPage(@PathVariable Integer pageNumber,@PathVariable Integer pageSize){
		List<PostDto> allPostsPage = this.postService.getAllPostsPage(pageNumber, pageSize);
		return ResponseEntity.ok(allPostsPage);
	}
	
	@GetMapping("/post/post/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		PostDto postById = this.postService.getPostById(postId);
		return ResponseEntity.ok(postById);
	}
	
	@GetMapping("/post/post-by-category/category/{categoryId}")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
		List<PostDto> postsByCategory = this.postService.getPostsByCategory(categoryId);
		return  ResponseEntity.ok(postsByCategory);
	}
	
	@GetMapping("/post/post-by-user/user/{userId}")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
		List<PostDto> postByUser = this.postService.getPostByUser(userId);
		return ResponseEntity.ok(postByUser);
	}
}
