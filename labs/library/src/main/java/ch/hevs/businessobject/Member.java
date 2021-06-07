package ch.hevs.businessobject;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="Member")
public class Member extends Person {

    @Id
    @SequenceGenerator(name= "Member_sequence", sequenceName = "Member_sequence_ID", initialValue=1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "Member_sequence")
    private int idMember;


    @Embedded
    private Address address;
    public String phoneNumber;
    public String email;


    //relation
    @OneToMany(mappedBy = "borrower")
    private Set<Book> borrowedBook;


    //constructors
    public Member() {

    }

    public Member(String firstname, String lastname,
                  String phoneNumber, String email,Set<Book> borrowedBook) {
        super(firstname, lastname);
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.borrowedBook = borrowedBook;
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
