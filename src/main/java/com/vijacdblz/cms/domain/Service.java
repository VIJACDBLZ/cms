package com.vijacdblz.cms.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="service")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;
    private String description;
}
