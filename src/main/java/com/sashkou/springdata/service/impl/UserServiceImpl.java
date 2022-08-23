package com.sashkou.springdata.service.impl;

import com.sashkou.springdata.service.User;
import com.sashkou.springdata.service.UserService;
import com.sashkou.springdata.service.UserStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserStore userStore;

    public UserServiceImpl(@Qualifier("ormMongoTemplateUserStore") UserStore userStore) {
        this.userStore = userStore;
    }

    @Override
    public List<User> getAll() {
        return userStore.findAll();
    }

    @Override
    public List<User> getAll(int order, String... properties) {
        return userStore.findAll(order, properties);
    }

    @Override
    public User get(Long id) {
        return userStore.findById(id);
    }

    @Override
    public void create(User user) {
        user.setId(new Random().nextLong());
        user.setExpireAt(Date.from(Instant.now()));
        userStore.save(user);
    }

    @Override
    public void update(Long id, User user) {
        log.error("method not implemented");
    }

    @Override
    public void delete(Long id) {
        log.error("method not implemented");
    }
}
