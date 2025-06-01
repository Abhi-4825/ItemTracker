package com.project.ItemTracker.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.ItemTracker.Model.AppUser;
import com.project.ItemTracker.Repository.AppUserRepo;
@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private AppUserRepo appUserRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            		AppUser user=appUserRepo.findById(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
		return User.withUsername(user.getUsername()).password(user.getPassword()).build();
	}

}
