package com.srhomoeo.clinic.payloads;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.srhomoeo.clinic.entities.Category;
import com.srhomoeo.clinic.entities.Comment;
import com.srhomoeo.clinic.entities.User;

public class PostDto {

	private String title;
	private String imageName;
	private String content;
	private Date addedDate;
	private UserDto user;
	private  CategoryDto category;
	private List<CommentDto> comments = new ArrayList<>();
	public List<CommentDto> getComments() {
		return comments;
	}
	public void setComments(List<CommentDto> comments) {
		this.comments = comments;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getAddedDate() {
		return addedDate;
	}
	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public CategoryDto getCategory() {
		return category;
	}
	public void setCategory(CategoryDto category) {
		this.category = category;
	}
	public PostDto(String title, String imageName, String content, Date addedDate, UserDto user, CategoryDto category) {
		super();
		this.title = title;
		this.imageName = imageName;
		this.content = content;
		this.addedDate = addedDate;
		this.user = user;
		this.category = category;
	}
	public PostDto() {
		super();
	}
	
	
}
