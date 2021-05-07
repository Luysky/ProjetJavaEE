package ch.hevs.businessobject;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Author")
public class Author extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToMany(mappedBy = "book", cascade = { CascadeType.PERSIST })
    private Set<Book> writtenBooks;

    private String biography;

    public Author(){

    }


    public Author(String firstname, String lastname, Long id,
                  Set<Book> writtenBooks, String biography) {
        super(firstname, lastname);
        this.id = id;
        this.writtenBooks = writtenBooks;
        this.biography = biography;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


