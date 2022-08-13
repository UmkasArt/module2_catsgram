package ru.yandex.practicum.catsgram.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    private static final Logger log = LoggerFactory.getLogger(PostController.class);

    @GetMapping("/posts")
    public List<Post> findAll() {
        log.debug("Текущее количество постов: {}", postService.findAll().size());
        return postService.findAll();
    }

    @GetMapping("/posts/post/{postId}")
    public Optional<Post> findById(@PathVariable int postId) {
        return postService.findAll().stream()
                .filter(x -> x.getId() == postId)
                .findFirst();
    }

    @PostMapping(value = "/posts/post")
    public Post create(@RequestBody Post post) {
        log.debug("Пост: {}", post);
        return postService.create(post);
    }
}