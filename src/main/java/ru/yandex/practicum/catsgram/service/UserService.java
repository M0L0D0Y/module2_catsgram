package ru.yandex.practicum.catsgram.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.controller.UserController;
import ru.yandex.practicum.catsgram.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.exception.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;

import java.util.*;

@Service
public class UserService {
    private final HashMap<String, User> users = new HashMap<>();
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public Collection<User> findAllUsers() {
        log.debug("Текущее количество пользователей: {}", users.size());
        return users.values();
    }

    public User createUser(User user) throws InvalidEmailException, UserAlreadyExistException {
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

    public User updateUser(User user) throws InvalidEmailException {
        if ((user.getEmail() == null) && (user.getEmail().equals(""))) {
            throw new InvalidEmailException("Отсутствует email");
        }
        log.debug("Обновлен/создан пользователь: {}", user);
        users.put(user.getEmail(), user);
        return user;
    }

    public User findUserByEmail(String emailUser) {
        for (String key : users.keySet()) {
            if (key.equals(emailUser)) {
                return users.get(key);
            }
        }
        return null;
    }

    public Optional<User> findByEmail(String emailUser) {
        List<User> userList = new LinkedList<>(users.values());
        return userList.stream()
                .filter(x -> x.getEmail().equals(emailUser))
                .findFirst();
    }
}
