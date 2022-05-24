package ru.yandex.practicum.catsgram.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.exception.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.Collection;
import java.util.HashMap;
@RestController
@RequestMapping("/users")
public class UserController {

    private final HashMap<String, User> users = new HashMap<>();
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public Collection<User> findAllUsers() {
        log.debug("Текущее количество пользователей: {}", users.size());
        return users.values();
    }

    @PostMapping
    public User createUser(@RequestBody User user) throws InvalidEmailException, UserAlreadyExistException {
        if ((user.getEmail() == null) && (user.getEmail().equals(""))) {
            throw new InvalidEmailException("Отсутствует email");
        }
        if (users.containsKey(user.getEmail())) {
            throw new UserAlreadyExistException("Пользователь с таким email" + user.getEmail() + " уже существует");
        }
        log.debug("Создан пользователь: {}", user);
        users.put(user.getEmail(), user);
        return user;
    }

    @PutMapping
    public User updateUser(@RequestBody User user) throws InvalidEmailException {
        if ((user.getEmail() == null) && (user.getEmail().equals(""))) {
            throw new InvalidEmailException("Отсутствует email");
        }
        log.debug("Обновлен/создан пользователь: {}", user);
        users.put(user.getEmail(), user);
        return user;
    }
}
