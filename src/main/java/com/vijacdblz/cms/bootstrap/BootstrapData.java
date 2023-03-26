package com.vijacdblz.cms.bootstrap;

import com.vijacdblz.cms.domain.Contact;
import com.vijacdblz.cms.domain.ContactMethod;
import com.vijacdblz.cms.repository.ContactRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class BootstrapData implements CommandLineRunner {
    private final ContactRepository contactRepository;

    public BootstrapData(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }

    @Override
    public void run(String... args){

        //Creating clinic
        Contact clinic = new Contact();
        clinic.setFullName("Dog Clinic 1");
        clinic.setType("clinic");
        ContactMethod contactMethod2 = new ContactMethod();
        contactMethod2.setType("email");
        contactMethod2.setTitle("email");
        contactMethod2.setValueText("DogClinic1@gmail.com");
        contactMethod2.setContact(clinic);

        clinic.getAssociatedContactMethods().add(contactMethod2);

        //saving clinic

        Contact clinicSaved = contactRepository.save(clinic);

        //Creating doctor

        Contact doctor = new Contact();
        doctor.setFirstName("Vijayasundaram");
        doctor.setLastName("K");
        doctor.setType("doctor");
        doctor.setAccountId("TEST");

        ContactMethod contactMethod = new ContactMethod();
        contactMethod.setType("email");
        contactMethod.setTitle("email");
        contactMethod.setValueText("vijacdblz@gmail.com");
        contactMethod.setContact(doctor);

        doctor.getAssociatedContactMethods().add(contactMethod);

        Contact doctorSaved = contactRepository.save(doctor);

        //saving doctor
        clinicSaved.getDoctors().add(doctorSaved);

        //adding doctor to the clinic
        Contact clinicUpdated = contactRepository.save(clinicSaved);



    }
}
