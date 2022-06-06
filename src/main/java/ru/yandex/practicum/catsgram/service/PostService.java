package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exception.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final List<Post> posts = new ArrayList<>();
    private final UserService userService;

    public PostService(UserService userService) {
        this.userService = userService;
    }


    public List<Post> findAll() {
        return posts;
    }

    public Post create(Post post) {
        if (userService.findUserByEmail(post.getAuthor()) == null) {
            throw new UserNotFoundException("Пользователь " + post.getAuthor() + "не найден");
        }
        posts.add(post);
        return post;
    }

    public Optional<Post> findById(int postId) {
            return posts.stream()
                    .filter(x -> x.getId() == postId)
                    .findFirst();
    }
}