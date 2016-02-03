package introsde.dsantoro.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import introsde.dsantoro.dao.DbwsDao;

/**
 * Entity implementation class for Entity: Meal
 *
 */
@Entity
@Table(name="meal")
public class Meal implements Serializable {

	private static final long serialVersionUID = 1L;
	public Meal() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	@Temporal(TemporalType.TIMESTAMP)
	private Date datetime;
	private Integer calories;
	private String type;
	
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
   
	// Database operations	
	public static List<Meal> getAll() {
		EntityManager em = DbwsDao.instance.createEntityManager();
	    List<Meal> list = em.createNamedQuery("Meal.findAll", Meal.class).getResultList();
	    DbwsDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Meal saveMeal(Meal p) {
		EntityManager em = DbwsDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(p);
		tx.commit();
	    DbwsDao.instance.closeConnections(em);
	    return p;
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
