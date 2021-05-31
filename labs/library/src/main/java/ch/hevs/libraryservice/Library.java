package ch.hevs.libraryservice;

import ch.hevs.businessobject.Book;
import ch.hevs.businessobject.Member;
import ch.hevs.businessobject.Writer;

import javax.ejb.Local;
import java.util.List;

@Local
public interface Library {

    public String book(Book oneBook, int idMember) throws Exception;

    public List<Book> getBooks();

    public List<Book> getNonBorrowedBooks();

    public Book getOneBook(int idBook);

    public List<Writer> getWriters();

    public Member getMember(int idMember);

    public List<Member> getAllMembers();

    public void populateDatabase();
}
