package ru.yandex.practicum.catsgram.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.controller.exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.controller.exceptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final Set<User> users = new HashSet<>();

    @GetMapping
    public Set<User> findAll() {
        log.debug("Текущее количество пользователей: {}", users.size());
        return users;
    }

    @PostMapping
    public User create(@RequestBody User user) {
        if (user.getEmail() == null || user.getEmail() == "") {
            throw new InvalidEmailException();
        }
        if (users.contains(user)) {
            throw new UserAlreadyExistException();
        }
        log.debug("Пользователь: {}", user);
        users.add(user);
        return user;
    }

    @PutMapping
    public User put(@RequestBody User user) {
        if (user.getEmail() == null || user.getEmail() == "") {
            throw new InvalidEmailException();
        }
        users.add(user);
        return user;
    }
}
