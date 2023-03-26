package com.vijacdblz.cms.controller;

import com.vijacdblz.cms.repository.ContactRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {
    private final ContactRepository contactRepository;

    public ContactController(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }


}
