package introsde.dsantoro.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import introsde.dsantoro.dao.DbwsDao;

/**
 * Entity implementation class for Entity: Activity
 *
 */
@Entity
@Table(name="activity")
@NamedQuery(name="Activity.findAll", query="SELECT a FROM Activity a")
public class Activity implements Serializable {
	
	public Activity() {
		super();
		this.datetime = new Date();
	}

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlElement
	private Long id;
	private String name;
	@Temporal(TemporalType.TIMESTAMP)
	private Date datetime;
	private Integer calories = 0;
	@ManyToOne
	private Person person;
	
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
	public static List<Activity> getAll() {
		EntityManager em = DbwsDao.instance.createEntityManager();
	    List<Activity> list = em.createNamedQuery("Activity.findAll", Activity.class).getResultList();
	    DbwsDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Activity saveActivity(Activity a, Person p) {
		a.setPerson(p);
		EntityManager em = DbwsDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(a);
		tx.commit();
	    DbwsDao.instance.closeConnections(em);
	    return a;
	}
	@XmlTransient
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public static Activity updateActivity(Activity p) {
		EntityManager em = DbwsDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		p=em.merge(p);
		tx.commit();
	    DbwsDao.instance.closeConnections(em);
	    return p;
	}
	
	public static void removeActivity(Activity p) {
		EntityManager em = DbwsDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    p=em.merge(p);
	    em.remove(p);
	    tx.commit();
	    DbwsDao.instance.closeConnections(em);
	}

	public static Activity getActivityById(Long personId) {
		EntityManager em = DbwsDao.instance.createEntityManager();
		Activity p = em.find(Activity.class, personId);
		DbwsDao.instance.closeConnections(em);
		return p;
	}
   
}
