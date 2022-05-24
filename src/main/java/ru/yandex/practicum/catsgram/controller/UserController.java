package ru.yandex.practicum.catsgram.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.exception.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.HashMap;
@RestController
@RequestMapping("/users")
public class UserController {

    private final HashMap<String, User> users = new HashMap<>();

    @GetMapping
    public HashMap<String, User> findAllUsers() {
        return users;
    }

    @PostMapping
    public void createUser(@RequestBody User user) throws InvalidEmailException, UserAlreadyExistException {
        if ((user.getEmail() == null) && (user.getEmail().equals(""))) {
            throw new InvalidEmailException("Отсутствует email");
        }
        if (users.containsKey(user.getEmail())) {
            throw new UserAlreadyExistException("Пользователь с таким email" + user.getEmail() + " уже существует");
        }
        users.put(user.getEmail(), user);
    }

    @PutMapping
    public void updateUser(@RequestBody User user) throws InvalidEmailException {
        if ((user.getEmail() == null) && (user.getEmail().equals(""))) {
            throw new InvalidEmailException("Отсутствует email");
        }
        users.put(user.getEmail(), user);
    }
}
