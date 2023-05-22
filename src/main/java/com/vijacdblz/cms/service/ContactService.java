package com.vijacdblz.cms.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.vijacdblz.cms.domain.Contact;
import com.vijacdblz.cms.domain.Type;
import org.springframework.data.domain.Pageable;

public interface ContactService {

    Iterable<Contact> findAll(Pageable pageable);

    Optional<Contact> findById(Long id);


    List<Contact> findByType(Type type , Pageable pageable);


    List<Contact> findAll(String type, Map<String, String> requestMap);

    Contact save(Contact contact);
}
