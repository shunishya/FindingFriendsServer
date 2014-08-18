/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.findingfriends.controller;

import com.findingfriends.daoimpl.UserDaoImplementation;
import com.findingfriends.model.User;
import java.util.List;

/**
 *
 * @author nitu
 */
public class UserController {

    UserDaoImplementation userDaoImplementation = new UserDaoImplementation();

    public boolean addUser(User user) {
        return userDaoImplementation.addUser(user);
    }

    public List<User> getallUser() {
        return userDaoImplementation.displayAllUser();
    }

    public User getUser(String phoneNumber) {
        return userDaoImplementation.getUser(phoneNumber);
    }

    public boolean isUserPresent(String phoneNumber) {
        return userDaoImplementation.isUserPresent(phoneNumber);
    }

    public boolean updateUser(String user_id, double gps_lat, double gps_long) {
        return userDaoImplementation.updateUser(user_id, gps_lat, gps_long);
    }

    public User getUserById(String user_id) {
        return userDaoImplementation.getUserById(user_id);
    }
}
