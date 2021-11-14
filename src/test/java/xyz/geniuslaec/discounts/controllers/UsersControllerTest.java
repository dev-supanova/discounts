package xyz.geniuslaec.discounts.controllers;

import java.util.Arrays;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import xyz.geniuslaec.discounts.entities.User;
import xyz.geniuslaec.discounts.entities.UserType;
import xyz.geniuslaec.discounts.services.contracts.UserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UsersController.class)
public class UsersControllerTest {
    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetsAllUsers() throws Exception {
        var user = new User(1, UserType.OTHER, new Date());
        var users = Arrays.asList(user);

        Mockito.when(userService.getAll()).thenReturn(users);

        mockMvc.perform(get("/users")).andExpect(status().isOk());
    }
}
