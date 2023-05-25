package com.caixabanktech.arq.tweetsservice.domain.entity;



import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
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

	@Column(name="description")
	private String desc;
	
	private String author;
	
	private Date createdAt;
}
