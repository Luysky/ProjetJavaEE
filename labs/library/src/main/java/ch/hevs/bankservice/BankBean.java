package ch.hevs.bankservice;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateful;
import javax.persistence.*;

import ch.hevs.businessobject.*;

//Notre EJB

@Stateful
public class BankBean implements Bank {

	//Supprimer le Extended -> pas recommendé
	@PersistenceContext(name = "BankPU", type = PersistenceContextType.EXTENDED)

	//ENTITY MANAGER
	private EntityManager em;

	//UTILISATION DE L ENTITYMANAGER POUR LES REQUETES JPQL
	public Account getAccount(String accountDescription, String lastnameOwner) {
		Query query = em.createQuery("FROM Account a WHERE a.description=:description AND a.owner.lastname=:lastname");
		query.setParameter("description", accountDescription);
		query.setParameter("lastname", lastnameOwner);

		Account account = (Account) query.getSingleResult();
		System.out.println("ID account called from getAccount(): " + account.getId());
		return account;
	}

	public List<Account> getAccountListFromClientLastname(String lastname) {
		return (List<Account>) em.createQuery("SELECT c.accounts FROM Client c where c.lastname=:lastname").setParameter("lastname", lastname).getResultList();
	}

	public void transfer(Account srcAccount, Account destAccount, int amount) {

		System.out.println("ID source account called from transfer(): " + srcAccount.getId());
		System.out.println("ID destination account called from transfer(): " + destAccount.getId());
		
	/*	Account srcRealAccount = em.merge(srcAccount);
		Account destRealAccount = em.merge(destAccount);
		srcRealAccount.debit(amount);
		destRealAccount.credit(amount); */

		// for use with EPC and stateful
		em.persist(srcAccount);
		em.persist(destAccount);
		srcAccount.debit(amount);
		destAccount.credit(amount);
	}

	public List<Client> getClients() {
		return em.createQuery("FROM Client").getResultList();
	}

	public Client getClient(long clientid) {
		return (Client) em.createQuery("FROM Client c where c.id=:id").setParameter("id", clientid).getSingleResult();
	}

	@Override
	public void populateDatabase() {

		EntityTransaction tx = null;
		try {

			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("bankPU");
			EntityManager em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			Address address1 = new Address("1950", "Place des Rats", "4", "Sion");

			Member member1 = new Member("Kevin", "Coppey",
					2,"0798543125", "kevin@yahoo.it");
			member1.setAddress(address1);
			em.persist(member1);

			Address address2 = new Address("1950", "Chemin du Désespoir", "8", "Sion");

			Member member2 = new Member("Thomas", "Luyet",
					3,"0798483545", "thomas@hes.ch");
			member2.setAddress(address2);
			em.persist(member1);


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


			Book book1 = new Book(1,"201f", "La Souris",
					"Français","Un drame sordide",300);
			book1.setCategory(category1);
			Set<Writer>setWriter1 = new HashSet<>();
			setWriter1.add(writer1);
			book1.setWriters(setWriter1);
			em.persist(book1);

			Book book2 = new Book(2,"202f", "Le Chat",
					"Français","Une histoire d'amour",150);
			book2.setCategory(category2);
			Set<Writer>setWriter2 = new HashSet<>();
			setWriter1.add(writer2);
			book2.setWriters(setWriter2);
			em.persist(book2);

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
