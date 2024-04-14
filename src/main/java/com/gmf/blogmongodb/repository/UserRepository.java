package com.gmf.blogmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gmf.blogmongodb.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
}
