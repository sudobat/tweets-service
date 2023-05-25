package com.caixabanktech.arq.tweetsservice.controller;

import static org.hamcrest.CoreMatchers.notNullValue;

import java.util.UUID;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.caixabanktech.arq.tweetsservice.domain.TweetDomain;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

/**
 * Test componente
 * @author u0180790
 *
 */
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class TweetsControllerTest {

	@Autowired
	private WebApplicationContext context;
	@Autowired
	private MockMvc mockMvc;
	
	
	private static String CONTEXT="/tweets";
	@BeforeEach
	void beforeEach() {
		RestAssuredMockMvc.webAppContextSetup(context);
		RestAssuredMockMvc.mockMvc(mockMvc);
	}
	
	@Test	
	void givenSaveOneTweetThenValidateReturnTweetList() {
		
		UUID uuid=UUID.randomUUID();
		//El Tweet no existe
		RestAssuredMockMvc.given()
		.when()
		.get(CONTEXT+"/"+uuid.toString())
		.then()		
		.statusCode(HttpStatus.SC_NOT_FOUND);

	
		TweetDomain domainT=new TweetDomain("Pantalones Rojos","TrollTweeter",null,null);

		RestAssuredMockMvc.given()
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.body(domainT)
		.when()
		.post(CONTEXT)		
		.then()		
		.statusCode(HttpStatus.SC_CREATED)
		.body("id", notNullValue());	
		
		RestAssuredMockMvc.given()
		.when()
		.get(CONTEXT)
		.then()		
		.statusCode(HttpStatus.SC_OK)
		.body("", hasSize(1));		
		
	}
	
	@Test
	void givenSaveOneTweetThenValidateUpdateCorrect() {
		UUID uuid=UUID.randomUUID();
		
		TweetDomain tweet=new TweetDomain("Tweet Polemico","YoSoyElAutor",null,uuid);
		
		/*Insert*/
		RestAssuredMockMvc.given()
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.body(tweet)
		.when()
		.post(CONTEXT)		
		.then()		
		.statusCode(HttpStatus.SC_CREATED)
		.body("id", equalTo(uuid.toString()));
		
		/**
		 * VALIDAMOS EL UPDATE
		 */
		
		RestAssuredMockMvc.given()
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.when()
		.get(CONTEXT+"/"+uuid.toString())		
		.then()		
		.statusCode(HttpStatus.SC_OK)
		.body("desc", equalTo("Tweet Polemico"));	
		
		tweet.setDesc("Tweet NO Polemico");
		
		RestAssuredMockMvc.given()
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.body(tweet)
		.when()
		.put(CONTEXT+"/"+uuid.toString())		
		.then()		
		.statusCode(HttpStatus.SC_CREATED)
		.body("id", equalTo(uuid.toString()));		
		
		RestAssuredMockMvc.given()
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.when()
		.get(CONTEXT+"/"+uuid.toString())		
		.then()		
		.statusCode(HttpStatus.SC_OK)
		.body("desc", equalTo("Tweet NO Polemico")).and().body("desc", not(equalTo("Tweet Polemico")));	

		/**
		 * FIN VALIDAMOS EL UPDATE
		 */		
		/**
		 * VALIDAMOS EL DELETE
		 */
		
		RestAssuredMockMvc.given()
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.when()
		.delete(CONTEXT+"/"+uuid.toString())		
		.then()		
		.statusCode(HttpStatus.SC_NO_CONTENT);
		
		
		RestAssuredMockMvc.given()
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.when()
		.get(CONTEXT+"/"+uuid.toString())		
		.then()		
		.statusCode(HttpStatus.SC_NOT_FOUND);	

		/**
		 * FIN VALIDAMOS EL DELETE
		 */
		
		
		
	}
}
