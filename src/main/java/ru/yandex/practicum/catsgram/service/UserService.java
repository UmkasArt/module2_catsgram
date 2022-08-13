package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.controller.exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.controller.exceptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    private final Set<User> users = new HashSet<>();

    public User create(User user) {
        if (user.getEmail() == null || user.getEmail() == "") {
            throw new InvalidEmailException();
        }
        if (users.contains(user)) {
            throw new UserAlreadyExistException();
        }
        users.add(user);
        return user;
    }

    public Set<User> findAll() {
        return users;
    }

    public User put(User user) {
        if (user.getEmail() == null || user.getEmail() == "") {
            throw new InvalidEmailException();
        }
        users.add(user);
        return user;
    }

    public User findUserByEmail(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
    }
}
