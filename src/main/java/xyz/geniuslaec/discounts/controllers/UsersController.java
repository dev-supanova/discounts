package xyz.geniuslaec.discounts.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.geniuslaec.discounts.entities.User;
import xyz.geniuslaec.discounts.models.CreateUserModel;
import xyz.geniuslaec.discounts.services.contracts.UserService;

@RestController
@RequestMapping(path = "/users")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        var user = userService.getById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody() CreateUserModel userModel) {
        if (userModel.getCustomerSince() == null || userModel.getUserType() == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(userService.add(userModel.getUserType(), userModel.getCustomerSince()));
    }
}
