package com.github.willschipp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.github.willschipp.model.Person;
import com.github.willschipp.model.PersonRepository;

@Profile("server")
@Service
public class DataInit {

    private static final Log logger = LogFactory.getLog(DataInit.class);
    
    @Value("${data.rowCount:1000}")
    int rowCount;

    @Autowired
    PersonRepository personRepository;

    public void generateAndPopulate() throws Exception {
        //generate records
        generateAndPopulate(rowCount);
    }

    public void generateAndPopulate(int rowCount) throws Exception {
        //generate records
        List<Person> persons = new ArrayList<>();
        //insert
        for (int i=0;i<rowCount;i++) {
            Person person = new Person();
            person.setFirstName("john" + i);
            person.setLastName("doe" + i);
            person.setLastUpdatedTimestamp(new Date());
            //add
            persons.add(person);
        } //end for
        //insert
        personRepository.saveAll(persons);
    }        

    public void purge() throws Exception {
        personRepository.deleteAllInBatch();
        logger.debug("deleted all records");
    }
}
