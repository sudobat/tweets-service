package com.caixabanktech.arq.tweetsservice.domain.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.caixabanktech.arq.tweetsservice.domain.TweetDomain;
import com.caixabanktech.arq.tweetsservice.domain.entity.Tweet;


@Mapper(componentModel="spring")
public interface TweetMapper {
	
	TweetMapper INSTANCE=Mappers.getMapper(TweetMapper.class);

	TweetDomain map(TweetDomain tweet);
	TweetDomain map(Tweet tweet);	
	List<Tweet> map(List<TweetDomain> tweet);
}
