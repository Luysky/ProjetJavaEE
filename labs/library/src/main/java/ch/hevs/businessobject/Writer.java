package ch.hevs.businessobject;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name="Writer")
public class Writer extends Person {

    @Id
    @SequenceGenerator(name= "Writer_sequence", sequenceName = "Writer_sequence_ID", initialValue=1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "Writer_sequence")
    private int idWriter;

    //relation
    @OneToMany(mappedBy = "writer", cascade = { CascadeType.PERSIST } )
    private Set<Book> writtenBooks;

    private String biography;

    //constructors
    public Writer(){

    }

    public Writer(String firstname, String lastname,String biography) {
        super(firstname, lastname);
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


