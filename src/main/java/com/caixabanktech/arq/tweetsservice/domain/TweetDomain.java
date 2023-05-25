package com.caixabanktech.arq.tweetsservice.domain;

import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TweetDomain {
	
	private String desc;
	
	private String author;
	
	private Date createdAt;
	
	private UUID id;
	
}
