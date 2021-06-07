package ch.hevs.libraryservice;

import ch.hevs.businessobject.*;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;


@Stateless
public class LibraryBean implements Library {

    //interface used to interact with the persistence context
    @PersistenceContext(name = "libraryPU", type= PersistenceContextType.TRANSACTION)
    private EntityManager em;

    //declaration of the session context
    @Resource
    private SessionContext ctx;

    //return all books
    @Override
    public List<Book> getBooks() {
        Query query = em.createQuery("FROM Book");
        return (List<Book>) query.getResultList();
    }

    //return all non borrowed books
    @Override
    public List<Book> getNonBorrowedBooks(){
        Query query = em.createQuery("FROM Book b WHERE b.borrower = null");
        return (List<Book>) query.getResultList();
    }


    //return only one book
    @Override
    public Book getOneBook(int idBook) {
        Query query = em.createQuery("FROM Book b WHERE b.idBook = :idBook");
        query.setParameter("idBook", idBook);
        return (Book) query.getSingleResult();
    }

    @Override
    public List<Book> getMultipleBooks(Member borrower) {
        Query query = em.createQuery("FROM Book b WHERE b.borrower = :borrower");
        query.setParameter("borrower", borrower);
        return (List<Book>) query.getResultList();
    }

    //return only one member
    public Member getMember(int idMember){

        Query query = em.createQuery("FROM Member m WHERE m.idMember = :idMember");
        query.setParameter("idMember", idMember);
        return (Member) query.getSingleResult();
    }

    //return all members
    public List<Member> getAllMembers(){

        Query query = em.createQuery("FROM Member ");
        return (List<Member>) query.getResultList();
    }

    //find the number of time a member has borrowed a book
    public long getNumberBorrowedBooks(int idMember){

        Query query = em.createQuery( "SELECT count (o) FROM Book b, IN (b.borrower) " +
                "o WHERE b.borrower.idMember = :idMember");
        query.setParameter("idMember", idMember);
        return (long) query.getSingleResult();
    }

    //method use to book
    @Override
    public String book(Book oneBook, int currentMember) throws Exception {

        String transactionResult="Error";

        EntityTransaction tx = null;
        try {

            EntityManagerFactory emf = Persistence
                    .createEntityManagerFactory("libraryPU");
            EntityManager em = emf.createEntityManager();
            tx = em.getTransaction();


            tx.begin();

            //retrieve the id of the selected member
            Member memberTest = getMember(currentMember);
            int idMember = memberTest.getId();

            //retrieve the member in the db
            Member member = em.find(Member.class,idMember);

            //retrieve the id of th selected book
            int idBook = oneBook.getId();

            //retrieve the book in the db
            Book book = em.find(Book.class,idBook);

            //set the borrower member
            book.setBorrower(member);
            em.merge(book);


            long number = getNumberBorrowedBooks(idMember);


            if(number >= 2) {
                transactionResult="Le membre a déjà loué deux livres !";
                ctx.setRollbackOnly();
                return transactionResult;
            }


            tx.commit();


        } catch (Exception e) {
            e.printStackTrace();
        }

        transactionResult = "Le livre a bien été loué !";

        return transactionResult;

    }

    //method used to delete a member
    @Override
    public String member(Member member) throws Exception {

        EntityTransaction tx = null;
        try {
            EntityManagerFactory emf = Persistence
                    .createEntityManagerFactory("libraryPU");
            EntityManager em = emf.createEntityManager();
            tx = em.getTransaction();

            tx.begin();

            //retrieve the id of the selected member
            int idMember = member.getId();

            //retrieve the selected member from the db
            Member memberToDelete = em.find(Member.class,idMember);


            //Private joke about our despair with this last point during the development...
            //cthulhu enable us to stock all the books with the same idMember
            List<Book>cthulhu = getMultipleBooks(member);

            //If the collection is not null we reset the borrower column for each book
            if(cthulhu.size()!=0){
                for (Book b:cthulhu
                ) {
                    Book savedBook = b;
                    savedBook.setBorrower(null);
                    em.merge(savedBook);
                }

            }


            //delete the member from the db
            em.remove(memberToDelete);


            tx.commit();



        } catch (Exception e) {
            e.printStackTrace();
        }

        String transactionResult="Le membre a bien été supprimé de la base de donnée !";

        return transactionResult;

    }


    //retrieve all the Writers
    public List<Writer> getWriters() {
        Query query = em.createQuery("FROM Writer");
        return (List<Writer>) query.getResultList();
    }


    //populate the database
    @Override
    public void populateDatabase() {

        EntityTransaction tx = null;
        try {

            EntityManagerFactory emf = Persistence
                    .createEntityManagerFactory("libraryPU");
            EntityManager em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            Member member1 = new Member("Kevin", "Coppey",
                    2,"0798543125", "kevin@yahoo.it",null);
            em.persist(member1);
            Address address1 = new Address("1950", "Place des Rats", "4", "Sion");
            member1.setAddress(address1);
            em.persist(member1);

            Member member2 = new Member("Thomas", "Luyet",
                    3,"0798483545", "thomas@hes.ch",null);
            em.persist(member2);
            Address address2 = new Address("1950", "Chemin du Désespoir", "8", "Sion");
            member2.setAddress(address2);
            em.persist(member2);

            Member member3 = new Member("Georges", "Sand",
                    1,"0798252483", "sand@sandy.com",null);
            em.persist(member3);
            Address address3 = new Address("1950", "Place des Potences", "13", "Sion");
            member3.setAddress(address3);
            em.persist(member3);


            Category category1 = new Category(10,"Horreur");
            em.persist(category1);


            Category category2 = new Category(20,"Romance");
            em.persist(category2);


            Writer writer1 = new Writer("Edgar Alan","Poe",13,
                    "Auteur de l'excellent Hello Kitty magazine");
            em.persist(writer1);


            Writer writer2 = new Writer("Maverick","O'Banen",40,
                    "Le seul, l'unique");
            em.persist(writer2);


            Book book1 = new Book(1,"201f", "La Souris",
                    "Français","Un drame sordide",300,null);
            book1.setCategory(category1);
            book1.setWriter(writer1);
            em.persist(book1);


            Book book2 = new Book(2,"202f", "Le Chat",
                    "Français","Une histoire d'amour",150,null);
            book2.setCategory(category2);
            book2.setWriter(writer2);

            em.persist(book2);


            Book book3 = new Book(3,"166e", "Madame Brisby et les rats de NIHM",
                    "Français","Aventure",250,null);
            book3.setCategory(category2);
            book3.setWriter(writer2);

            em.persist(book3);


            tx.commit();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
