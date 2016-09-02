package com.ola;

import java.util.List;
import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



	public interface TextRepository extends CrudRepository<TextModel, Long> {

		  /**
		   * This method will find an texts instance in the database by its username.
		   * Note that this method is not implemented and its working code will be
		   * automagically generated from its signature by Spring Data JPA.
		   */
		
		 public List<TextModel> findByUserName(String UserName);
				 
		 public TextModel findByOid(Long textId);
 
		 @Query("SELECT t FROM Text t where t.oid IS NOT NULL")
		 public List<TextModel> findAllTexts();
		  
		    @Modifying(clearAutomatically = false)
		    @Transactional
		    @Query("UPDATE Text t SET t.responseText = :responseText WHERE t.oid = :toid")
		    int updateResponseText(@Param("toid") Long toid, @Param("responseText") String responseText);
		}


