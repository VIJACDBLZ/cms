package com.vijacdblz.cms.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Date;
import java.util.*;
import java.util.List;

@Data
@Entity
@Table(name="contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String type;
    private String description;
    private String accountId;
    private String address1;
    private String address2;
    private String address3;
    private String city;
    private String state;
    private String zip;
    private double latitude;
    private double longitude;

    private boolean deleted = false;
    private Date createdDate = new Date();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="clinic_doctor", joinColumns = {@JoinColumn(name="clinic_id")}, inverseJoinColumns = {@JoinColumn(name="doctor_id")})
    @Fetch(FetchMode.SELECT)
    private Set<Contact> doctors = new HashSet<>();

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "contact")
    private Set<ContactMethod> associatedContactMethods = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact contact)) return false;

        return getId() != null ? getId().equals(contact.getId()) : contact.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
