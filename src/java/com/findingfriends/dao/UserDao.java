/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.findingfriends.dao;

import com.findingfriends.model.User;
import java.util.List;

/**
 *
 * @author nitu
 */
public interface UserDao {

    public boolean addUser(User user);

    public List<User> displayAllUser();
    public boolean isUserPresent(String phoneNumber);
    public User getUser(String phoneNumber);
    public boolean updateUser(String user_id,double gps_lat,double gps_long);
    public User getUserById(String user_id);
}
