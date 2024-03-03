package com.srhomoeo.clinic.services.impl;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srhomoeo.clinic.entities.Comment;
import com.srhomoeo.clinic.entities.Post;
import com.srhomoeo.clinic.entities.User;
import com.srhomoeo.clinic.exceptions.ResourceNotFoundException;
import com.srhomoeo.clinic.payloads.CommentDto;
import com.srhomoeo.clinic.repositories.CommentRepo;
import com.srhomoeo.clinic.repositories.PostRepo;
import com.srhomoeo.clinic.repositories.UserRepo;
import com.srhomoeo.clinic.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId) {
		// TODO Auto-generated method stub
		
		Post post = this.postRepo.findById(postId).orElseThrow(
				()-> new ResourceNotFoundException("Post","post_id",postId)
				);
		
		User user = this.userRepo.findById(userId).orElseThrow(
				()-> new ResourceNotFoundException("User","user_id",userId));
		
		
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setCommentDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
		comment.setPost(post);
		comment.setUser(user);
		
		Comment saveComment = this.commentRepo.save(comment);
		CommentDto commentDto2 = this.modelMapper.map(saveComment, CommentDto.class);
		
		return commentDto2;
	}

	@Override
	public CommentDto updateComemnt(CommentDto commentDto,Integer commentId) {
		// TODO Auto-generated method stub
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(
				()-> new ResourceNotFoundException("comment","comment_id",commentId));
		comment.setCommentDate(commentDto.getCommentDate());
		comment.setContent(commentDto.getContent());
		this.commentRepo.save(comment);
		return this.modelMapper.map(comment, CommentDto.class);
	}

	@Override
	public void deletComment(Integer commentId) {
		// TODO Auto-generated method stub
		this.commentRepo.deleteById(commentId);
	}

	@Override
	public CommentDto getComment(Integer commentId) {
		// TODO Auto-generated method stub
		
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(
				()-> new ResourceNotFoundException("Comment","comment_id",commentId));
		
		return this.modelMapper.map(comment, CommentDto.class);
	}

}
