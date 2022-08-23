package com.sashkou.springdata.service;

import java.util.List;

public interface UserStore {

    List<User> findAll();
    List<User> findAll(int order, String... sortProperties);

    User findById(Long id);

    void save(User user);
    //void update(Long id, User user);
    //void delete(Long id);
}
