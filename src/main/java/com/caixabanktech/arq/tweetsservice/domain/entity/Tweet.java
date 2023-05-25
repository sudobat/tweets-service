package com.caixabanktech.arq.tweetsservice.domain.entity;



import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tweet {
	@Id
	private UUID id;
	
	private String desc;
	
	private String author;
	
	private Date createdAt;
}
