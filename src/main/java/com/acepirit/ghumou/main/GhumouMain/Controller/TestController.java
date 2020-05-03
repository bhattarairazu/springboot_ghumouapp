package com.acepirit.ghumou.main.GhumouMain.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acepirit.ghumou.main.GhumouMain.Entity.AuthenticateRequest;

@RestController
@RequestMapping("/test")
public class TestController {
	@PostMapping("/formdata")
	public void testMapping(@ModelAttribute("authrequest") AuthenticateRequest authrequest) {
		System.out.println("thisi form data"+authrequest.getUsername()+" pass is "+authrequest.getPassword());
	}

}
