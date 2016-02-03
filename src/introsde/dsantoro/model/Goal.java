package introsde.dsantoro.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import introsde.dsantoro.dao.DbwsDao;

/**
 * Entity implementation class for Entity: Goal
 *
 */
@Entity
@Table(name="goal")
public class Goal implements Serializable {

	public Goal() {
		super();
		Long time = new Date().getTime();
		day = new Date(time - time % (24 * 60 * 60 * 1000));		
	}

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Id
	@Temporal(TemporalType.TIMESTAMP)
	private Date day;
	private String name;
	private Integer calories;
	
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
	
	// Database operations	
	public static List<Goal> getAll() {
		EntityManager em = DbwsDao.instance.createEntityManager();
	    List<Goal> list = em.createNamedQuery("Goal.findAll", Goal.class).getResultList();
	    DbwsDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Goal saveGoal(Goal p) {
		EntityManager em = DbwsDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(p);
		tx.commit();
	    DbwsDao.instance.closeConnections(em);
	    return p;
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
