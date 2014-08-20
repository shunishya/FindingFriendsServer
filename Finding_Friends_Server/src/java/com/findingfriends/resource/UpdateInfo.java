/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.findingfriends.resource;

import com.findingfriends.controller.UserController;
import com.findingfriends.model.UpdateLocation;
import com.findingfriends.model.UpdateLocationResponse;
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
@Path("update")
public class UpdateInfo {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UpdateInfo
     */
    public UpdateInfo() {
    }

    /**
     * Retrieves representation of an instance of
     * com.findingfriends.resource.UpdateInfo
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of UpdateInfo
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
    public UpdateLocationResponse updateLocation(UpdateLocation request) {
        UpdateLocationResponse response = null;
        UserController userController = new UserController();
        if (request != null) {
            if (userController.updateUser(request.getUser_id(), request.getGps_lat(), request.getGps_long())) {
                response = new UpdateLocationResponse();
                response.setError(false);
                response.setMessage("Update success");
            } else {
                response = new UpdateLocationResponse();
                response.setError(true);
                response.setMessage("Update unsuccess");
            }
        } else {
            response = new UpdateLocationResponse();
            response.setError(true);
            response.setMessage("Request is null");
        }
        return response;
    }
}
