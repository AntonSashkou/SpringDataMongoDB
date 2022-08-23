package com.sashkou.springdata.dao.mongo.repository;

import com.sashkou.springdata.service.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, Long> {

    List<User> findAll(Sort sort);
}
