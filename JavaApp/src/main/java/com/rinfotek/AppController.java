package com.rinfotek;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {
	@Autowired
	private UserRepository repo;

//for index.html file
	@GetMapping(" ")
	public String viewHomePage() {
		return "index";
	}

	// for signup_form.html file logic
	@GetMapping("/register") // import register from index.html
	public String showSignUpForm(Model model) {
		model.addAttribute("user", new User());
		return "signup_form";
	}

	// Handler for registration
	@PostMapping("/do_register")
	public String processRegistration(User user) {

		// for code/pswd in db encoded formate
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);

		repo.save(user);
		return "register_success";
	}

	@GetMapping("/business_form") // import register from index.html
	public String showBusinessForm(Model model) {
		model.addAttribute("user", new User());
		return "business_form";
	}

	// for users list
	@GetMapping("/list_users")
	public String viewUsersList(Model model) {
		List<User> listUsers = repo.findAll();
		model.addAttribute("listUsers", listUsers);

		return "users";
	}
}
