package com.sashkou.springdata.dao.mongo.template;

import com.sashkou.springdata.service.User;
import com.sashkou.springdata.service.UserStore;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "mongoTemplateUserStore")
@RequiredArgsConstructor
public class MongoTemplateUserStore implements UserStore {

    private final MongoTemplate mongoTemplate;

    @Override
    public List<User> findAll() {
        return mongoTemplate.findAll(User.class);
    }

    @Override
    public List<User> findAll(int order, String... sortProperties) {
        Sort.Direction direction = order == 1 ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortProperties);

        Query query = new Query();
        query.with(sort);

        return mongoTemplate.find(query, User.class);
    }

    @Override
    public User findById(Long id) {
        return mongoTemplate.findById(id, User.class);
    }

    @Override
    public void save(User user) {
        mongoTemplate.save(user);
    }
}
