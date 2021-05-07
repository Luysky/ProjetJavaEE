package ch.hevs.businessobject;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.Set;

@Embeddable
public class Address {

    private String postalCode;
    private String street;
    private String number;
    private String city;

    // postalCode
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String codePostal) {
        this.postalCode = codePostal;
    }

    // street
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    // city
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    // constructors
    public Address() {
    }

    public Address(String postalCode, String street, String number, String city) {
        this.postalCode = postalCode;
        this.street = street;
        this.number = number;
        this.city = city;
    }
}
