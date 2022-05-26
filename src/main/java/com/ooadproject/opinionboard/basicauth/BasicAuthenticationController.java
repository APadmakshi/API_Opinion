package com.ooadproject.opinionboard.basicauth;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
public class BasicAuthenticationController {
	@GetMapping("/basicauth")
	public AuthenticationBean baseauth()
	{
		return new AuthenticationBean("You are authenticated");
	}
}
