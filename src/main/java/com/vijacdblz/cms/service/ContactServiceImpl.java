package com.vijacdblz.cms.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vijacdblz.cms.domain.Contact;
import com.vijacdblz.cms.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }

    @Override
    public Iterable<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Override
    public Optional<Contact> findById(Long id) {
        return contactRepository.findById(id);
        
    }
    
}
