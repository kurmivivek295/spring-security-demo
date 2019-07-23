package com.vk.controller.appuser;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vk.config.jwt.JwtTokenProvider;
import com.vk.domain.appuser.AppUser;
import com.vk.dto.appuser.AppUserDto;
import com.vk.repository.appuser.AppUserRepo;
import com.vk.service.appuser.AppUserDetailService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AppUserController {
	
	@Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    AppUserRepo appUserRepo;

    @Autowired
    private AppUserDetailService appUserDetailService;
    
    @GetMapping("/appuser")
    public AppUser dummy() {
    	return new AppUser();
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AppUserDto appUserDto) {
        try {
            String username = appUserDto.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, appUserDto.getPassword()));
            String token = jwtTokenProvider.createToken(username, this.appUserRepo.findByEmail(username).getRoles());
            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email/password supplied");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AppUser appUser) {
        AppUser userExists = appUserDetailService.findUserByEmail(appUser.getEmail());
        if (userExists != null) {
            throw new BadCredentialsException("User with username: " + appUser.getEmail() + " already exists");
        }
        appUserDetailService.saveUser(appUser);
        Map<Object, Object> model = new HashMap<>();
        model.put("message", "User registered successfully");
        return ResponseEntity.ok(model);
    }
}
