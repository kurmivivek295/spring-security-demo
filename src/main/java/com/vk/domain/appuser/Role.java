package com.vk.domain.appuser;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role {

	@Id
	private String id;
	private String role;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<AppUser> appUsers = new HashSet<>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Set<AppUser> getAppUsers() {
		return appUsers;
	}
	public void setAppUsers(Set<AppUser> appUsers) {
		this.appUsers = appUsers;
	}
	
}
