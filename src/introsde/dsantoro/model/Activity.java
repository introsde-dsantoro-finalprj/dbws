package introsde.dsantoro.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import introsde.dsantoro.dao.DbwsDao;

/**
 * Entity implementation class for Entity: Activity
 *
 */
@Entity
@Table(name="activity")
public class Activity implements Serializable {
	
	public Activity() {
		super();
	}

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	@Temporal(TemporalType.TIMESTAMP)
	private Date datetime;
	private Integer calories;
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

	// Database operations	
	public static List<Activity> getAll() {
		EntityManager em = DbwsDao.instance.createEntityManager();
	    List<Activity> list = em.createNamedQuery("Activity.findAll", Activity.class).getResultList();
	    DbwsDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Activity saveActivity(Activity p) {
		EntityManager em = DbwsDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(p);
		tx.commit();
	    DbwsDao.instance.closeConnections(em);
	    return p;
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
