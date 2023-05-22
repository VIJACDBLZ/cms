package com.vijacdblz.cms.controller;

import java.util.*;

import com.vijacdblz.cms.domain.Type;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(new ArrayList<>((Collection<? extends Contact>) contactService.findByType(Type.CLINIC, PageRequest.of(page, size))));
    }

    @GetMapping("/api/v1/doctors")
    public ResponseEntity<List<Contact>> getDoctorsListing(@RequestParam int page , @RequestParam(required = false, defaultValue = "20") int size ) {
        return ResponseEntity.ok(new ArrayList<>((Collection<? extends Contact>) contactService.findByType(Type.DOCTOR, PageRequest.of(page, size))));
    }
    
    @GetMapping("/api/v1/contact/{id}")
    public ResponseEntity<Object> getContact(@PathVariable Long id) {
        Optional<Contact> contact = contactService.findById(id);

        if(contact.isPresent())
            return ResponseEntity.ok().body(contact.get());
        else
            return ResponseEntity.notFound().build();
    }


    @GetMapping("/api/v1/contact/search/{type}")
    public List<Contact> searchContacts(@PathVariable String type,
                                           @RequestParam(value="test") Map<String, String> requestMap) {

        return contactService.findAll(type, requestMap);
    }

    @PostMapping("/api/v1/contact")
    public Contact createContact(@RequestBody Contact contact){

        return contactService.save(contact);
    }

}
