package com.srhomoeo.clinic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srhomoeo.clinic.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
