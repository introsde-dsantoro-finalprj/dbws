package introsde.dsantoro.dao;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public enum DbwsDao {
	instance;
	private EntityManagerFactory emf;
	final String DB_HOST = System.getenv("DB_HOST");
	final String DB_PORT = System.getenv("DB_PORT");
	final String DB_USER = System.getenv("DB_USER");
	final String DB_PWD = System.getenv("DB_PWD");
	final String DB_NAME = System.getenv("DB_NAME");
	
	private DbwsDao() {
		if (emf!=null) {
			emf.close();
		}
		if (alternativeConf()) {
			System.out.println("DB config: Using environment variable");
			Properties props = new Properties();
			props.setProperty("javax.persistence.jdbc.url", "jdbc:mysql://"+DB_HOST+"/"+DB_NAME);
			props.setProperty("javax.persistence.jdbc.user", DB_USER);
			props.setProperty("javax.persistence.jdbc.password", DB_PWD);
			emf = Persistence.createEntityManagerFactory("dbws", props);
		}
		else {
			System.out.println("DB config: Using persistence.xml file");
			emf = Persistence.createEntityManagerFactory("dbws");
		}
	}
	
	private boolean alternativeConf() {
		if ( DB_HOST != null && DB_USER != null && DB_PWD != null && DB_NAME != null) {
			return true;
		}
		return false;
	}

	public EntityManager createEntityManager() {
		return emf.createEntityManager();
	}

	public void closeConnections(EntityManager em) {		
		em.close();
	}

	public EntityTransaction getTransaction(EntityManager em) {
		return em.getTransaction();
	}
	
	public EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}
	
//	// Person related operations could also directly go into the "Person" Model
//	// Check Methods in LifeStaus as example
//	public static Person getPersonById(Long personId) {
//		EntityManager em = instance.createEntityManager();
//		Person p = em.find(Person.class, personId);
//		instance.closeConnections(em);
//		return p;
//	}
//	
//	public static List<Person> getAll() {
//		EntityManager em = instance.createEntityManager();
//	    List<Person> list = em.createNamedQuery("Person.findAll", Person.class).getResultList();
//	    instance.closeConnections(em);
//	    return list;
//	}
//	
//	// add other database global access operations
	
}
