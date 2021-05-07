package ch.hevs.businessobject;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Member")
public class Member extends Person {

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
private Long id;

@Embedded
private Address address;
public String phoneNumber;
public String email;

@OneToMany
private Set<Book> borrowedBook;


    public Member() {

    }


    public Member(String firstname, String lastname, Long id, Address address,
                  String phoneNumber, String email, Set<Book> borrowedBook) {
        super(firstname, lastname);
        this.id = id;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.borrowedBook = borrowedBook;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Book> getBorrowedBook() {
        return borrowedBook;
    }

    public void setBorrowedBook(Set<Book> borrowedBook) {
        this.borrowedBook = borrowedBook;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
