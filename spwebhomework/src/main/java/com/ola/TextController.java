package com.ola;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.nio.charset.Charset;
import java.sql.Timestamp;

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
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("texts")
public class TextController {

	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
	TextingService textService;
	
	@RequestMapping(method = RequestMethod.POST, 
            produces = "application/json; charset=UTF-8")
    public  ResponseEntity<TextModel> getNdReturnUserText(
    		 @RequestParam(value = "text", required = true) String text,
    	     @RequestParam(value = "userName", required = true) String userName)
{  
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);	    
		long time = System.currentTimeMillis();
	    Timestamp timestamp = new Timestamp(time);
	    
		TextModel usertext = new TextModel(userName, text, timestamp);
	//	logger.debug("the value is as follow :" + txt);
		TextModel txt = textService.saveText(usertext);
		logger.debug("the value is as follow :" + txt);
	        return new ResponseEntity<TextModel>(txt ,responseHeaders, HttpStatus.CREATED);
	    }

	
	
	// Endpoint to retrieve all user texts
		@RequestMapping(value = "{userName}", method = RequestMethod.GET, 
	            produces = "application/json; charset=UTF-8")
	    public ResponseEntity<List<TextModel>> getAllUserTexts(
	    		@PathVariable("userName")  String userName) {
			final HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
			List<TextModel> listOfUsersTexts = new java.util.ArrayList<>();
			if(userName !=null){
				 listOfUsersTexts= textService.getUserTexts(userName);
				
				 if (!listOfUsersTexts.equals(null)){
				 return new ResponseEntity<List<TextModel>>(listOfUsersTexts,httpHeaders , HttpStatus.OK);
			}
			
				 else
				 {
				return new ResponseEntity<List<TextModel>>(HttpStatus.INTERNAL_SERVER_ERROR);
				 }
			}
			else
				// if request parameter is missing retunr back request to the user
			return new ResponseEntity<List<TextModel>>(HttpStatus.BAD_REQUEST);
	    }
		
		
	
		@RequestMapping(method = RequestMethod.GET, 
	            produces = "application/json; charset=UTF-8")
	    public  ResponseEntity< List<TextModel>> getAllText() {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
			List<TextModel> listOfTexts = new ArrayList<TextModel>();
			listOfTexts = 	textService.getALLTexts();
		 return new ResponseEntity<List<TextModel>>(listOfTexts,  httpHeaders, HttpStatus.OK);
				  
			}	
		
	}





