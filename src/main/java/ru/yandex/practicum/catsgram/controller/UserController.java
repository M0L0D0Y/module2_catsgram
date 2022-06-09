package ru.yandex.practicum.catsgram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.exception.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.service.UserService;

import java.util.Collection;
import java.util.Optional;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public Collection<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) throws InvalidEmailException, UserAlreadyExistException {
        return userService.createUser(user);
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) throws InvalidEmailException {
        return userService.updateUser(user);
    }

    @GetMapping("/users/{emailUser}")
    public Optional<User> findById(@PathVariable String emailUser) {
        return userService.findByEmail(emailUser);
    }
}
