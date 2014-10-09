/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.findingfriends.model;

import java.util.List;

/**
 *
 * @author nitu
 */
public class GroupOfFriendRequest {

    private String user_id;
    private List<String> listOfFriends;
    private double gps_lat;
    private double gps_long;
    private String device_id;

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public double getGps_lat() {
        return gps_lat;
    }

    public void setGps_lat(double gps_lat) {
        this.gps_lat = gps_lat;
    }

    public double getGps_long() {
        return gps_long;
    }

    public void setGps_long(double gps_long) {
        this.gps_long = gps_long;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public List<String> getListOfFriends() {
        return listOfFriends;
    }

    public void setListOfFriends(List<String> listOfFriends) {
        this.listOfFriends = listOfFriends;
    }

}
