package ch.hevs.businessobject;

import javax.persistence.*;


@Entity
@Table(name="Book")
public class Book {

    @Id
    @SequenceGenerator(name= "Book_sequence", sequenceName = "Book_sequence_ID", initialValue=1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "Book_sequence")
    private int idBook;

    private String isbn;

    //relation
    @ManyToOne
    private Writer writer;

    //relation
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Category category;

    //relation
    @ManyToOne(cascade = {CascadeType.REMOVE})
    private Member borrower;

    private String title;
    private String language;
    private String description;
    private int numberOfPages;


    //constructors
    public Book(){

    }


    public Book(String isbn,
                String title, String language, String description,
                int numberOfPages, Member borrower) {
        this.isbn = isbn;
        this.title = title;
        this.language = language;
        this.description = description;
        this.numberOfPages = numberOfPages;
        this.borrower = borrower;
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


    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
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
