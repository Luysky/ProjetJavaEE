package ch.hevs.businessobject;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idBook;


    private String isbn;



    //relation
    //@ManyToMany(mappedBy = "writtenBooks", cascade = { CascadeType.PERSIST })
    @ManyToMany
    @JoinTable(name = "Book_Writer",joinColumns = @JoinColumn(name = "idBook"),
                inverseJoinColumns = @JoinColumn(name = "idWriter"))
    //@JoinColumn(name = "writer_fk",nullable = false)
    private Set<Writer> writers;




    //relation
    @ManyToOne
    //@JoinColumn(name = "category_fk",nullable = false)
    private Category category;

    @ManyToOne
    //@JoinColumn(name = "member_fk",nullable = false)
    private Member borrower;

    private String title;
    private String language;
    private String description;
    private int numberOfPages;
    //private boolean borrowed;



    public Book(){

    }


    public Book(int idBook, String isbn,
                String title, String language, String description,
                int numberOfPages) {
        this.idBook = idBook;
        this.isbn = isbn;
        //this.writers = writers;
        //this.category = category;
        this.title = title;
        this.language = language;
        this.description = description;
        this.numberOfPages = numberOfPages;
        //this.borrower = borrower;
    }

    public int getId() {
        return idBook;
    }

    public void setId(int idBook) {
        this.idBook = idBook;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }



    public Set<Writer> getWriters() {
        return writers;
    }

    public void setWriters(Set<Writer> writers) {
        this.writers = writers;
    }



    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public Member getBorrower() {
        return borrower;
    }

    public void setBorrower(Member borrower) {
        this.borrower = borrower;
    }

}
