package xyz.geniuslaec.discounts.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import xyz.geniuslaec.discounts.entities.User;
import xyz.geniuslaec.discounts.entities.UserType;
import xyz.geniuslaec.discounts.services.contracts.UserService;

@Service
public class UserServiceImpl implements UserService {
    private List<User> users = new ArrayList<>();

    public UserServiceImpl() {
        users.add(new User(0, UserType.EMPLOYEE, new Date()));
    }

    @Override
    public User add(UserType userType, Date sinceDate) {
        var user = new User(users.size(), userType, sinceDate);
        users.add(user);
        return user;
    }

    @Override
    public User getById(int id) {
        return users.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<User> getAll() {
        return users;
    }
    
}
