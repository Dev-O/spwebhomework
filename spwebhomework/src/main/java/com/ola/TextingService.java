package com.ola;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service

public class TextingService {
	TextModel sendText(String userSentText){
		TextModel userText = new TextModel();
		userText.setText(userSentText);
		
		return userText;
	}
}
