package com.vijacdblz.cms.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vijacdblz.cms.domain.Contact;
import com.vijacdblz.cms.service.ContactService;

@RestController
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/api/v1/contact")
    public ResponseEntity<List<Contact>> getContacts() {
        return ResponseEntity.ok(new ArrayList<>((Collection<? extends Contact>) contactService.findAll()));
    }
    
    @GetMapping("/api/v1/contact/{id}")
    public ResponseEntity<Object> getContact(@PathVariable Long id) {
        return ResponseEntity.ok( contactService.findById(id).orElse(null));
    }

}
