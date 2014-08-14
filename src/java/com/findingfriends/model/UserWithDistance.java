/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.findingfriends.model;

/**
 *
 * @author nitu
 */
public class UserWithDistance implements Comparable<UserWithDistance> {

    private User user;
    private double dist;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getDist() {
        return dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }

    @Override
    public int compareTo(UserWithDistance o) {
        if (dist > o.getDist()) {
            return 1;
        } else if (dist == o.getDist()) {
            return 0;
        } else {
            return -1;
        }
    }

}
