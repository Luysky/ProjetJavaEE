package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.libraryservice.Library;
import ch.hevs.businessobject.*;

/**
 * TransferBean.java
 * 
 */

public class RentBean
{

    private List<Address> addresses;
    private List<Book> books;
    private Book book;
    private List<Calendar> categories;
    private List<Member> members;
    private List<Writer> writers;

	private Library library;

	private String Test;
	private String transactionResult;

	private int currentMember;


	private String message;
    
    @PostConstruct
    public void initialize() throws NamingException {
    	
    	// use JNDI to inject reference to bank EJB
    	InitialContext ctx = new InitialContext();

    	//Renommer nom du chemin d acces snap
		library = (Library) ctx.lookup("java:global/TP12-WEB-EJB-PC-EPC-E-0.0.1-SNAPSHOT/LibraryBean!ch.hevs.libraryservice.Library");

		//bank = (Bank) ctx.lookup("java:global/TP12-WEB-EJB-PC-EPC-E-0.0.1-SNAPSHOT/BankBean!ch.hevs.bankservice.Bank");

		message = "Click to populate DB ! One time only !";


		//populateDatabase();

		writers = new ArrayList<>();
		//writers = library.getWriters();

		books = new ArrayList<Book>();
		//books = library.getBooks();

		writers = library.getWriters();
		books = library.getBooks();
		members = library.getAllMembers();
		currentMember = 1;

		performUpdateBookList();



    }



	// transactionResult
	public String getTransactionResult () {
		return transactionResult;
	}

	public void setTransactionResult(String transactionResult) {
		this.transactionResult = transactionResult;
	}


    public void populateDatabase(){

    	library.populateDatabase();

		writers = library.getWriters();
		books = library.getBooks();
		members = library.getAllMembers();
		currentMember = 1;

	}


	public void showAllBooks() {
		books = library.getBooks();
    	//flights = travel.getFlights(travel.getDestinationByName(departureName), travel.getDestinationByName(arrivalName));
	}


	//Books
	public List<Book> getBooks(){
		return books;
	}

	public List<Member>getMembers(){
    	return members;
	}


	public List<Book> getUpdatedBooks(){

    	books = library.getNonBorrowedBooks();

    	return books;
	}


	public String performUpdateBookList() {

		try {


			getUpdatedBooks();


		} catch (Exception e) {
			e.printStackTrace();
		}

		return "bookForm"; //  the String value returned represents the outcome used by the navigation handler to determine what page to display next.

	}


	//Books
	public List<Writer> getWriters(){
		return writers;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	public String performMember(int idMember) {

		try {

				currentMember=idMember;


			} catch (Exception e) {
			e.printStackTrace();
		}

		return "bookForm"; //  the String value returned represents the outcome used by the navigation handler to determine what page to display next.

	}


	public String performBooking(int idBook) {

		try {


			Book selectedBook = library.getOneBook(idBook);

			//TODO changer selectedBook + idMember
			this.transactionResult = library.book(selectedBook,currentMember);

			// History
			/*
			try {
				history = travel.getPassengerHistory();
			}catch(Exception e) {
				System.out.println("There is no history yet");
			}

			 */

			// Bookings
			/*
			try {
				bookings = travel.getBookings();
			}catch(Exception e) {
				System.out.println("There is no bookings yet");
			}

			 */

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "confirmationForm"; //  the String value returned represents the outcome used by the navigation handler to determine what page to display next.

    }

}