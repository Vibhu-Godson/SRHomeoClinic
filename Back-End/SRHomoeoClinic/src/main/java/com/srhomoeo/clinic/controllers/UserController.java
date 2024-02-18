package com.srhomoeo.clinic.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srhomoeo.clinic.payloads.APIResponse;
import com.srhomoeo.clinic.payloads.UserDto;
import com.srhomoeo.clinic.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired	
	private UserService userService;
	

	//	POST -  Create User
	@PostMapping(value = "/",consumes = "application/json")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
		UserDto createdUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createdUserDto,HttpStatus.CREATED);
	}

	
	//	PUT - Update User 
	@PutMapping("/{userId}")  // Path URI variable -> {userId}  
	public ResponseEntity<UserDto> UpdateUser(@RequestBody UserDto userDto,@PathVariable("userId") Integer userId) {
		UserDto updateUser = this.userService.updateUser(userDto, userId);
		return ResponseEntity.ok(updateUser);
	}
	
	
	//	DELETE - delete User 
	@DeleteMapping("/{userId}")
	public ResponseEntity<APIResponse> DeleteUser(@RequestBody UserDto userDto, @PathVariable("userId") Integer userId){
		this.userService.deleteUser(userId);
		return new ResponseEntity(new APIResponse("User Deleted Successfully",true), HttpStatus.OK);
	}
	
	
	//	Get - user get
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable("userId") Integer userId){
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
}
