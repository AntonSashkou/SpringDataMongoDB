package com.sashkou.springdata.controller;

import com.sashkou.springdata.service.User;
import com.sashkou.springdata.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @GetMapping
    public List<User> getAll() {
        return service.getAll();
    }

    @GetMapping("/sort")
    public List<User> getAndSortAll(@RequestParam int order, @RequestParam String[] properties) {
        return service.getAll(order, properties);
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable @NonNull Long id) {
        return service.get(id);
    }

    @PostMapping
    public void create(@RequestBody @Valid User user) {
        service.create(user);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable @NonNull Long id, @RequestBody @Valid User user) {
        service.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @NonNull Long id) {
        service.delete(id);
    }
}
