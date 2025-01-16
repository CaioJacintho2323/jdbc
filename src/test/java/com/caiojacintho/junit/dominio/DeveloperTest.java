package com.caiojacintho.junit.dominio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeveloperTest {

    @Test
    public void instanceOf_ExcecutesChildClassMethod_WhenObjectIdOfChildType(){
//        Employee employeeDeveloper = new Developer("1", "Java");
        Employee employeeDeveloper = new Employee("1");
//        if(employeeDeveloper instanceof Employee){
//            Developer developer = (Developer) employeeDeveloper;
//            Assertions.assertEquals("Java", developer.getMainLanguage());
//        }
        if(employeeDeveloper instanceof Developer developer){
            Assertions.assertEquals("Java", developer.getMainLanguage());
        }
    }

}