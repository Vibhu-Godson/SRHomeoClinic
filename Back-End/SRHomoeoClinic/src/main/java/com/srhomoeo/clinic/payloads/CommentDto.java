package com.srhomoeo.clinic.payloads;

import java.sql.Date;

import com.srhomoeo.clinic.entities.Post;
import com.srhomoeo.clinic.entities.User;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class CommentDto {

	private Integer id;
	
	private String content;

	private Date commentDate;

//	private PostDto post;
//
    private UserDto user;

    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}
    
    
    
}
