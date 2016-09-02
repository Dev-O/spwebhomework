package com.ola;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.mockito.Mockito.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;

@WebAppConfiguration
public class TextControllerTests extends SpwebhomeworkApplicationTests{
	
   private MockMvc mockMvc;
	  //  TextController textControllerMock;  
	
	 @Mock
    private  TextingService textServiceMock;
	 
	 @InjectMocks
	 private TextController textControllerMock;
	 
	 //TextController textControllerMock = org.mockito.Mockito.mock(TextController.class);
	 
	 @Before
	 public void setup() {
		 MockitoAnnotations.initMocks(this);
		 mockMvc = standaloneSetup(textControllerMock)
		                 .build();
	        
	    }
	 
	 @Test
	    public void createAndsendUserTextTest() throws Exception {
	    	long time = System.currentTimeMillis();
		    Timestamp timestamp = new Timestamp(time);
	    	String texton = "I am here";
	    	String userNamere = "pala";
	    	TextModel myText = new TextModel (texton, userNamere,timestamp);		
	    	when(textServiceMock.saveText(myText)).thenReturn(myText);
	    	String texton1 = "I am here";
	    	String userNamere1 = "pala";
	    	MultiValueMap<String,String> paramVal= new   LinkedMultiValueMap<String, String>();
	        paramVal.add("text", texton1);
	        paramVal.add("userName",userNamere1);
	    	mockMvc.perform(post("/texts").params(paramVal))
	         .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		        .andExpect(status().isCreated())
		        .andExpect(jsonPath("$.text").value("I am here"))
	            .andExpect(jsonPath("$.userName").value("pala"));    	
	    }

	 
	    @Test
	    public void getAlltextShouldReturnAlltextsTest() throws Exception {
	    	long time = System.currentTimeMillis();
		    Timestamp timeStamp = new Timestamp(time);
		    TextModel  txt1 = new TextModel ("Bada","hello Bada", timeStamp);
	    	long time2 = System.currentTimeMillis();
		    Timestamp timeStamp2 = new Timestamp(time2);
		    TextModel  txt2 = new TextModel ("Aaron","hello Aaron", timeStamp2);
	    	long time3 = System.currentTimeMillis();
		    Timestamp timeStamp3 = new Timestamp(time3);
		    TextModel  txt3 = new TextModel ("Collins","hello Collins", timeStamp3);
	    	long time4 = System.currentTimeMillis();
		    Timestamp timeStamp4 = new Timestamp(time4);
		    TextModel  txt4 = new TextModel ("Andrew","hello Andrew",timeStamp4 );
	    	List<TextModel > listOfTexts = new ArrayList<>();
	    	listOfTexts.add(txt1);
	    	listOfTexts.add(txt2);
	    	listOfTexts.add(txt3);
	    	listOfTexts.add(txt4);
	        when(textServiceMock.getALLTexts()).thenReturn(listOfTexts);

	        mockMvc.perform(get("/texts") .contentType(MediaType.APPLICATION_JSON_UTF8))
	            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$[0].userName").value("Bada"))
	            .andExpect(jsonPath("$[1].userName").value("Aaron"))
	            .andExpect(jsonPath("$[2].userName").value("Collins"))
	            .andExpect(jsonPath("$[0].text").value("hello Bada"))
	            .andExpect(jsonPath("$[1].text").value("hello Aaron"))
	            .andExpect(jsonPath("$[2].text").value("hello Collins"));
	    }    
	   
	       
	    @Test
	    public void getSpecificUserTextsTest1() throws Exception {
	    	long time = System.currentTimeMillis();
		    Timestamp timeStamp = new Timestamp(time);
		    long time2 = System.currentTimeMillis();
		    Timestamp timeStamp2 = new Timestamp(time2);
	    	String userName = "Awa";
	    	TextModel txt1 = new TextModel("Awa","hello Awa", timeStamp);
	    	TextModel txt2 = new TextModel("Awa","hi Awa", timeStamp2);
	    	List<TextModel> listOfTexts = new ArrayList<>();
	    	listOfTexts.add(txt1);
	    	listOfTexts.add(txt2);
	    	 when(textServiceMock.getUserTexts(userName)).thenReturn(listOfTexts);
	        mockMvc.perform(get("/texts/" +userName))
	           .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$", hasSize(2)))
	            .andExpect(jsonPath("$.[0].userName").value("Awa"))
	            .andExpect(jsonPath("$.[0].text").value("hello Awa"));
	            	    }
}