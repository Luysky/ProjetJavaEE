package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.libraryservice.Library;
import ch.hevs.businessobject.*;



public class RentBean
{
	private Library library;

    private List<Book> books;
    private List<Member> members;
    private List<Writer> writers;

	private String transactionResult;
	private int currentMember;
	private String message;
    
    @PostConstruct
    public void initialize() throws NamingException {
    	
    	// use JNDI to inject reference to bank EJB
    	InitialContext ctx = new InitialContext();

    	//JNDI
		library = (Library) ctx.lookup("java:global/TP12-WEB-EJB-PC-EPC-E-0.0.1-SNAPSHOT/LibraryBean!ch.hevs.libraryservice.Library");


		message = "Click to populate DB ! One time only !";


		//instanciate the variables of writers and books
		writers = new ArrayList<>();
		books = new ArrayList<Book>();

		//get the infos from the service layer for writers, books and members
		writers = library.getWriters();
		books = library.getBooks();
		members = library.getAllMembers();

		//set the current member by default to 1
		currentMember = 1;

		//refresh the book list
		performUpdateBookList();


    }



	// transactionResult
	public String getTransactionResult () {
		return transactionResult;
	}
	public void setTransactionResult(String transactionResult) {
		this.transactionResult = transactionResult;
	}


	//method to populate the db
    public void populateDatabase(){

    	library.populateDatabase();

		writers = library.getWriters();
		books = library.getBooks();
		members = library.getAllMembers();
		currentMember = 1;
	}


	//Books
	public List<Book> getBooks(){
		return books;
	}
	public List<Member>getMembers(){
    	return members;
	}


	//method to retrieve every non borrowed books
	public List<Book> getUpdatedBooks(){
    	books = library.getNonBorrowedBooks();
    	return books;
	}

	//method use to update the books list after the booking of a book
	//the book is not available anymore
	public String performUpdateBookList() {
		try {
			getUpdatedBooks();

			} catch (Exception e) {
				e.printStackTrace();
			}
		return "bookForm";
	}

	//method used to set the new current member
	public String performCurrentMember(int idMember) {
		try {
				currentMember=idMember;
			} catch (Exception e) {
			e.printStackTrace();
		}
		return "bookForm";
	}

	//method used to borrow a book
	public String performBooking(int idBook) {

		try {
			Book selectedBook = library.getOneBook(idBook);
			this.transactionResult = library.book(selectedBook,currentMember);

			//update the book list
			performUpdateBookList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "confirmationForm";
    }


    //method used to delete a member
	public String performDelete(int idMember) {

		try {
			currentMember = idMember;

			Member selectedMember = library.getMember(currentMember);
			this.transactionResult = library.member(selectedMember);

			//when a member is deleted all his books are available again
			//update of the available books
			books = getUpdatedBooks();

			//update of the available members
			members = library.getAllMembers();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "confirmationForm";

	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}


}