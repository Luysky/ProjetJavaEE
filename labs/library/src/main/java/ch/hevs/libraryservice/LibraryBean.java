package ch.hevs.libraryservice;

import ch.hevs.businessobject.*;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.*;
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

/*

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


/*

            Book book1 = new Book(1,"201f", "La Souris",
                    "Français","Un drame sordide",300);
            book1.setCategory(category1);
            Set<Writer> setWriter1 = new HashSet<>();
            setWriter1.add(writer1);
            //book1.setWriters(setWriter1);
            em.persist(book1);



            Book book2 = new Book(2,"202f", "Le Chat",
                    "Français","Une histoire d'amour",150);
            book2.setCategory(category2);
            Set<Writer>setWriter2 = new HashSet<>();
            setWriter1.add(writer2);
            //book2.setWriters(setWriter2);
            em.persist(book2);
*/


            tx.commit();



        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
