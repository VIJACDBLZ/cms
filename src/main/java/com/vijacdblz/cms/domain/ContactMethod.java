package com.vijacdblz.cms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="contactmethod")
public class ContactMethod {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="contact_id", nullable=false)
    @JsonIgnore
    private Contact contact;
    private String cmtype;
    private String title;
    private String valueText;
    private boolean deleted = false;
    private Date createdDate = new Date();
    private String accountId;


    

}
