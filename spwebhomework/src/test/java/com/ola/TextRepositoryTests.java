package com.ola;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;


@WebAppConfiguration
public class TextRepositoryTests extends SpwebhomeworkApplicationTests {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	public TextRepository textRepository;
	
	Long fOid, sOid, tOid;
	
	Long[] arrayOfIds= new Long[]{fOid, sOid, tOid};
	
	@Before
    public void setUp() {
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
		listOfTexts  = textRepository.findAllTexts();
	     
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
    
    @Transactional
    @Rollback(true)
    @Test    
    public  void createbNewTextInDbShouldReturnTextCreated(){
     	    Long tempOid = null;
     	try{	
     		logger.info("Test started at" + " " + ":" + LocalDateTime.now());
     		long time = System.currentTimeMillis();
     	    Timestamp timestamp = new Timestamp(time);
     	    textRepository.save(new TextModel("Lee", "well lets see",timestamp));
     	    for (TextModel x : textRepository.findAll()){
     	    	
     	    	if (Arrays.binarySearch(arrayOfIds,x.getOid() , null)<0){
     	    		tempOid = x.getOid();
     	    		
     	    	}
     	    	
     	    }
     		assertEquals(textRepository.findByOid(tempOid).getText(), "well lets see");
     	}	
     	catch(Exception ex){
     		logger.debug("Test is aborted due to Exception" + " : "+ ex.getMessage());	
     	}
     	
     }
     
    @Transactional
    @Rollback(true)
    @Test    
   public  void findTextByIDShouldReturnTextStoredByTheID(){
     	
     	try{	
     		logger.info("Test started at" + " " + ":" + LocalDateTime.now());
     		assertEquals(textRepository.findByOid(fOid).getText(), "I see you shannon");
     		assertEquals(textRepository.findByOid(sOid).getText(), "I am here now");
     	}	
     	catch(Exception ex){
     		logger.debug("Test is aborted due to Exception" + " : "+ ex.getMessage());	
     	}
     	
     }

    @Transactional
    @Rollback(true)
    @Test
  public   void FindTextByUserNameShouldReturnListOfUserTexts(){
     	try{
     		logger.info("Test started at" + " " + ":" + LocalDateTime.now() );
     		List<TextModel> listOfUsersTexts = textRepository.findByUserName("shannon");
     		assertTrue(listOfUsersTexts.size()>0);
     		assertEquals(listOfUsersTexts.get(0).getText(),"I see you shannon");
     		assertEquals(listOfUsersTexts.get(1).getText(),"I am here now");
     		assertEquals(listOfUsersTexts.get(2).getText(),"Hello Arnold");
     	}
     	catch(Exception ex){
     		logger.debug("This is a debug message" + " "+ ex.getMessage());	
     	}	
     }

    @Transactional
    @Rollback(true)
    @Test
 public    void getAllTextsInDBShouldReturnListOfAllTextIndb(){
     	
     	try{
     		logger.info("Test started at" + " " + ":" + LocalDateTime.now());
     		assertTrue(textRepository.findAllTexts().size()>2);
     		assertEquals(textRepository.findAllTexts().get(0).getText(),"I see you shannon");
     		assertEquals(textRepository.findAllTexts().get(1).getText(),"I am here now");
     		assertEquals(textRepository.findAllTexts().get(2).getText(),"Hello Arnold");
     		
     	}
     	catch(Exception ex){
     		logger.debug("This is a debug message" + " "+ ex.getMessage());	
     	}
     		
     	
     }
     
   

}