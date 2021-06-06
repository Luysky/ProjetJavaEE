package ch.hevs.libraryservice;

import ch.hevs.businessobject.*;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Stateless
public class LibraryBean implements Library {

    @PersistenceContext(name = "libraryPU", type= PersistenceContextType.TRANSACTION)
    private EntityManager em;

    @Resource
    private SessionContext ctx;


    @Override
    public List<Book> getBooks() {
        Query query = em.createQuery("FROM Book");

        //query.setParameter("idBook", 1);
        //query.setParameter("arrival", arrival);

        return (List<Book>) query.getResultList();
    }

    @Override
    public List<Book> getNonBorrowedBooks(){

        Query query = em.createQuery("FROM Book b WHERE b.borrower = null");

        return (List<Book>) query.getResultList();

    }


    @Override
    public Book getOneBook(int idBook) {
        Query query = em.createQuery("FROM Book b WHERE b.idBook = :idBook");

        query.setParameter("idBook", idBook);
        //query.setParameter("idBook", 1);
        //query.setParameter("arrival", arrival);

        return (Book) query.getSingleResult();
    }

    public Member getMember(int idMember){

        Query query = em.createQuery("FROM Member m WHERE m.idMember = :idMember");

        query.setParameter("idMember", idMember);
        //query.setParameter("idBook", 1);
        //query.setParameter("arrival", arrival);

        return (Member) query.getSingleResult();

    }

    public List<Member> getAllMembers(){

        Query query = em.createQuery("FROM Member ");

        //query.setParameter("idBook", 1);
        //query.setParameter("arrival", arrival);

        return (List<Member>) query.getResultList();

    }


    @Override
    public String book(Book oneBook, int currentMember) throws Exception {

        EntityTransaction tx = null;
        try {


            EntityManagerFactory emf = Persistence
                    .createEntityManagerFactory("libraryPU");
            EntityManager em = emf.createEntityManager();
            tx = em.getTransaction();


            tx.begin();

            //Car la gestion des users ne fonctionne pas !
            Member memberTest = getMember(currentMember);
            int idMember = memberTest.getId();

            Member member = em.find(Member.class,idMember);


            int idBook = oneBook.getId();

            Book book = em.find(Book.class,idBook);
            book.setBorrower(member);



            tx.commit();


            } catch (Exception e) {
            e.printStackTrace();

        }

        //A SUPPRIMER !
        String transactionResult="Success!";

        return transactionResult;

    }

    @Override
    public String member(Member member) throws Exception {

        EntityTransaction tx = null;
        try {


            EntityManagerFactory emf = Persistence
                    .createEntityManagerFactory("libraryPU");
            EntityManager em = emf.createEntityManager();
            tx = em.getTransaction();



            tx.begin();



            int idMember = member.getId();


            Member memberToDelete = em.find(Member.class,idMember);


            Set<Book>rentedBook =   memberToDelete.getBorrowedBook();

            if(!rentedBook.isEmpty()){
                System.out.println("*************************J'ai passé ici");
                for (Book b:rentedBook
                ) {
                    Book savedBook = b;
                    savedBook.setBorrower(null);
                    em.persist(savedBook);

                }
            }



            System.out.println("******************MEMBER TO DELETE = "+memberToDelete.getId());
            em.remove(memberToDelete);






            tx.commit();



        } catch (Exception e) {
            e.printStackTrace();

        }

        //A SUPPRIMER !
        String transactionResult="Success!";

        return transactionResult;

    }


    public List<Writer> getWriters() {

        Query query = em.createQuery("FROM Writer");

        //query.setParameter("idBook", 1);
        //query.setParameter("arrival", arrival);

        return (List<Writer>) query.getResultList();

    }


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
                    2,"0798543125", "kevin@yahoo.it");
            em.persist(member1);
            Address address1 = new Address("1950", "Place des Rats", "4", "Sion");
            member1.setAddress(address1);
            em.persist(member1);

            Member member2 = new Member("Thomas", "Luyet",
                    3,"0798483545", "thomas@hes.ch");
            em.persist(member2);
            Address address2 = new Address("1950", "Chemin du Désespoir", "8", "Sion");
            member2.setAddress(address2);
            em.persist(member2);

            Member member3 = new Member("Georges", "Sand",
                    1,"0798252483", "sand@sandy.com");
            em.persist(member3);
            Address address3 = new Address("1950", "Place des Potences", "13", "Sion");
            member3.setAddress(address3);
            em.persist(member3);


            Category category1 = new Category(10,"Horreur");
            em.persist(category1);



            Category category2 = new Category(20,"Romance");
            em.persist(category2);
//Set<Bank>setBook1 = new HashSet<>();
//setBook1.add()
//category1.setBook();




            Writer writer1 = new Writer("Edgar Alan","Poe",13,
                    "Auteur de l'excellent Hello Kitty magazine");
            em.persist(writer1);



            Writer writer2 = new Writer("Maverick","O'Banen",40,
                    "Le seul, l'unique");
            em.persist(writer2);




            Book book1 = new Book(1,"201f", "La Souris",
                    "Français","Un drame sordide",300,null);
            book1.setCategory(category1);
            //Set<Writer> setWriter1 = new HashSet<>();
            book1.setWriter(writer1);
            //book1.setWriters(setWriter1);
            em.persist(book1);



            Book book2 = new Book(2,"202f", "Le Chat",
                    "Français","Une histoire d'amour",150,null);
            book2.setCategory(category2);
            //Set<Writer>setWriter2 = new HashSet<>();
            //setWriter1.add(writer2);
            //book2.setWriters(setWriter2);
            //Set<Writer> setWriter1 = new HashSet<>();
            book2.setWriter(writer2);

            em.persist(book2);



            tx.commit();





        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
