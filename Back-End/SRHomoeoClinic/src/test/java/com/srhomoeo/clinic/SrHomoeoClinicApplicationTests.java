package com.srhomoeo.clinic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.srhomoeo.clinic.repositories.UserRepo;

@SpringBootTest
class SrHomoeoClinicApplicationTests {

	@Autowired
	private UserRepo userRepo;
	@Test
	void contextLoads() {
	}
	
	@Test
	public void repoTest() {
//		String className = this.userRepo.getClass().getName();
//		String packageName = this.userRepo.getClass().getPackageName();
//		System.out.println(className);
//		System.out.println(packageName);
	}

}
