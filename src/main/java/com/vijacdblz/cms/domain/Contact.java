package com.vijacdblz.cms.domain;

import lombok.Data;

import java.util.List;

@Data
public class Contact {
    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String type;
    private String description;

    private List<ContactMethod> associatedContactMethods;
}
