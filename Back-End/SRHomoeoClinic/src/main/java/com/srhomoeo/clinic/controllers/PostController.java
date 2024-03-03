package com.srhomoeo.clinic.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.srhomoeo.clinic.config.AppConstents;
import com.srhomoeo.clinic.entities.Post;
import com.srhomoeo.clinic.payloads.APIResponse;
import com.srhomoeo.clinic.payloads.PostDto;
import com.srhomoeo.clinic.payloads.PostResponse;
import com.srhomoeo.clinic.services.FileService;
import com.srhomoeo.clinic.services.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
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
			@RequestParam(value="pageNumber",defaultValue=AppConstents.PAGE_NUMBER,required=false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue=AppConstents.PAGE_SIZE,required=false) Integer pageSize,
			@RequestParam(value="sortBy",defaultValue=AppConstents.SORT_BY,required=false) String sortBy,
			@RequestParam(value="isAssending",defaultValue=AppConstents.IS_ASSENDING,required=false) boolean isAssending
			){
//		String sortBy="Id";
		PostResponse allPosts = this.postService.getAllPosts(pageNumber,pageSize,sortBy,isAssending);
		return ResponseEntity.ok(allPosts);
	}
	
	
	@GetMapping("/post/post/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		PostDto postById = this.postService.getPostById(postId);
		return ResponseEntity.ok(postById);
	}
	
	@GetMapping("/post/post-by-category/category/{categoryId}")
	public ResponseEntity<PostResponse> getPostByCategory(@PathVariable Integer categoryId,
			@RequestParam(value="pageNumber",defaultValue=AppConstents.PAGE_NUMBER,required=false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue=AppConstents.PAGE_SIZE,required=false) Integer pageSize,
			@RequestParam(value="sortBy",defaultValue=AppConstents.SORT_BY,required=false) String sortBy,
			@RequestParam(value="isAssending",defaultValue=AppConstents.IS_ASSENDING,required=false) boolean isAssending
	){
		PostResponse postResponse = this.postService.getPostsByCategory(categoryId,pageNumber,pageSize,sortBy,isAssending);
		return  ResponseEntity.ok(postResponse);
//		return null;
	}
	
	@GetMapping("/post/post-by-user/user/{userId}")
	public ResponseEntity<PostResponse> getPostByUser(@PathVariable Integer userId,
			@RequestParam(value="pageNumber",defaultValue=AppConstents.PAGE_NUMBER,required=false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue=AppConstents.PAGE_SIZE,required=false) Integer pageSize,
			@RequestParam(value="sortBy",defaultValue=AppConstents.SORT_BY,required=false) String sortBy,
			@RequestParam(value="isAssending",defaultValue=AppConstents.IS_ASSENDING,required=false) boolean isAssending
			){
		PostResponse postByUser = this.postService.getPostByUser(userId,pageNumber,pageSize,sortBy,isAssending);
		return ResponseEntity.ok(postByUser);
	}
	
	@GetMapping("/post/post-by-title-having-{keyword}")
	public ResponseEntity<PostResponse> getPostByTitleContaining(@PathVariable String keyword,
			@RequestParam(value="pageNumber",defaultValue=AppConstents.PAGE_NUMBER,required=false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue=AppConstents.PAGE_SIZE,required=false) Integer pageSize,
			@RequestParam(value="sortBy",defaultValue=AppConstents.SORT_BY,required=false) String sortBy,
			@RequestParam(value="isAssending",defaultValue=AppConstents.IS_ASSENDING,required=false) boolean isAssending
			){
		PostResponse searchPostByKeyword = this.postService.getPostByTitleContaining(keyword, pageNumber, pageSize, sortBy, isAssending);
		return ResponseEntity.ok(searchPostByKeyword);
	}
	
	@GetMapping("/post/post-by-content-having-{keyword}")
	public ResponseEntity<PostResponse> getPostByContentContaining(@PathVariable String keyword,
			@RequestParam(value="pageNumber",defaultValue=AppConstents.PAGE_NUMBER,required=false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue=AppConstents.PAGE_SIZE,required=false) Integer pageSize,
			@RequestParam(value="sortBy",defaultValue=AppConstents.SORT_BY,required=false) String sortBy,
			@RequestParam(value="isAssending",defaultValue=AppConstents.IS_ASSENDING,required=false) boolean isAssending
			){
		PostResponse postResponse = this.postService.getPostByContentContaining(keyword, pageNumber, pageSize, sortBy, isAssending);
		return ResponseEntity.ok(postResponse);
	}
	
	
//	post image upload
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@PathVariable Integer postId,
			@RequestParam("image") MultipartFile image
			) throws IOException{
		
		PostDto postDto = this.postService.getPostById(postId);
		String fileName = this.fileService.uploadImage(path, image);
		postDto.setImageName(fileName);
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		
		return ResponseEntity.ok(updatePost);
	}
	
	// method to serve image file
	@GetMapping(value="/post/image/{imageName}", produces= MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName,
			HttpServletResponse response) throws IOException{
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
}
