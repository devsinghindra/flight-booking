package com.devsinghindra.flight.dao;

import com.devsinghindra.flight.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends MongoRepository<User,String> {
}
