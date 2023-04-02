package com.vijacdblz.cms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
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
    public Iterable<Contact> findAll(Pageable pageable) {
        return contactRepository.findAll(pageable);
    }

    @Override
    public Optional<Contact> findById(Long id) {
        return contactRepository.findById(id);
        
    }

    @Override
    public List<Contact> findByType(String type , Pageable pageable) {
        return contactRepository.findByType(type , pageable);
    }

}
