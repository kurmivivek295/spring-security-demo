package com.vk.service.appuser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import com.vk.domain.appuser.AppUser;
import com.vk.domain.appuser.Role;
import com.vk.repository.appuser.AppUserRepo;
import com.vk.repository.role.RoleRepo;

@Service
public class AppUserDetailService implements UserDetailsService {
	
	@Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    public AppUser findUserByEmail(String email) {
        return appUserRepo.findByEmail(email);
    }

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		AppUser appUser = findUserByEmail(email);
		if(appUser != null) {
			List<GrantedAuthority> authorities = getUserAuthority(appUser.getRoles());
			return buildUserForAuthentication(appUser, authorities);
		} else  {
			throw new UsernameNotFoundException("User with email: " + email + "doesn't exists");
		}
	}
    
	private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
		Set<GrantedAuthority> roles = new HashSet<>();
		userRoles.forEach((role) -> {
			roles.add(new SimpleGrantedAuthority(role.getRole()));
		});

		List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
		return grantedAuthorities;
	}
	
	private UserDetails buildUserForAuthentication(AppUser appUser, List<GrantedAuthority> authorities) {
		return new User(appUser.getEmail(), appUser.getPassword(), authorities);
	}
	
    public void saveUser(AppUser appUser) {
        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        appUser.setEnabled(true);
        Role userRole = roleRepo.findByRole("ADMIN");
        appUser.setRoles(new HashSet<>(Arrays.asList(userRole)));
        appUserRepo.save(appUser);
    }
}
