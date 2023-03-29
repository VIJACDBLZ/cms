package com.vijacdblz.cms.service;

import java.util.Optional;

import com.vijacdblz.cms.domain.Contact;

public interface ContactService {

    Iterable<Contact> findAll();

    Optional<Contact> findById(Long id);

    
}
