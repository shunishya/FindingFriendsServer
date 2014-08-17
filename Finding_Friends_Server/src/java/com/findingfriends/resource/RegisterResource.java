/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.findingfriends.resource;

import com.findingfriends.controller.UserController;
import com.findingfriends.model.RegisterRequest;
import com.findingfriends.model.RegisterResponse;
import com.findingfriends.model.User;
import java.util.UUID;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author nitu
 */
@Path("register")
public class RegisterResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RegisterResource
     */
    public RegisterResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.findingfriends.resource.RegisterResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        return "register user";
    }

    /**
     * PUT method for updating or creating an instance of RegisterResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public RegisterResponse register(RegisterRequest request) {
        RegisterResponse response = new RegisterResponse();
        UserController userController = new UserController();
        User user = null;
        if (request != null) {
            user = userController.getUser(request.getPhoneNumber());
            if (user != null) {
                boolean success = userController.updateUser(user.getUser_id(), request.getGps_lat(), request.getGps_long());
                response.setError(false);
                response.setMessage("Register sucess.");
                response.setUser_id(user.getUser_id());

            } else {
                String uuid = UUID.randomUUID().toString();
                user = new User();
                user.setGps_lat(request.getGps_lat());
                user.setGps_long(request.getGps_long());
                user.setPhoneNumber(request.getPhoneNumber());
                user.setUserName(request.getUserName());
                user.setUser_id(uuid);
                user.setTime(System.currentTimeMillis());
                if (userController.addUser(user)) {
                    response.setError(false);
                    response.setMessage("Register sucess.");
                    response.setUser_id(uuid);
                }
            }

        }
        return response;
    }
}
