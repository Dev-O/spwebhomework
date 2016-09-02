package com.ola;

import java.sql.Timestamp;
import javax.validation.constraints.NotNull;
import org.springframework.context.annotation.ComponentScan;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.hateoas.ResourceSupport;

@ComponentScan
@Entity
@Table(name = "Posts")
public class TextModel extends ResourceSupport {

	public TextModel(String text){
	
		this.text = text;	
	}
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name="OID")
	private Long oid;
	
	@Column(name="TEXT_POSTED", nullable=false,updatable=false)
	private  String text;
	
	@Column(name="USER_NAME", nullable=false,updatable=false)
	private  String userName;
	
	@NotNull
	@Column(name="TIME_CREATED", nullable=false,updatable=false)
	private Timestamp timePosted;
	
	 @JsonCreator
	    public TextModel(@JsonProperty("userName") String userName, @JsonProperty("text") String text, @JsonProperty("timePosted") Timestamp timePosted) {   
	        this.text= text ;
	        this.userName = userName;
	        this.timePosted = timePosted;
	    }
	public TextModel(){
		
			
	}
     
	 public Long getOid() {
			return oid;
		}

	    public void setOid (Long oid) {
			this.oid = oid;
		}
	   
	    
	    public String getText(){
	    	
	    	return text;
	    
	    }
	    
       public void setText(String text){
	    	
	    	this.text = text;
	    }
	    
	    public String getUserName(){
	     	
	     	return userName;
	     }
	    public void setUserName(String userName){
	     	this.userName = userName;
	     	
	     }
}
