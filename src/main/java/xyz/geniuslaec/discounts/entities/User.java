package xyz.geniuslaec.discounts.entities;

import java.util.Date;

public class User {
    private int id;
    private UserType userType;
    private Date customerSince;

    public User(int id, UserType userType, Date customerSince) {
        this.id = id;
        this.userType = userType;
        this.customerSince = customerSince;
    }

    public int getId() {
        return id;
    }

    public UserType getUserType() {
        return userType;
    }

    public Date getCustomerSince() {
        return customerSince;
    }
}
