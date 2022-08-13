package ru.yandex.practicum.catsgram.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.time.LocalDate;
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
    public List<Post> findAll(
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
            @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort
    ) {
        if(!(sort.equals("asc") || sort.equals("desc"))){
            throw new IllegalArgumentException();
        }
        if(page < 0 || size <= 0){
            throw new IllegalArgumentException();
        }
        Integer from = page * size;
        return postService.findAll(size, from, sort);
    }

    @GetMapping("/posts/post/{postId}")
    public Optional<Post> findById(@PathVariable int postId) {
        return postService.findAbsAll().stream()
                .filter(x -> x.getId() == postId)
                .findFirst();
    }

    @PostMapping(value = "/posts/post")
    public Post create(@RequestBody Post post) {
        log.debug("Пост: {}", post);
        return postService.create(post);
    }
}