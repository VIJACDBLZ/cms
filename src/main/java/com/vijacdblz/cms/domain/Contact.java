package com.vijacdblz.cms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vijacdblz.cms.util.Utility;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.*;
import java.util.List;

@Data
@Entity
@Table(name="contact")
public class Contact {
    @Id
    private String id = UUID.randomUUID().toString();
    private String firstName;
    private String lastName;
    private String fullName;
    @Enumerated(EnumType.ORDINAL)
    private Type type;
    private String description;
    private String accountId;
    private String address;
    private String city;
    private String state;
    private int zip;
    private double latitude;
    private double longitude;
    private boolean deleted = false;
    private boolean hasPromotion = false;
    @Enumerated(EnumType.ORDINAL)
    private Affordability pricing;
    private int rating; //out of 5
    @ManyToMany
    @JoinTable(
            name = "clinic_service",
            joinColumns = @JoinColumn(name = "contact_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"))
    private Set<Service> servicesProvided = new HashSet<>();
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="clinic_doctor", joinColumns = {@JoinColumn(name="clinic_id")}, inverseJoinColumns = {@JoinColumn(name="doctor_id")})
    @Fetch(FetchMode.SELECT)
    private Set<Contact> associatedDoctors = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "contact_id")
    private Set<ContactMethod> associatedContactMethods = new HashSet<>();

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "contact")
    private Set<OpeningHour> openingHours = new HashSet<>();

    @CreatedDate
    private LocalDateTime created;
    @LastModifiedDate
    private LocalDateTime lastModified;


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

    @PrePersist
    public void prePersist() {
        created = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        lastModified = LocalDateTime.now();
    }
}
