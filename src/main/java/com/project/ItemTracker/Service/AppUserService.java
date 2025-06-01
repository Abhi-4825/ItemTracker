package com.project.ItemTracker.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.project.ItemTracker.Model.AppUser;
import com.project.ItemTracker.Repository.AppUserRepo;

@Service
public class AppUserService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AppUserRepo appUserRepo;
	
    public void AppUserRegister(AppUser user) {
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
        appUserRepo.save(user);
    }
	
	
	
}
