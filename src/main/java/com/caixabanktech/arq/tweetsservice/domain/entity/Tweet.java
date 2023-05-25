package com.caixabanktech.arq.tweetsservice.domain.entity;



import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Tweet {
	@Id
	private UUID id;
	
	private String desc;
	
	private String author;
	
	private Date createdAt;
}
