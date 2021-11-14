package xyz.geniuslaec.discounts.services.contracts;

import java.util.Date;
import java.util.List;

import xyz.geniuslaec.discounts.entities.User;
import xyz.geniuslaec.discounts.entities.UserType;

public interface UserService {
    User add(UserType userType, Date sinceDate);
    User getById(int id);
    List<User> getAll();
}
