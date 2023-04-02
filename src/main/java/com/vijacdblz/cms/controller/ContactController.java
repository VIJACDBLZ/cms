package com.vijacdblz.cms.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vijacdblz.cms.domain.Contact;
import com.vijacdblz.cms.service.ContactService;

@RestController
@Tag(name="Contacts")
public class ContactController {

    private final ContactService contactService;


    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/api/v1/clinic")
    public ResponseEntity<List<Contact>> getClinicListing(@RequestParam int page , @RequestParam(required = false, defaultValue = "20") int size ) {
        return ResponseEntity.ok(new ArrayList<>((Collection<? extends Contact>) contactService.findByType("clinic", PageRequest.of(page, size))));
    }

    @GetMapping("/api/v1/doctors")
    public ResponseEntity<List<Contact>> getDoctorsListing(@RequestParam int page , @RequestParam(required = false, defaultValue = "20") int size ) {
        return ResponseEntity.ok(new ArrayList<>((Collection<? extends Contact>) contactService.findByType("doctor", PageRequest.of(page, size))));
    }
    
    @GetMapping("/api/v1/contact/{id}")
    public ResponseEntity<Object> getContact(@PathVariable Long id) {
        Optional<Contact> contact = contactService.findById(id);

        if(contact.isPresent())
            return ResponseEntity.ok().body(contact.get());
        else
            return ResponseEntity.notFound().build();
    }


    @GetMapping("/api/v1/contact/{type}/{page}")
    public List<Contact> getProductsByName(@PathVariable String type,
                                           @PathVariable int page,
                                           @RequestParam(required = false, defaultValue = "20") int size) {
//        return productRepository.findProductsByName(name, Sort.by("id").descending());

        return contactService.findByType(type, PageRequest.of(page, size));
    }

}
