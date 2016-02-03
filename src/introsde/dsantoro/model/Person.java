package introsde.dsantoro.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import introsde.dsantoro.dao.DbwsDao;

/**
 * Entity implementation class for Entity: Person
 *
 */
@Entity
@Table(name="person")
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
public class Person implements Serializable {

	public Person() {
		super();
	}

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String firstname;
	private String lastname;
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthday;
	private String email;
	private String weight;
	private String height;
	private Integer daycalories;

	@Override
	public String toString() {
		return 
				"Firstname: " + firstname + " " +
				"Lastname: " + lastname + " " +
				"Id: " + id;
	}
   
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public Integer getDaycalories() {
		return daycalories;
	}

	public void setDaycalories(Integer daycalories) {
		this.daycalories = daycalories;
	}

	public Long getId() {
		return id;
	}
	
	// Database operations	
	public static List<Person> getAll() {
		EntityManager em = DbwsDao.instance.createEntityManager();
	    List<Person> list = em.createNamedQuery("Person.findAll", Person.class).getResultList();
	    DbwsDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Person savePerson(Person p) {
		EntityManager em = DbwsDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(p);
		tx.commit();
	    DbwsDao.instance.closeConnections(em);
	    return p;
	}
	
	public static Person updatePerson(Person p) {
		EntityManager em = DbwsDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		p=em.merge(p);
		tx.commit();
	    DbwsDao.instance.closeConnections(em);
	    return p;
	}
	
	public static void removePerson(Person p) {
		EntityManager em = DbwsDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    p=em.merge(p);
	    em.remove(p);
	    tx.commit();
	    DbwsDao.instance.closeConnections(em);
	}

	public static Person getPersonById(Long personId) {
		EntityManager em = DbwsDao.instance.createEntityManager();
		Person p = em.find(Person.class, personId);
		DbwsDao.instance.closeConnections(em);
		return p;
	}
}
