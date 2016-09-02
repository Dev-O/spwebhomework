package com.ola;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository

	public interface TextRepository extends CrudRepository<TextModel, Long> {

		  /**
		   * This method will find an texts instance in the database by its username.
		   * Note that this method is not implemented and its working code will be
		   * automagically generated from its signature by Spring Data JPA.
		   */
		
		 public List<TextModel> findByUserName(String UserName);
				 
		 public TextModel findByOid(Long textId);
 
		 @Query("SELECT t FROM TextModel t where t.oid IS NOT NULL")
		 public List<TextModel> findAllTexts();
		  
}


