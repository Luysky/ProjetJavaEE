package ch.hevs.businessobject;

import javax.persistence.*;

//@Entity
public class Person {

    //@Id
  //  @GeneratedValue (strategy = GenerationType.SEQUENCE)
//    protected long id;
    public String firstname;
    public String lastname;



    public Person(){

    }

    public Person(String firstname, String lastname) {
        //this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }
/*
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

 */

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
