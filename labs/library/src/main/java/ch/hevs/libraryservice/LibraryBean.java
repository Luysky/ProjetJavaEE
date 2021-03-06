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
                transactionResult="Le membre a d??j?? lou?? deux livres !";
                ctx.setRollbackOnly();
                return transactionResult;
            }


            tx.commit();


        } catch (Exception e) {
            e.printStackTrace();
        }

        transactionResult = "Le livre a bien ??t?? lou?? !";

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

        String transactionResult="Le membre a bien ??t?? supprim?? de la base de donn??e !";

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
                    "0798543125", "kevin@yahoo.it",null);
            em.persist(member1);
            Address address1 = new Address("1950", "Place des Rats", "4", "Sion");
            member1.setAddress(address1);
            em.persist(member1);

            Member member2 = new Member("Thomas", "Luyet",
                    "0798483545", "thomas@hes.ch",null);
            em.persist(member2);
            Address address2 = new Address("1950", "Chemin du D??sespoir", "8", "Sion");
            member2.setAddress(address2);
            em.persist(member2);

            Member member3 = new Member("Georges", "Sand",
                    "0798252483", "sand@sandy.com",null);
            em.persist(member3);
            Address address3 = new Address("1950", "Place des Potences", "13", "Sion");
            member3.setAddress(address3);
            em.persist(member3);


            Category category1 = new Category("Horreur");
            em.persist(category1);


            Category category2 = new Category("Romance");
            em.persist(category2);

            Category category3 = new Category("Policier");
            em.persist(category3);


            Writer writer1 = new Writer("Edgar Alan","Poe",
                    "Auteur de l'excellent Hello Kitty magazine");
            em.persist(writer1);


            Writer writer2 = new Writer("Maverick","O'Banen",
                    "Le seul, l'unique");
            em.persist(writer2);

            Writer writer3 = new Writer("Michel","Moatti",
                    "Docteur en sociologie des m??dias");
            em.persist(writer3);


            Book book1 = new Book("201f", "La Souris",
                    "Fran??ais","Un drame sordide",300,null);
            book1.setCategory(category1);
            book1.setWriter(writer1);
            em.persist(book1);


            Book book2 = new Book("202f", "Le Chat",
                    "Fran??ais","Une histoire d'amour",150,null);
            book2.setCategory(category2);
            book2.setWriter(writer2);

            em.persist(book2);


            Book book3 = new Book("166e", "Madame Brisby et les rats de NIHM",
                    "Fran??ais","Aventure",250,null);
            book3.setCategory(category2);
            book3.setWriter(writer2);

            em.persist(book3);


            Book book4 = new Book("342a", "Retour ?? Whitechapel",
                    "Fran??ais","Drame de guerre",432,null);
            book4.setCategory(category3);
            book4.setWriter(writer3);

            em.persist(book4);


            tx.commit();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
