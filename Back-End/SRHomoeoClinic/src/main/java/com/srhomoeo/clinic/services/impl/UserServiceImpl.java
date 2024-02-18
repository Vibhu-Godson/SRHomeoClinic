package com.srhomoeo.clinic.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srhomoeo.clinic.entities.User;
import com.srhomoeo.clinic.payloads.UserDto;
import com.srhomoeo.clinic.repositories.UserRepo;
import com.srhomoeo.clinic.services.UserService;
import com.srhomoeo.clinic.exceptions.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = this.DtotoUser(userDto);
		User savedUser = this.userRepo.save(user);
		return this.UsertoDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		// TODO Auto-generated method stub
		User user  = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
//		user.set
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User userUpdated = this.userRepo.save(user);
		UserDto userDtoUpdated = this.UsertoDto(userUpdated); 
		return userDtoUpdated;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," Id ",userId));
		return this.UsertoDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> users = this.userRepo.findAll();
//		List<UserDto> userDtos = null;
//		for(int i=0;i<users.size();i++) {
//			userDtos.add(this.UsertoDto(users.get(i)));	
//		}
		
		List<UserDto> userDtos = users.stream().map(user->this.UsertoDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," Id ", userId));
		this.userRepo.delete(user);

	}

	public User DtotoUser(UserDto userDto) {
//		User user = new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		
		User user = this.modelMapper.map(userDto, User.class); 
		return user;
	}
	public UserDto UsertoDto(User user) {
	
		UserDto userDto = this.modelMapper.map(user,UserDto.class);
		return userDto;
	}
}
