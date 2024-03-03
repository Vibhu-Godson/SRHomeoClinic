package com.srhomoeo.clinic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srhomoeo.clinic.payloads.APIResponse;
import com.srhomoeo.clinic.payloads.CommentDto;
import com.srhomoeo.clinic.services.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@GetMapping("/{commentId}")
	ResponseEntity<CommentDto> getComment(@PathVariable(value="commentId") Integer commentId){
		
		CommentDto commentDto = this.commentService.getComment(commentId);
		return ResponseEntity.ok(commentDto);
	}
	
	@PostMapping("/post/{postId}/user/{userId}")
	ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,
			@PathVariable(value="postId") Integer postId,
			@PathVariable(value="userId") Integer userId
			){
		CommentDto comment = this.commentService.createComment(commentDto,postId,userId);
		return ResponseEntity.ok(comment);
	}
	
	@PostMapping("/{commentId}")
	ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto, @PathVariable(value="commentId") Integer commentId ){
		CommentDto updateComemnt = this.commentService.updateComemnt(commentDto, commentId);
		return ResponseEntity.ok(updateComemnt);
	}
	
	@DeleteMapping("/{commentId}")
	ResponseEntity<APIResponse> deleteComment(@PathVariable(value="commentId") Integer commentId){
		this.commentService.deletComment(commentId);
		return ResponseEntity.ok(new APIResponse("Comment deleted Successfully", true));
	}
}
