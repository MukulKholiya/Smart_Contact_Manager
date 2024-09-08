package com.scm.services.Implementations;

import com.scm.entities.Contact;
import com.scm.helper.ResourceNotFoundException;
import com.scm.repository.ContactRepository;
import com.scm.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    ContactRepository contactRepository;

    @Override
    public Contact save(Contact contact) {
        String contactId = UUID.randomUUID().toString();
        contact.setId(contactId);
        return contactRepository.save(contact);
    }

    @Override
    public Contact update(Contact contact) {
        return null;
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getContactById(String Id) {
        return contactRepository.findById(Id).orElseThrow(
                ()->new ResourceNotFoundException("Contact with this id does not exist"));

    }

    @Override
    public void delete(String Id) {
        contactRepository.deleteById(Id);
    }

    @Override
    public List<Contact> getByUserId(String Id) {
        return contactRepository.findByUserId(Id);
    }

    @Override
    public List<Contact> search(String Id, String email, String phoneNumber) {
        return List.of();
    }
}
