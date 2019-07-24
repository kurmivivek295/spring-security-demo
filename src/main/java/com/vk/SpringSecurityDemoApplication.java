package com.vk;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.vk.domain.appuser.Role;
import com.vk.repository.role.RoleRepo;

@SpringBootApplication
public class SpringSecurityDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityDemoApplication.class, args);
	}

	@Bean
	public ApplicationRunner loadData(RoleRepo roleRepo) {
		return args -> {
			Role role = new Role();
			role.setId("101");
			role.setRole("ADMIN");
			roleRepo.save(role);
		};
	}
	
}
