package com.vijacdblz.cms.repository;

import com.vijacdblz.cms.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository  extends JpaRepository<Contact, Long> {
}
