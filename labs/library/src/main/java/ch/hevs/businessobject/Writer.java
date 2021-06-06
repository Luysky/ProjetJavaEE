package ch.hevs.businessobject;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name="Writer")
public class Writer extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idWriter;

    //relation
    @OneToMany(mappedBy = "writer", cascade = { CascadeType.PERSIST } )
    private Set<Book> writtenBooks;

    private String biography;

    //constructors
    public Writer(){

    }

    public Writer(String firstname, String lastname, int idWriter,
                   String biography) {
        super(firstname, lastname);
        this.idWriter = idWriter;
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


