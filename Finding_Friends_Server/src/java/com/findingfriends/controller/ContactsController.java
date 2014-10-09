/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.findingfriends.controller;

import com.findingfriends.daoimpl.ContactDaoimplementation;
import com.findingfriends.model.Contact;
import com.findingfriends.model.ContactModel;
import java.util.List;

/**
 *
 * @author nitu
 */
public class ContactsController {

    ContactDaoimplementation contactDao = new ContactDaoimplementation();

    public boolean addContact(List<Contact> contactList, String user_id) {
        return contactDao.addContacts(contactList, user_id);
    }

    public List<ContactModel> getallUser(String user_id, String deviceId) {
        return contactDao.displayAllContactOfUser(user_id, deviceId);
    }

    public int deleteContacts(List<String> contacts_id, String user_id) {
        return contactDao.deleteTheseContacts(contacts_id, user_id);

    }

    public ContactModel getUserContactByPhone(String parent_id, String phoneNumber,String deviceId) {
        return contactDao.getContactOfUserByPhone(parent_id, phoneNumber,deviceId);
    }

}
