package ch.hevs.businessobject;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    private String isbn;

    //relation
    @ManyToMany(mappedBy = "Book", cascade = { CascadeType.PERSIST })
    private Set<Author> authors;

    //relation
    @ManyToMany(mappedBy = "Book", cascade = { CascadeType.PERSIST })
    private Set<Category> categories;

    private String title;
    private String language;
    private String description;
    private int numberOfPages;
    private boolean borrowed;


    public Book(){

    }


    public Book(Long id, String isbn, Set<Author> authors, Set<Category> categories,
                String title, String language, String description, int numberOfPages, boolean borrowed) {
        this.id = id;
        this.isbn = isbn;
        this.authors = authors;
        this.categories = categories;
        this.title = title;
        this.language = language;
        this.description = description;
        this.numberOfPages = numberOfPages;
        this.borrowed = borrowed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }
}
