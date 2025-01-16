package com.caiojacintho.junit.test;

import com.caiojacintho.junit.dominio.Person;
import com.caiojacintho.junit.service.PersonService;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class PersonTest01 {
    public static void main(String[] args) {
        Person person = new Person(18);
        PersonService personService = new PersonService();
        log.info("Is Adult '{}'",personService.isAdult(person));
    }
}
