package com.vijacdblz.cms.repository;

import com.vijacdblz.cms.domain.Contact;
import com.vijacdblz.cms.domain.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ContactRepository  extends PagingAndSortingRepository<Contact, Long> {
    List<Contact> findByType(Type type , Pageable pageable );
    Optional<Contact> findById(Long id);
    Contact save(Contact contact);
}
