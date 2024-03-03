package com.srhomoeo.clinic.services;

import com.srhomoeo.clinic.payloads.CommentDto;

public interface CommentService {

	// create Comment
	CommentDto createComment(CommentDto comment, Integer postId, Integer userId);
	
	// update comment
	CommentDto updateComemnt(CommentDto comment, Integer commentId);
	
	// delete comment
	void deletComment(Integer commentId);
	
	// find comment
	CommentDto getComment(Integer commentId);
	
}
