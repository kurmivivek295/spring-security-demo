package com.vk.repository.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vk.domain.appuser.Role;

@Repository("roleRepo")
public interface RoleRepo extends JpaRepository<Role, String> {

	Role findByRole(String role);
	
}
