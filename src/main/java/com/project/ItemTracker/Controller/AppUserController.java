package com.project.ItemTracker.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.AuthorizeRequestsDsl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.ItemTracker.Model.AppUser;
import com.project.ItemTracker.Service.AppUserService;
import com.project.ItemTracker.Service.JwtService;

@RestController
public class AppUserController {
	@Autowired
	private AppUserService appUserService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtService jwtService;
	
	@PostMapping("/register")
	public void registerAppUser(@RequestBody AppUser user) {
		appUserService.AppUserRegister(user);
	}
	
	@PostMapping("/generateToken")
	public String authenticateAndGetToken(@RequestBody AppUser appUser ) {
		Authentication authentication=authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword()));
		if(authentication.isAuthenticated()) {
			return jwtService.generateToken(appUser.getUsername());
		}
		else
			throw new UsernameNotFoundException("Invalid User Request");
	}
	
	

}
