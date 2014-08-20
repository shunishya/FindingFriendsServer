/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.findingfriends.daoimpl;

import com.findingfriends.SQLUtility.SQLUtility;
import com.findingfriends.dao.ContactsDao;
import com.findingfriends.model.Contact;
import com.findingfriends.model.ContactModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nitu
 */
public class ContactDaoimplementation implements ContactsDao {

    Connection con = null;
    ResultSet rs = null;
    Statement stmt = null;

    @Override
    public boolean addContacts(List<Contact> contacts, String user_id) {
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = new Contact();
            contact = contacts.get(i);
            String sql = "INSERT INTO contacts(`parent_id`,`phonenumber`,`user_id`,`name`) VALUES('" + contact.getParent_id() + "','" + contact.getPhoneNumber() + "','" + contact.getUser_id() + "','" + contact.getName() + "');";
            try {
                con = SQLUtility.getConnection();
                stmt = con.createStatement();
                int a = stmt.executeUpdate(sql);
                if (a < 0) {
                    //con.close();
                    return false;
                }

            } catch (InstantiationException ex) {
                Logger.getLogger(ContactDaoimplementation.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ContactDaoimplementation.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ContactDaoimplementation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    @Override
    public List<ContactModel> displayAllContactOfUser(String user_id) {
        List<ContactModel> contactList = new ArrayList<ContactModel>();

        String sql = "SELECT * from contacts WHERE `parent_id`= '" + user_id + "';";
        try {
            con = SQLUtility.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ContactModel contact = new ContactModel();
                String parent_id = rs.getString("parent_id");
                String phoneNumber = rs.getString("phonenumber");
                String userId = rs.getString("user_id");
                String name = rs.getString("name");
                contact.setName(name);
                contact.setPhonenumber(phoneNumber);
                contact.setUser_id(userId);

                contactList.add(contact);
            }
        } catch (InstantiationException ex) {
            Logger.getLogger(UserDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(UserDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contactList;
    }

    @Override
    public int deleteTheseContacts(List<String> contactsTobeDelete, String parent_id) {
        for (String user_id : contactsTobeDelete) {
            String sql = "DELETE from contacts WHERE `user_id`= '" + user_id + "' AND `parent_id`='" + parent_id + "';";
            try {
                con = SQLUtility.getConnection();
                stmt = con.createStatement();
                int a = stmt.executeUpdate(sql);
                if (a < 0) {
                    return -1;
                }
            } catch (InstantiationException ex) {
                Logger.getLogger(ContactDaoimplementation.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ContactDaoimplementation.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ContactDaoimplementation.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return contactsTobeDelete.size();
    }

    @Override
    public ContactModel getContactOfUserByPhone(String parent_id, String phoneNumber) {
        String sql = "SELECT * from contacts WHERE `parent_id`= '" + parent_id + "' AND `phonenumber`='" + phoneNumber + "';";
        ContactModel contact = null;
        try {
            con = SQLUtility.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String userName = rs.getString("name");
                String phoneNumbers = rs.getString("phonenumber");
                String userId = rs.getString("user_id");

                contact = new ContactModel();
                contact.setName(userName);
                contact.setPhonenumber(phoneNumber);
                contact.setUser_id(userId);

            }
        } catch (InstantiationException ex) {
            Logger.getLogger(UserDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(UserDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contact;
    }

}
