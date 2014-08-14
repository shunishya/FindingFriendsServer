/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.findingfriends.resource;

import com.findingfriends.controller.ContactsController;
import com.findingfriends.controller.UserController;
import com.findingfriends.model.Contact;
import com.findingfriends.model.ContactModel;
import com.findingfriends.model.SyncContactRequest;
import com.findingfriends.model.SyncContactResponse;
import com.findingfriends.model.User;
import java.util.ArrayList;
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
@Path("synccontact")
public class ContactsSyncResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ContactsSyncResource
     */
    public ContactsSyncResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.findingfriends.resource.ContactsSyncResource
     *
     * @param user_id
     * @return an instance of java.lang.String
     */
    @GET
    public String getJson() {
        ContactsController contactController = new ContactsController();
        List<ContactModel> contacts = contactController.getallUser("047fa532-9c33-44be-b815-49ac5779f9f0");
        return contacts.get(0).getName();
    }

    /**
     * PUT method for updating or creating an instance of ContactsSyncResource
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
    public SyncContactResponse syncContact(SyncContactRequest contactSyncRequest) {
        UserController userController = new UserController();
        ContactsController contactController = new ContactsController();
        List<User> contactList = userController.getallUser();
        ArrayList<ContactModel> appContacts = new ArrayList<>();
        ArrayList<Contact> contactsTobeAddToDatabase = new ArrayList<>();
        SyncContactResponse response = new SyncContactResponse();
        if (!contactList.isEmpty()) {
            List<ContactModel> contactsFromUser = contactSyncRequest.getContactsTobeAdd();

            for (int i = 0; i < contactList.size(); i++) {
                for (ContactModel contactModel : contactsFromUser) {
                    if (contactList.get(i).getPhoneNumber().equals(contactModel.getPhonenumber())) {
                        Contact contactToBeAddToDataBase = new Contact();
                        contactToBeAddToDataBase.setName(contactModel.getName());
                        contactToBeAddToDataBase.setParent_id(contactSyncRequest.getUser_id());
                        contactToBeAddToDataBase.setPhoneNumber(contactModel.getPhonenumber());
                        contactToBeAddToDataBase.setUser_id(contactList.get(i).getUser_id());
                        contactModel.setUser_id(contactList.get(i).getUser_id());
                        appContacts.add(contactModel);
                        contactsTobeAddToDatabase.add(contactToBeAddToDataBase);
                    }
                }
            }
        }
        if (contactController.addContact(contactsTobeAddToDatabase, contactSyncRequest.getUser_id())) {
            response.setError(false);
            response.setAppUsers(appContacts);
        } else {
            response.setError(true);
            response.setMessage("User can't be added to server database.");
        }
        if (!contactSyncRequest.getContactsToBeDeleted().isEmpty()) {
            int count = contactController.deleteContacts(contactSyncRequest.getContactsToBeDeleted(), contactSyncRequest.getUser_id());
            if (count == -1) {
                response.setError(true);
                response.setAppUsers(appContacts);
            } else {
                response.setError(false);
                response.setMessage("User can't be deleted from server database.");
            }
        }
        return response;
    }
}
