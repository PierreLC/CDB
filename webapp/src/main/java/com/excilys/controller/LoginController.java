package com.excilys.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.excilys.dto.UserDTO;
import com.excilys.services.AuthenticationService;

@Controller
public class LoginController {
	
	private PasswordEncoder passwordEncoder;
	
	private AuthenticationService authenticationService;
	
	public LoginController(PasswordEncoder passwordEncoder, AuthenticationService authenticationService) {
		this.passwordEncoder = passwordEncoder;
		this.authenticationService = authenticationService; 
	}
	
	@GetMapping(value = { "", "/login", "/home" })
	public String login() {
		
		return "login";
	}
	
	@GetMapping(value = "/addUser")
	public String register(Model model) {
		
		return "register";
	}
	
//	@PostMapping(value = "/addUser")
//	public String register(@ModelAttribute("newUser") UserDTO userDTO) {
//
//		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
//		authenticationService.addUser(userDTO);
//		return "login";
//	}
	
//	@PostMapping(value = {"", "/login", "/home"})
//	public String loginPage(@RequestParam(value = "error", required = false) String error,
//							@RequestParam(value = "logout", required = false) String logout,
//							ModelAndView modelAndView) {
//		
//		String errorMessage = null;
//		if(error != null) {
//			errorMessage = "Username or Password incorrect !";
//		}
//		if(logout != null) {
//			errorMessage = "You have been successfully logged out !";
//		}
//		modelAndView.addObject("errorMessage", errorMessage);
//		
//		return "login";	
//	}
	
//	@GetMapping(value = "/logout")
//	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
//		
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		
//		if(auth != null) {
//			new SecurityContextLogoutHandler().logout(request, response, auth);
//		}
//		return "redirect:/login?logout=true";
//	}
}
