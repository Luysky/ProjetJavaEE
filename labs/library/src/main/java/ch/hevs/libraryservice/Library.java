package ch.hevs.libraryservice;

import ch.hevs.businessobject.Book;

import javax.ejb.Local;
import java.util.List;

@Local
public interface Library {

    public List<Book> getBooks();

    public void populateDatabase();
}
