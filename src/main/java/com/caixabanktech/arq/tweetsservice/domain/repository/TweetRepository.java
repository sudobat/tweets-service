package com.caixabanktech.arq.tweetsservice.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caixabanktech.arq.tweetsservice.domain.entity.Tweet;

@Repository
public interface TweetRepository extends JpaRepository<Tweet,UUID>{

}
