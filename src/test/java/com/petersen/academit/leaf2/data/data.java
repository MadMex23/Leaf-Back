package com.petersen.academit.leaf2.data;

import com.petersen.academit.leaf2.entity.Account;
import com.petersen.academit.leaf2.entity.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class data {
    public static User getTestUser1() {
        return new User(1, "Test", "User", "test1@leaf.com", "Test1234!");
    }

    public static User getTestUser2() {
        return new User(2, "Other", "User", "test2@leaf.com", "Other1234!");
    }

    public static Account getTestAccount1() {
        return new Account(1, "Chase Bank", "Bank", 5000, getTestUser1());
    }

    public static Account getTestAccount2() {
        return new Account(2, "Wallet", "Cash", 7000, getTestUser2());
    }

    public static List<Account> accountArrayList = Arrays.asList(getTestAccount1());
}
