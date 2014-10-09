/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.findingfriends.dao;

import com.findingfriends.model.Contact;
import com.findingfriends.model.ContactModel;
import java.util.List;

/**
 *
 * @author nitu
 */
public interface ContactsDao {

    public boolean addContacts(List<Contact> contacts, String user_id);

    public List<ContactModel> displayAllContactOfUser(String user_id,String deviceId);
    public int deleteTheseContacts(List<String> contactsTobeDelete,String parent_id);
    public ContactModel getContactOfUserByPhone(String parent_id, String phoneNumber,String deviceId);

}
