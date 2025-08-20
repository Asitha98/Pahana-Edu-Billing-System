package com.pahanabilling.dao;

import com.pahanabilling.dao.impl.UserDaoImpl;
import com.pahanabilling.model.User;
import org.junit.*;

import java.sql.*;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeNoException;
import static org.junit.Assume.assumeNotNull;

public class UserDaoImplTest {

    private final UserDao dao = new UserDaoImpl();

    @BeforeClass
    public static void initSchema() {
        try (Connection con = DBConnection.getConnection()) {
            assumeNotNull(con);
            try (Statement st = con.createStatement()) {
                st.execute("CREATE TABLE IF NOT EXISTS users (" +
                        "username VARCHAR(50) NOT NULL PRIMARY KEY," +
                        "password VARCHAR(150) NOT NULL," +
                        "role ENUM('admin','cashier') NOT NULL DEFAULT 'cashier')" +
                        " ENGINE=InnoDB");
            }
        } catch (SQLException e) {
            assumeNoException("DB not reachable", e);
        }
    }

    @Before
    public void clean() throws Exception {
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement()) {
            st.execute("TRUNCATE TABLE users");
        }
    }

    @Test
    public void createAndFindByUsername() {
        User u = new User();
        u.setUsername("tester");
        u.setPassword("secret");
        u.setRole("admin");

        int rows = dao.create(u);
        assertEquals(1, rows);

        Optional<User> found = dao.findByUsername("tester");
        assertTrue(found.isPresent());
        assertEquals("tester", found.get().getUsername());
        assertEquals("admin", found.get().getRole());
    }

    @Test(expected = DaoException.class)
    public void duplicateUsernameThrows() {
        User u1 = new User(); u1.setUsername("dup"); u1.setPassword("x"); u1.setRole("cashier");
        User u2 = new User(); u2.setUsername("dup"); u2.setPassword("y"); u2.setRole("admin");
        dao.create(u1);
        dao.create(u2); // should violate PK and throw DaoException
    }
}
