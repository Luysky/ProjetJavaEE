package ch.hevs.businessobject;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name="Writer")
public class Writer extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idWriter;



    //@ManyToMany(mappedBy = "writers", cascade = { CascadeType.PERSIST })
    @ManyToMany(mappedBy = "writers", cascade = { CascadeType.PERSIST })
    //@JoinTable(name = "Book_Writer",joinColumns = @JoinColumn(name = "idWriter"),
    //        inverseJoinColumns = @JoinColumn(name = "idBook"))
    private Set<Book> writtenBooks;




    private String biography;

    public Writer(){

    }


    public Writer(String firstname, String lastname, int idWriter,
                   String biography) {
        //super(firstname, lastname);
        this.idWriter = idWriter;
        //this.writtenBooks = writtenBooks;
        this.biography = biography;
    }

    public int getId() {
        return idWriter;
    }

    public void setId(int id) {
        this.idWriter = idWriter;
    }


    public Set<Book> getWrittenBooks() {
        return writtenBooks;
    }




    public void setWrittenBooks(Set<Book> writtenBooks) {
        this.writtenBooks = writtenBooks;
    }



    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}


