package com.vijacdblz.cms.repository;

import com.vijacdblz.cms.domain.Contact;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepository  extends PagingAndSortingRepository<Contact, Long> {
    List<Contact> findByType(String type , Pageable pageable );
    Optional<Contact> findById(Long id);
    Contact save(Contact contact);
}
