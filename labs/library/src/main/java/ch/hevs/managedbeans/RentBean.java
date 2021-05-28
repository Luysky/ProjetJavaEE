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




        //Test = goToTest();

    }




    public String goToTest(){
        return "Test";
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


	}

	public void showAllBooks() {
		books = library.getBooks();
    	//flights = travel.getFlights(travel.getDestinationByName(departureName), travel.getDestinationByName(arrivalName));
	}


	//Books
	public List<Book> getBooks(){
		return books;
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

	public String performBooking(int id) {

		try {


			//Flight selectedFlight = travel.getSelectedFlight(id);

			Book selectedBook = library.getOneBook(id);

			System.out.println("************************* "+selectedBook.getId() +"*****************************");

			// Transfer
			this.transactionResult = library.book(selectedBook);

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