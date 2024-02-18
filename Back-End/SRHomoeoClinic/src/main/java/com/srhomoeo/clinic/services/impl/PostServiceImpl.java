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
import org.springframework.stereotype.Service;

import com.srhomoeo.clinic.entities.Category;
import com.srhomoeo.clinic.entities.Post;
import com.srhomoeo.clinic.entities.User;
import com.srhomoeo.clinic.exceptions.ResourceNotFoundException;
import com.srhomoeo.clinic.payloads.PostDto;
import com.srhomoeo.clinic.payloads.PostResponse;
import com.srhomoeo.clinic.payloads.UserDto;
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
		
		if(post.getCategory() != null) {
			Category category = this.categoryRepo.findById(postDto.getCategory().getCategoryId())
			.orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",1));
			post.setCategory(this.modelMapper.map(category, Category.class));
			System.out.println("OK");
			
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
	public PostResponse getAllPosts(Integer pageNumber,Integer pageSize) {
		// TODO Auto-generated method stub
		
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
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
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
		List<Post> postByCategory = this.postRepo.findByCategory(category);
		List<PostDto> postsDtoByCategory = postByCategory.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postsDtoByCategory;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","UserId",userId));
		List<Post> postsByUser = this.postRepo.findByUser(user);
		List<PostDto> postDtosByUser = postsByUser.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtosByUser;
	}

	@Override
	public List<PostDto> searchPostByKeyword(String keyword) {
		// TODO Auto-generated method stub
//		this.postRepo.findByKeyWord(keyword);
		return null;
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
