package com.ola;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class TextingService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public TextRepository textRepository;
	

	// get all the texts in db
	List<TextModel> getALLTexts(){
		
		try{
		List<TextModel> listOfAllTextsInDb = textRepository.findAllTexts();
		return listOfAllTextsInDb;	
		}
		
		catch(Exception ex){
			
			logger.debug( " This is an error " + " " + ex.getMessage());		
			return null;		
		}
	}
	
	
	// fetch list of user specific text from db
List<TextModel> getUserTexts(String userName){		
	
	try{
	List<TextModel> listOfAllUserTexts = textRepository.findByUserName(userName);
	return listOfAllUserTexts;	
	}
	
	catch(Exception ex){
		
		logger.debug( " This is an error " + " " + ex.getMessage());
		
		return null;
	}
	}

// update text entity with user response text
		
TextModel  UpdateText(Long id, String userResponse){
	
	try{
	int x = textRepository.updateResponseText (id, userResponse);
		
		return textRepository.findByOid(id);
	
	}
		catch(Exception ex){
			logger.debug(ex.getMessage());
			return null;	
		}
	     
}
     	
// save text in db	
TextModel saveText(TextModel text) {
	
	try {
	textRepository.save(text);
	return text;
	}
	catch(Exception ex){
		logger.debug(ex.getMessage());	
		
		return null;
	}
}

TextModel getTextById(Long texId){
	
	try {
	TextModel txt =  textRepository.findByOid(texId);
	return txt;
	}
	
	catch(Exception ex ){
		logger.debug("Getting textById returns :" , ex.getMessage());
		return null;
	}
	
}
}
