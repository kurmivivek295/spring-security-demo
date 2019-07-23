package com.vk.repository.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vk.domain.appuser.AppUser;

@Repository("appUserRepo")
public interface AppUserRepo extends JpaRepository<AppUser, String> {

	AppUser findByEmail(String email);
	
}
