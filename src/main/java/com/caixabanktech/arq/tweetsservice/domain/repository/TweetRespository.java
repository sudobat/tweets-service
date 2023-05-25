package com.caixabanktech.arq.tweetsservice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caixabanktech.arq.tweetsservice.domain.entity.Tweet;

@Repository
public interface TweetRespository extends JpaRepository<Tweet,String>{
	
	

}
