package com.vijacdblz.cms.bootstrap;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.vijacdblz.cms.domain.Affordability;
import com.vijacdblz.cms.domain.Contact;
import com.vijacdblz.cms.domain.ContactMethod;
import com.vijacdblz.cms.domain.Type;
import com.vijacdblz.cms.repository.ContactRepository;
import com.vijacdblz.cms.service.ContactService;
import com.vijacdblz.cms.util.Utility;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Component
public class BootstrapData implements CommandLineRunner {
    private final ContactRepository contactRepository;
    private final ContactService contactService;

    private final Utility utility;

    public BootstrapData(ContactRepository contactRepository, Utility utility, ContactService contactService){
        this.contactRepository = contactRepository;
        this.utility = utility;
        this.contactService = contactService;
    }

    @Override
    public void run(String... args) throws IOException {
        try {

            File file = new File("/home/ec2-user/clinic.csv");

            CsvMapper mapper = new CsvMapper();
            CsvSchema schema = CsvSchema.emptySchema().withHeader();
            MappingIterator<Map<String, String>> iterator = mapper.reader(Map.class)
                    .with(schema)
                    .readValues(file);

            while (iterator.hasNext()) {

                Map<String, String> clinicData = iterator.next();
                System.out.println(new ObjectMapper().writeValueAsString(clinicData));

                Contact clinic = new Contact();
                clinic.setId(UUID.randomUUID().toString());
                clinic.setFullName(clinicData.get("FULLNAME"));
                clinic.setType(Type.CLINIC);
                clinic.setAddress(clinicData.get("ADDRESS"));
                clinic.setCity(clinicData.get("CITY"));
                clinic.setState(clinicData.get("STATE"));


                if (utility.isStringInteger(clinicData.get("ZIPCODE")))
                    clinic.setZip(Integer.parseInt(clinicData.get("ZIPCODE")));

                if ("TRUE".equalsIgnoreCase(clinicData.get("HAS_PROMOTION")))
                    clinic.setHasPromotion(true);
                else
                    clinic.setHasPromotion(false);

                if (StringUtils.isNotBlank(clinicData.get("PHONE"))) {

                    ContactMethod cmPhone = new ContactMethod();
                    cmPhone.setTitle("Phone");
                    cmPhone.setType("PHONE");
                    cmPhone.setValueText(clinicData.get("PHONE"));


                    clinic.setAssociatedContactMethods(new HashSet(Arrays.asList(cmPhone)));
                    cmPhone.setContact(clinic);

                }

                contactService.save(clinic);

            }


        }catch (Exception e){
            System.out.println("Exception occurred while loading CSV data");
        }


    }
}
