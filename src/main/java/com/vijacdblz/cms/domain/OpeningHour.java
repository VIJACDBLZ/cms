package com.vijacdblz.cms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name="opening_hour")
public class OpeningHour {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="contact_id", nullable=false)
    @JsonIgnore
    private Contact contact;
    private int dayOfWeek;
    private LocalDate openingTime;
    private LocalDate closingTime;

}
