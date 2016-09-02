package com.ola;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.web.WebAppConfiguration;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@WebAppConfiguration
public class TextControllerTests extends SpwebhomeworkApplication{
	
     private MockMvc mockMvc;

	 @Mock
     private  TextingService textServiceMock;
	 
	 @InjectMocks
	 private TextController textControllerMock;
	 	 
	 @Before
	 public void setup() {
		 MockitoAnnotations.initMocks(this);
		 mockMvc = standaloneSetup(textControllerMock)
		                 .build();      
	    }
	 
	 @Test
	    public void createAndsendUserTextTest() throws Exception {
	    	String txt = "I am here";
	    	TextModel textModel = new TextModel();
	    	textModel.setText(txt);
	    	when(textServiceMock.sendText(txt)).thenReturn(textModel);
	    	mockMvc.perform(post("/texts").param("text" , txt))
	         .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		        .andExpect(status().isCreated())
		        .andExpect(jsonPath("$.text").value("I am here"));    	
	    }
	 
}
