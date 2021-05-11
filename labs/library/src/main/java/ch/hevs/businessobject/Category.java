package ch.hevs.businessobject;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Category")
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idCategory;

    private String genre;

    @OneToMany(mappedBy = "category", cascade = { CascadeType.PERSIST })
    //@JoinColumn(name = "book_fk",nullable = false)
    private Set<Book> book;

    public Category(){

    }

    public Category(int idCategory, String genre) {
        this.idCategory = idCategory;
        this.genre = genre;
        //this.book = book;
    }

    public int getId() {
        return idCategory;
    }

    public void setId(int idCategory) {
        this.idCategory = idCategory;
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
