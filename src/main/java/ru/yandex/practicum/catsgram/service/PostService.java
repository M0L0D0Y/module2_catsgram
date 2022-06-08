package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exception.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final List<Post> posts = new ArrayList<>();
    private final UserService userService;
    private static Integer globalId = 0;


    public PostService(UserService userService) {
        this.userService = userService;
    }


    public List<Post> findAll(Integer size, Integer from, String sort) {
        return posts.stream().sorted((p0, p1) -> {
            int comp = p0.getCreationDate().compareTo(p1.getCreationDate());
            if (sort.equals("desc")) {
                comp = -1 * comp;
            }
            return comp;
        }).skip(from).limit(size).collect(Collectors.toList());
    }


    public Post create(Post post) {
        if (userService.findUserByEmail(post.getAuthor()) == null) {
            throw new UserNotFoundException("Пользователь " + post.getAuthor() + "не найден");
        }
        post.setId(getNextId());
        posts.add(post);
        return post;
    }

    public Optional<Post> findById(int postId) {
        return posts.stream()
                .filter(x -> x.getId() == postId)
                .findFirst();
    }

    private static Integer getNextId() {
        return globalId++;
    }
}