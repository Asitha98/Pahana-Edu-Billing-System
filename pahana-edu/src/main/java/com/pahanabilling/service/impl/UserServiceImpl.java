package com.pahanabilling.service.impl;

import com.pahanabilling.factory.DaoFactory;
import com.pahanabilling.dao.UserDao;
import com.pahanabilling.model.User;
import com.pahanabilling.service.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl() {
        this(DaoFactory.getUserDao());
    }

    // For tests (dependency injection)
    UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Optional<User> login(String username, String password) {
        return userDao.findByUsername(username)
                .filter(u -> u.getPassword() != null && u.getPassword().equals(password));
        // If you store hashes, swap the comparison for a PasswordUtil.matches(...)
    }

    @Override
    public int createCashier(User user) {
        if (user.getRole() == null || user.getRole().isBlank()) {
            user.setRole("cashier");
        }
        return userDao.create(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
