package com.docker.k8s.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/get/message")
	public String getMessage(@RequestParam(required = true) String name) {
		return "Welcome To Docker & K8s and Deply by jenkins : " + name;

	}

	@GetMapping("/")
	public String getMessage() {
		return "Welcome To Docker & K8s : Jai Bajarang Bali ";

	}

}
