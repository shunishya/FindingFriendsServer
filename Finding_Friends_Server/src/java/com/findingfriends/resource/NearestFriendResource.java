/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.findingfriends.resource;

import com.findingfriends.controller.ContactsController;
import com.findingfriends.controller.UserController;
import com.findingfriends.model.ContactModel;
import com.findingfriends.model.DistanceUtils;
import com.findingfriends.model.GroupOfFriendRequest;
import com.findingfriends.model.GroupOfFriendsResponse;
import com.findingfriends.model.NearestFriendRequest;
import com.findingfriends.model.NearestFriendResponse;
import com.findingfriends.model.User;
import com.findingfriends.model.UserWithDistance;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author nitu
 */
@Path("nearestfriends")
public class NearestFriendResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of NearestFriendResource
     */
    public NearestFriendResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.findingfriends.resource.NearestFriendResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        return "result";
    }

    /**
     * PUT method for updating or creating an instance of NearestFriendResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public NearestFriendResponse getNearestFriends(NearestFriendRequest request) {
        ContactsController contactsController = new ContactsController();
        UserController userController = new UserController();
        ArrayList<UserWithDistance> listUsers = new ArrayList<>();
        NearestFriendResponse response = new NearestFriendResponse();
        if (request.getUser_id() != null) {
            if (userController.updateUser(request.getUser_id(), request.getLat(), request.getLog())) {
                List<ContactModel> contact = contactsController.getallUser(request.getUser_id(), request.getDevice_id());
                for (ContactModel model : contact) {
                    if (!model.getUser_id().equals(request.getUser_id())) {
                        User user;
                        UserWithDistance userWithDistance = new UserWithDistance();
                        user = userController.getUserById(model.getUser_id());
                        user.setUserName(model.getName());
                        userWithDistance.setUser(user);
                        double dist = DistanceUtils.distance(user.getGps_lat(), user.getGps_long(), request.getLat(), request.getLog());
                        userWithDistance.setDist(dist);
                        listUsers.add(userWithDistance);
                    }
                }
                Collections.sort(listUsers);
            }

            if (listUsers.size() < 5) {
                response.setCount(listUsers.size());
                response.setNearestPeople(listUsers);
            } else {
                response.setCount(5);
                ArrayList<UserWithDistance> users = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    users.add(listUsers.get(i));
                }
                response.setNearestPeople(users);
            }
        } else {
            response.setCount(0);
            response.setError(true);
            response.setMessage("user_id must not be null.");
        }
        return response;
    }

    @Path("/findgroupoffriends")
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public GroupOfFriendsResponse findGroupOfFriends(GroupOfFriendRequest request) {
        ContactsController contactsController = new ContactsController();
        UserController userController = new UserController();
        ArrayList<UserWithDistance> listUsers = new ArrayList<>();
        GroupOfFriendsResponse response = new GroupOfFriendsResponse();
        userController.updateUser(request.getUser_id(), request.getGps_lat(), request.getGps_long());
        for (String id : request.getListOfFriends()) {
            User user = userController.getUserById(id);
            UserWithDistance userWithDistance = new UserWithDistance();
            userWithDistance.setUser(user);
            double dist = DistanceUtils.distance(user.getGps_lat(), user.getGps_long(), request.getGps_lat(), request.getGps_long());
            System.out.println(dist);
            userWithDistance.setDist(dist);
            listUsers.add(userWithDistance);
        }
        Collections.sort(listUsers);
        response.setGroupOfPeople(listUsers);
        return response;
    }

}
