package com.pahanabilling.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.*;

import com.pahanabilling.dao.impl.UserDaoImpl;
import com.pahanabilling.model.User;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDaoTest {

    private UserDao dao;

    @BeforeAll
    static void initOnce() { /* no-op */ }

    @BeforeEach
    void setUp() {
        dao = new UserDaoImpl();
    }

    @Test
    @Order(1)
    void testCreateAndFindByUsername() {
        String username = "tester_junit";
        User u = new User();
        u.setUsername(username);
        u.setPassword("secret"); // if you hash, DAO handles it
        u.setRole("admin");

        int rows = dao.create(u);
        assertEquals(1, rows);

        Optional<User> found = dao.findByUsername(username);
        assertTrue(found.isPresent());
        assertEquals("admin", found.get().getRole());
        assertEquals(username, found.get().getUsername());
    }
}
