package com.pahanabilling.dao;

import com.pahanabilling.model.User;
import java.util.Optional;

public interface UserDao {
    Optional<User> findByUsername(String username);
    int create(User user);  // returns 1 if inserted (username is PK)
}
