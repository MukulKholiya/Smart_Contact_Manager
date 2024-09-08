package com.scm.services;

import com.scm.entities.Contact;


import java.util.List;


public interface ContactService {
    Contact save(Contact contact);
    Contact update(Contact contact);
    List<Contact> getAllContacts();
    Contact getContactById(String Id);
    void delete(String Id);
    List<Contact> getByUserId(String Id);
    List<Contact> search(String Id,String email,String phoneNumber );

}
