package ch.hevs.businessobject;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Category")
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String genre;

    @ManyToMany
    private Set<Book> book;

    public Category(){

    }

    public Category(Long id, String genre, Set<Book> book) {
        this.id = id;
        this.genre = genre;
        this.book = book;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Set<Book> getBook() {
        return book;
    }

    public void setBook(Set<Book> book) {
        this.book = book;
    }

    /*
Fantasy.
Sci-Fi.
Mystery.
Thriller.
Romance.
Westerns.
Dystopian.
Contemporary
 */

}
