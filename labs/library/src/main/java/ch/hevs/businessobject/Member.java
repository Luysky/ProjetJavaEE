package ch.hevs.businessobject;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Member")
public class Member extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idMember;

    //private String firstname;
    //private String lastname;

    @Embedded
    private Address address;
    public String phoneNumber;
    public String email;

    @OneToMany(mappedBy = "borrower")
    //@OneToMany(mappedBy = "borrower")
    //@JoinColumn(name = "book_fk",nullable = false)
    private Set<Book> borrowedBook;


    public Member() {

    }


    public Member(String firstname, String lastname, int idMember,
                  String phoneNumber, String email) {
        super(firstname, lastname);
        this.idMember = idMember;
        //this.firstname = firstname;
        //this.lastname = lastname;
        //this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        //this.borrowedBook = borrowedBook;
    }

    public int getId() {
        return idMember;
    }

    public void setId(int id) {
        this.idMember = idMember;
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
