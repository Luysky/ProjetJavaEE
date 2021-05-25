package ch.hevs.businessobject;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;

import static javax.persistence.InheritanceType.JOINED;

@MappedSuperclass
public class Person {


    public String firstname;
    public String lastname;



    public Person(){

    }


    public Person(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }



    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

}
