package xyz.geniuslaec.discounts.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import xyz.geniuslaec.discounts.entities.User;
import xyz.geniuslaec.discounts.entities.UserType;
import xyz.geniuslaec.discounts.services.contracts.UserService;

public class UserServiceTests {
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserServiceImpl();
    }

    @Test
    public void userServiceConstructed() {
        List<User> users = userService.getAll();

        assertTrue(users.size() > 0);
    }

    @Test
    public void canGetDefaultUserById() {
        var user = userService.getById(0);

        assertEquals(0, user.getId());
    }

    @Test
    public void canAddUser() {
        var initialSize = userService.getAll().size();

        var user = userService.add(UserType.AFFILIATE, new Date());
        var sizeAfter = userService.getAll().size();

        assertEquals(UserType.AFFILIATE, user.getUserType());
        assertTrue(initialSize < sizeAfter);
    }
}
