package com.excilys.mapper;

import org.springframework.stereotype.Component;

import com.excilys.dto.RoleDTO;
import com.excilys.dto.UserDTO;
import com.excilys.model.User;

@Component
public class UserMapper {

	public static UserDTO userToUserDTO(User user) {
		
		if(user.getRole() != null) {
			
			RoleDTO roleDTO = new RoleDTO.Builder().setId(user.getRole().getId())
												   .setRoleName(user.getRole().getRoleName())
												   .build();
			
			UserDTO userDTO = new UserDTO.Builder().setUserName(user.getUsername())
												   .setPassword(user.getPassword())
												   .setRole(roleDTO)
												   .build();
			
			return userDTO;
		} else {
			
			UserDTO userDTO = new UserDTO.Builder().setUserName(user.getUsername())
					   .setPassword(user.getPassword())
					   .build();
			
			return userDTO;
		}
	}
}
