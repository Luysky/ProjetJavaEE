package ch.hevs.businessobject;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Category")
public class Category {


    @Id
    @SequenceGenerator(name= "Category_sequence", sequenceName = "Category_sequence_ID", initialValue=1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "Category_sequence")
    private int idCategory;

    private String genre;

    //relation
    @OneToMany(mappedBy = "category", cascade = { CascadeType.PERSIST })
    private Set<Book> book;

    //constructors
    public Category(){

    }

    public Category(String genre) {
        this.genre = genre;
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


}
