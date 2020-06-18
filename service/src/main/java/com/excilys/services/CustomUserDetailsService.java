package com.excilys.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.excilys.model.Role;
import com.excilys.model.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private AuthenticationService authService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if (username.trim().isEmpty()) {
			throw new UsernameNotFoundException("username is empty");
		}
 
		User user = authService.findFirstByUsername(username);
 
		if (user == null) {
			throw new UsernameNotFoundException("User " + username + " not found");
		}
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getGrantedAuthorities(user));
	}
 
	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		Role role = user.getRole();
		authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		
		return authorities;
	}

}
