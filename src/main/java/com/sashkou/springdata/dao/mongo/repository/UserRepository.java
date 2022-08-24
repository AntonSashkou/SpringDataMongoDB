package com.sashkou.springdata.dao.mongo.repository;

import com.sashkou.springdata.service.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends MongoRepository<User, Long> {

    List<User> findAll(Sort sort);
}
