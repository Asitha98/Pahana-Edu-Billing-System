package com.pahanabilling.service;

import com.pahanabilling.model.User;
import java.util.Optional;

public interface UserService {
    /** Authenticate and return the user if credentials are valid. */
    Optional<User> login(String username, String password);

    /** Create a cashier user; return 1 if inserted. */
    int createCashier(User user);

    /** Optional helper to fetch a user by username. */
    Optional<User> findByUsername(String username);
}
