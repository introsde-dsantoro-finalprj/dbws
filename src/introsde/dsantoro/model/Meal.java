package introsde.dsantoro.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import introsde.dsantoro.dao.DbwsDao;

/**
 * Entity implementation class for Entity: Meal
 *
 */
@Entity
@Table(name="meal")
@NamedQuery(name="Meal.findAll", query="SELECT m FROM Meal m")
public class Meal implements Serializable {

	private static final long serialVersionUID = 1L;
	public Meal() {
		super();
		this.datetime = new Date();
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlElement
	private Long id;
	private String name;
	@Temporal(TemporalType.TIMESTAMP)
	private Date datetime;
	private Integer calories = 0;
	private String type;	
	@ManyToOne
	private Person person;
	
	@XmlTransient
	public Person getPerson() {
		return person;
	}
	
	public void setPerson1(Person person) {
		this.person = person;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public Integer getCalories() {
		return calories;
	}

	public void setCalories(Integer calories) {
		this.calories = calories;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}
 
	@Override
	public String toString() {
		return  this.getClass().getName() + ": " +
				"Name: " + name + " " +
				"Date: " + datetime + " " +
				"Id: " + id;
	}
	
	// Database operations	
	public static List<Meal> getAll() {
		EntityManager em = DbwsDao.instance.createEntityManager();
	    List<Meal> list = em.createNamedQuery("Meal.findAll", Meal.class).getResultList();
	    DbwsDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Meal saveMeal(Meal m, Person p) {
		m.setPerson1(p);
		EntityManager em = DbwsDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(m);
		tx.commit();
	    DbwsDao.instance.closeConnections(em);
	    return m;
	}
	
	public static Meal updateMeal(Meal p) {
		EntityManager em = DbwsDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		p=em.merge(p);
		tx.commit();
	    DbwsDao.instance.closeConnections(em);
	    return p;
	}
	
	public static void removeMeal(Meal p) {
		EntityManager em = DbwsDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    p=em.merge(p);
	    em.remove(p);
	    tx.commit();
	    DbwsDao.instance.closeConnections(em);
	}

	public static Meal getMealById(Long personId) {
		EntityManager em = DbwsDao.instance.createEntityManager();
		Meal p = em.find(Meal.class, personId);
		DbwsDao.instance.closeConnections(em);
		return p;
	}
}
