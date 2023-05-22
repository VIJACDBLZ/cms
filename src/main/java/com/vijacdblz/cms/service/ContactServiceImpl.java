package com.vijacdblz.cms.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.vijacdblz.cms.domain.Type;
import com.vijacdblz.cms.util.Utility;
import io.micrometer.common.util.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vijacdblz.cms.domain.Contact;
import com.vijacdblz.cms.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    private final Utility utility;

    public ContactServiceImpl(ContactRepository contactRepository, Utility utility){
        this.contactRepository = contactRepository;
        this.utility = utility;
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
    public List<Contact> findByType(Type type , Pageable pageable) {
        return contactRepository.findByType(type , pageable);
    }

    @Override
    public List<Contact> findAll(String type, Map<String, String> requestMap) {
        return null;
    }

    @Override
    public Contact save(Contact contact) {

        //TODO:requires improvement

        try {
            if (contact.getLatitude() == 0 && contact.getLongitude() == 0 && contact.getZip() > 0) {
                String[] latlong = utility.getLatlong(contact.getZip()).split(",");
                contact.setLatitude(Double.parseDouble(latlong[0]));
                contact.setLongitude(Double.parseDouble(latlong[1]));
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }


        return contactRepository.save(contact);
    }

}
