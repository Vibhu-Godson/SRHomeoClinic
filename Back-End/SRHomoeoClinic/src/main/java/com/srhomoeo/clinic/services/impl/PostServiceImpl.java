package com.srhomoeo.clinic.services.impl;



import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.srhomoeo.clinic.entities.Category;
import com.srhomoeo.clinic.entities.Post;
import com.srhomoeo.clinic.entities.User;
import com.srhomoeo.clinic.exceptions.ResourceNotFoundException;
import com.srhomoeo.clinic.payloads.PostDto;
import com.srhomoeo.clinic.payloads.PostResponse;
import com.srhomoeo.clinic.repositories.CategoryRepo;
import com.srhomoeo.clinic.repositories.PostRepo;
import com.srhomoeo.clinic.repositories.UserRepo;
import com.srhomoeo.clinic.services.PostService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired 
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","userId", userId));
		
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("category","categoryId",categoryId));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost = this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
		post.setTitle(postDto.getTitle());
		post.setAddedDate(post.getAddedDate());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		if(post.getCategory() != null) {
			Category category = this.categoryRepo.findById(postDto.getCategory().getCategoryId())
			.orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",1));
			post.setCategory(this.modelMapper.map(category, Category.class));
//			System.out.println("OK");
			
//			postDto.getCategory().getCategoryId();
		}
		
		Post savedPost = this.postRepo.save(post);
		return this.modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
		this.postRepo.deleteById(postId);
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy, boolean isAssending) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize,
				isAssending?Sort.by(sortBy):Sort.by(sortBy).descending()
						);
		Page<Post> pagePost = this.postRepo.findAll(pageRequest);
		PostResponse postResponse = new PostResponse();
		List<Post> posts = pagePost.getContent();
		List<PostDto> postsDto = posts.stream().map(
				(post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList()
						);
		postResponse.setPosts(postsDto);
		postResponse.setLastPage(pagePost.isLast());
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","PostId",postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostResponse getPostsByCategory(Integer categoryId, Integer pageNumber, Integer pageSize, String sortBy,
			boolean isAssending) {
		// TODO Auto-generated method stub
		
		
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize,
				isAssending?Sort.by(sortBy):Sort.by(sortBy).descending()
						);
		Page<Post> pagePost = this.postRepo.findByCategory(category, pageRequest);
		List<PostDto> postsDtoByCategory = pagePost.getContent().stream()
				.map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
//		PageRequest.
		postResponse.setPosts(postsDtoByCategory);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setLastPage(pagePost.isLast());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		return postResponse;
	}

	@Override
	public PostResponse getPostByUser(Integer userId,Integer pageNumber, Integer pageSize, String sortBy, boolean isAssending) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","UserId",userId));
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, 
				isAssending?Sort.by(sortBy):Sort.by(sortBy).descending()
						);
		Page<Post> pagePost = this.postRepo.findByUser(user,pageRequest);
		
		List<PostDto> postDtosByUser = pagePost.getContent().stream()
				.map((post)->this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setLastPage(pagePost.isLast());
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setPosts(postDtosByUser);
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		return postResponse;
	}

	@Override
	public PostResponse getPostByTitleContaining(String keyword, Integer pageNumber, Integer pageSize, String sortBy, boolean isAssending) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
		Page<Post> pagePost = this.postRepo.findPostByTitleContaining(keyword,pageRequest);
		List<PostDto> postDtosByKeyword = pagePost.getContent().stream()
				.map((post)->this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setLastPage(pagePost.isLast());
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setPosts(postDtosByKeyword);
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		return postResponse;
	}

	@Override
	public PostResponse getPostByContentContaining(String keyword, Integer pageNumber, Integer pageSize, String sortBy, boolean isAssending) {
		
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
		Page<Post> pagePost = this.postRepo.findPostByContentContaining(keyword,pageRequest);
		List<PostDto> postDtosByKeyword = pagePost.getContent().stream()
				.map((post)->this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setLastPage(pagePost.isLast());
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setPosts(postDtosByKeyword);
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		return postResponse;
	}
	@Override
	public List<PostDto> getAllPostsPage(Integer pageNumber,Integer pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = PageRequest.of(pageNumber,pageSize);
		Page<Post> postsInPage = this.postRepo.findAll(pageRequest);
		List<Post> posts = postsInPage.getContent();
		List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

}
