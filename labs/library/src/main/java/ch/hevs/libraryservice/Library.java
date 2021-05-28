package ch.hevs.libraryservice;

import ch.hevs.businessobject.Book;
import ch.hevs.businessobject.Writer;

import javax.ejb.Local;
import java.util.List;

@Local
public interface Library {

    public String book(Book oneBook) throws Exception;

    public List<Book> getBooks();

    public Book getOneBook(int idBook);

    public List<Writer> getWriters();

    public void populateDatabase();
}
