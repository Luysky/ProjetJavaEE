package ch.hevs.managedbeans;

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
    private List<Calendar> categories;
    private List<Member> members;
    private List<Writer> writers;

	private Library library;

	private String Test;



	private String message;
    
    @PostConstruct
    public void initialize() throws NamingException {
    	
    	// use JNDI to inject reference to bank EJB
    	InitialContext ctx = new InitialContext();

    	//Renommer nom du chemin d acces snap
		library = (Library) ctx.lookup("java:global/TP12-WEB-EJB-PC-EPC-E-0.0.1-SNAPSHOT/LibraryBean!ch.hevs.libraryservice.Library");

		//bank = (Bank) ctx.lookup("java:global/TP12-WEB-EJB-PC-EPC-E-0.0.1-SNAPSHOT/BankBean!ch.hevs.bankservice.Bank");

		message = "Click to populate DB ! One time only !";




		//books = library.getBooks();
        //Test = goToTest();

    }




    public String goToTest(){
        return "Test";
    }


    public void populateDatabase(){

    	library.populateDatabase();

	}

	public void showAllBooks() {
		books = library.getBooks();
    	//flights = travel.getFlights(travel.getDestinationByName(departureName), travel.getDestinationByName(arrivalName));
	}


	//Books
	public List<Book> getBooks(){
		return books;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}