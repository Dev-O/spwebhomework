package com.ola;

import org.springframework.stereotype.Component;

@Component
public class TextModel {
	
	private String text;

	public TextModel(String text){
	
		this.text = text;	
	}
	
	public TextModel(){
		
			
	}
     
    public void setText(String text){
    	this.text = text;
    }
    
   public  String  getText(){	
    	return text;
    }
}
