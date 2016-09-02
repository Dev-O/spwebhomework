package com.ola;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RestController
@RequestMapping("texts")
public class TextController {

	@Autowired
	TextingService textService;
	
	@RequestMapping(method = RequestMethod.POST, 
            produces = "application/json; charset=UTF-8")
    public  ResponseEntity<TextModel> getNdReturnUserText(
    		@RequestParam(value = "text", required = true) String userSentText)
{  
		HttpHeaders responseHeaders = new  HttpHeaders();   
		responseHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		TextModel textt = textService.sendText(userSentText);
	        return new ResponseEntity<TextModel>(textt ,responseHeaders, HttpStatus.CREATED);
	    }

	}


