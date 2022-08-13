package ru.yandex.practicum.catsgram.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.controller.exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.controller.exceptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.service.UserService;

import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Set<User> findAll() {
        log.debug("Текущее количество пользователей: {}", userService.findAll().size());
        return userService.findAll();
    }

    @PostMapping
    public User create(@RequestBody User user) {
        log.debug("Пользователь: {}", user);
        return userService.create(user);
    }

    @PutMapping
    public User put(@RequestBody User user) {
        return userService.put(user);
    }
}
