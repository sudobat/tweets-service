package com.caixabanktech.arq.tweetsservice.integration;

import static org.hamcrest.Matchers.equalTo;

import java.util.UUID;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;

import com.caixabanktech.arq.tweetsservice.domain.TweetDomain;

import io.restassured.RestAssured;

public class TweetsControllerTestIT {
	private static String CONTEXT="/tweets";
	@Value("${micro-url:http://localhost:8080}")
	private String microUrl;
	
	@BeforeEach
	void beforeEach() {
		RestAssured.baseURI=System.getProperty("micro-url","http://localhost:8080");
	}
	
	@Test
	void givenRandomUUIDThenReturnNotFound() throws Exception{
		UUID uuid = UUID.randomUUID();
		RestAssured.given()
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.when()
		.get(CONTEXT+"/"+uuid.toString())		
		.then()		
		.statusCode(HttpStatus.SC_NOT_FOUND);
	}
	
	@Test
	void givenValidUUIDThenReturnValidData() throws Exception{
		UUID uuid = UUID.randomUUID();
		
		TweetDomain tweet=new TweetDomain("Tweet Polemico","YoSoyElAutorIT",null,uuid);
				
		RestAssured.given()
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.when()
		.get(CONTEXT+"/"+uuid.toString())		
		.then()		
		.statusCode(HttpStatus.SC_NOT_FOUND);
		
		RestAssured.given().body(tweet)
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.when()
		.post(CONTEXT)		
		.then()		
		.statusCode(HttpStatus.SC_CREATED);
		
		RestAssured.given()
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.when()
		.get(CONTEXT+"/"+uuid.toString())		
		.then()		
		.statusCode(HttpStatus.SC_OK).body("id", equalTo(uuid.toString()));		
		
		
	}
	
	
	@Test
	void givenValidUUIDThenUpdateValidDataAndReturn() throws Exception{
		UUID uuid = UUID.randomUUID();
		
		TweetDomain tweet=new TweetDomain("Tweet Polemico Update","YoSoyElAutorIT",null,uuid);
				
		RestAssured.given()
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.when()
		.get(CONTEXT+"/"+uuid.toString())		
		.then()		
		.statusCode(HttpStatus.SC_NOT_FOUND);
		
		RestAssured.given().body(tweet)
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.when()
		.post(CONTEXT)		
		.then()		
		.statusCode(HttpStatus.SC_CREATED);
		
		RestAssured.given()
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.when()
		.get(CONTEXT+"/"+uuid.toString())		
		.then()		
		.statusCode(HttpStatus.SC_OK).body("desc", equalTo("Tweet Polemico Update"));		
		
		tweet.setDesc("Tweet Polemico Update1");

		RestAssured.given().body(tweet)
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.when()
		.put(CONTEXT+"/"+uuid.toString())		
		.then()		
		.statusCode(HttpStatus.SC_CREATED);
		
		RestAssured.given()
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.when()
		.get(CONTEXT+"/"+uuid.toString())		
		.then()		
		.statusCode(HttpStatus.SC_OK).body("desc", equalTo("Tweet Polemico Update1"));		
	}
	
	@Test
	void givenValidUUIDThenDeleteAndReturnNoData() throws Exception{
		UUID uuid = UUID.randomUUID();
		
		TweetDomain tweet=new TweetDomain("Tweet Polemico Update","YoSoyElAutorIT",null,uuid);
		
		RestAssured.given()
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.when()
		.get(CONTEXT+"/"+uuid.toString())		
		.then()		
		.statusCode(HttpStatus.SC_NOT_FOUND);
		
		RestAssured.given().body(tweet)
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.when()
		.post(CONTEXT)		
		.then()		
		.statusCode(HttpStatus.SC_CREATED);
		
		RestAssured.given()
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.when()
		.get(CONTEXT+"/"+uuid.toString())		
		.then()		
		.statusCode(HttpStatus.SC_OK).body("desc", equalTo("Tweet Polemico Update"));
		
		RestAssured.given()
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.when()
		.delete(CONTEXT+"/"+uuid.toString())
		.then()		
		.statusCode(HttpStatus.SC_NO_CONTENT);
		
		RestAssured.given()
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.when()
		.get(CONTEXT+"/"+uuid.toString())		
		.then()		
		.statusCode(HttpStatus.SC_NOT_FOUND);
	}
}
