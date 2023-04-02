package com.vijacdblz.cms.service;

import java.util.List;
import java.util.Optional;

import com.vijacdblz.cms.domain.Contact;
import org.springframework.data.domain.Pageable;

public interface ContactService {

    Iterable<Contact> findAll(Pageable pageable);

    Optional<Contact> findById(Long id);


    List<Contact> findByType(String type , Pageable pageable);

    
}
