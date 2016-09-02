package com.ola;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/")
public class HelloWorldController {

	  @RequestMapping("hello")     
	    public String hello() {
		  
	        return "Hello World!";
	    }

	}


