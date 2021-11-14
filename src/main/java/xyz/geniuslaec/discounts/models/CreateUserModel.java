package xyz.geniuslaec.discounts.models;

import java.util.Date;

import xyz.geniuslaec.discounts.entities.UserType;

public class CreateUserModel {
    private UserType userType;
    private Date customerSince;

    public CreateUserModel(UserType userType, Date customerSince) {
        this.userType = userType;
        this.customerSince = customerSince;
    }

    public UserType getUserType() {
        return userType;
    }

    public Date getCustomerSince() {
        return customerSince;
    }
}
