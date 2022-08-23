package com.sashkou.springdata.dao.mongo.repository;

import com.sashkou.springdata.exception.DataNotFoundException;
import com.sashkou.springdata.service.User;
import com.sashkou.springdata.service.UserStore;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "mongoRepositoryUserStore")
@RequiredArgsConstructor
public class MongoRepositoryUserStore implements UserStore {

    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAll(int order, String... sortProperties) {
        Sort.Direction direction = order == 1 ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortProperties);
        return userRepository.findAll(sort);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new DataNotFoundException(String.format("user with id %d not found", id)));
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
