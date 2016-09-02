package com.ola;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


	@WebAppConfiguration
	public class TextControllerAPITestsDocumentation extends SpwebhomeworkApplicationTests  {
		
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	 	@Autowired
		public TextRepository textRepository;
		
	 	@Rule
		public JUnitRestDocumentation restDocumentation =
				new JUnitRestDocumentation("target/generated-snippets");
		
	 	private MockMvc mockMvc;
		
		@Autowired
	    private WebApplicationContext webApplicationContext;
	    
	    Long fOid, sOid, tOid;
		
		Long[] arrayOfIds= new Long[]{fOid, sOid, tOid};
	   
	    @Before
	    public void setUp() {
	        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
	                .apply(documentationConfiguration(this.restDocumentation))
	                .build();
	        
	        textRepository.deleteAll();
	        // fill database with some values
	    	long time = System.currentTimeMillis();
		    Timestamp timestamp = new Timestamp(time);
		    textRepository.save(new TextModel("shannon", "I see you shannon",timestamp));
		    
			long time2 = System.currentTimeMillis();
		    Timestamp timestamp2 = new Timestamp(time2);
		    textRepository.save(new TextModel("shannon", "I am here now",timestamp2));
			long time3 = System.currentTimeMillis();
		    Timestamp timestamp3 = new Timestamp(time3);
		    textRepository.save(new TextModel("Arnold", "Hello Arnold",timestamp3));
			 List<TextModel> listOfTexts = new ArrayList<TextModel>();
			 //listOfTexts  = textRepository.findAllTexts();
			 Iterable<TextModel> x = textRepository.findAll();
			    for(TextModel y : x){
			    	
			    	if (y.getUserName().equalsIgnoreCase("shannon") && y.getText().equalsIgnoreCase("I see you shannon")){
			    		
			    	    fOid = y.getOid();}
			    	
			    	 if (y.getUserName().equalsIgnoreCase("shannon") && y.getText().equalsIgnoreCase("I am here now")){
			    		
			    	     sOid = y.getOid();
			    	}
			    		
			    	if (y.getUserName().equalsIgnoreCase("Arnold")){
			    		
			    		tOid = y.getOid();
			    	}
			    		 
			    	}
	    }
		
	@Test
	public void createAndsendUserTextTest() throws Exception {
		MultiValueMap<String,String> paramVal= new   LinkedMultiValueMap<String, String>();
	    String textWord = "How are you";
	    String resText = "olammon";
	    paramVal.add("text", textWord);
	    paramVal.add("userName", resText);
		mockMvc.perform(post("/texts").params(paramVal))
	   .andExpect(status().isCreated())
	          .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
	          .andExpect(jsonPath("$.text").value("How are you"))
	          .andExpect(jsonPath("$.userName").value("olammon"))
			  .andDo(document("texts", responseFields( 
						fieldWithPath("text").description("The user's texts"), 
						fieldWithPath("userName").description("The user's handle"),
						fieldWithPath("timePosted").description("The time Text was Posted"),
						fieldWithPath("oid").ignored()),
						requestParameters( 
								parameterWithName("text").description("The text to return")
								,parameterWithName("userName").description("The handle of the user"))));
		  
	}

	@Test
	public void getAlltextShouldReturnAlltextsTest() throws Exception {
	Integer x =3;
	mockMvc.perform(get("/texts"))
	.andExpect(status().isOk())
	      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
	       .andExpect(jsonPath("$.[0].userName").value("shannon"))
	       .andExpect(jsonPath("$.[0].text").value("I see you shannon"))
	       .andExpect(jsonPath("$", hasSize(3)));
													
	}
	
	@Test
	public void getSpecificUserTextsTest() throws Exception {
	  String userName = "shannon";
		mockMvc.perform(get("/texts/" +userName ))
	   .andExpect(status().isOk())
	          .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
	          .andExpect(jsonPath("$", hasSize(2)))
				.andDo(document("texts", responseFields(
						fieldWithPath("[].userName").ignored(),
						fieldWithPath("[].text").ignored(),
						fieldWithPath("[].timePosted").ignored(),
						fieldWithPath("[].links").ignored(),
						fieldWithPath("[].oid").ignored())
						  ));
		
	}
	@After // tearDown()

	public void after() throws Exception {
	textRepository.deleteAll();
	}

	}
