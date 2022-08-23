package com.sashkou.springdata.service;

import java.util.List;

public interface UserService {

    List<User> getAll();
    List<User> getAll(int order, String... properties);
    User get(Long id);
    void create(User user);
    void update(Long id, User user);
    void delete(Long id);
}
