package com.excilys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.excilys.dto.UserDTO;
import com.excilys.mapper.UserMapper;
import com.excilys.model.Role;
import com.excilys.model.User;
import com.excilys.repository.RoleRepository;
import com.excilys.repository.UserRepository;

@Service
public class AuthenticationService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public void addUser(UserDTO userDTO) {
		
		User user = UserMapper.userDTOToUser(userDTO);
		
		userRepository.save(user);
	}
	
	public void addRole(Role role) {
		
		roleRepository.save(role);
	}
	
	public Page<User> listUser(int offset, int pageSize) {
		
		Page<User> usersPag = userRepository.findAll(PageRequest.of(offset, pageSize));
		
		return usersPag;
	}
	
	public User findFirstByUsername(String username) {
		
		return userRepository.findFirstByUsername(username);
	}
}
