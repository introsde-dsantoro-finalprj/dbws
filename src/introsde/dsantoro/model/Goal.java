package introsde.dsantoro.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import introsde.dsantoro.dao.DbwsDao;

/**
 * Entity implementation class for Entity: Goal
 *
 */
@Entity
@Table(name="goal")
@NamedQuery(name="Goal.findAll", query="SELECT g FROM Goal g")
public class Goal implements Serializable {

	public Goal() {
		super();
		Long time = new Date().getTime();
		day = new Date(time - time % (24 * 60 * 60 * 1000));		
	}

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlElement
	private Long id;	
	@Temporal(TemporalType.TIMESTAMP)
	private Date day;
	private String name;
	private Integer calories = 0;
	@ManyToOne
	private Person person;
	
	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	@XmlTransient
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return  this.getClass().getName() + ": " +
				"Name: " + name + " " +
				"Date: " + day + " " +
				"Id: " + id;
	}
	
	
	// Database operations	
	public static List<Goal> getAll() {
		EntityManager em = DbwsDao.instance.createEntityManager();
	    List<Goal> list = em.createNamedQuery("Goal.findAll", Goal.class).getResultList();
	    DbwsDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Goal saveGoal(Goal g, Person p) {
		g.setPerson(p);
		EntityManager em = DbwsDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(g);
		tx.commit();
	    DbwsDao.instance.closeConnections(em);
	    return g;
	}
	public static Goal updateGoal(Goal p) {
		EntityManager em = DbwsDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		p=em.merge(p);
		tx.commit();
	    DbwsDao.instance.closeConnections(em);
	    return p;
	}
	
	public static void removeGoal(Goal p) {
		EntityManager em = DbwsDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    p=em.merge(p);
	    em.remove(p);
	    tx.commit();
	    DbwsDao.instance.closeConnections(em);
	}

	public static Goal getGoalById(Long personId) {
		EntityManager em = DbwsDao.instance.createEntityManager();
		Goal p = em.find(Goal.class, personId);
		DbwsDao.instance.closeConnections(em);
		return p;
	}
   
}
