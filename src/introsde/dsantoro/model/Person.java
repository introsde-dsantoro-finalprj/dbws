package introsde.dsantoro.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.persistence.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlElement
	private Long id;
	private String firstname;
	private String lastname;
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthday;
	private String email;
	private String weight;
	private String height;
	private Integer daycalories = 0;
	@OneToMany(mappedBy="person", cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	private Collection<Meal> meals = new ArrayList<Meal>();
	@OneToMany(mappedBy="person", cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	private Collection<Goal> goals = new ArrayList<Goal>();
	@OneToMany(mappedBy="person", cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	private Collection<Activity> activities = new ArrayList<Activity>();

	@Override
	public String toString() {		
		return  this.getClass().getName() + ": " +
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

	@XmlElementWrapper(name = "meals")
	@XmlElement(name="meal")
	public Collection<Meal> getMeals() {
		return meals;
	}

	public void setMeals(Collection<Meal> meals) {
		this.meals = meals;
	}

	@XmlElementWrapper(name = "goals")
	@XmlElement(name="goal")
	public Collection<Goal> getGoals() {
		return goals;
	}

	public void setGoals(Collection<Goal> goals) {
		this.goals = goals;
	}

	@XmlElementWrapper(name = "activities")
	@XmlElement(name="activity")
	public Collection<Activity> getActivities() {
		return activities;
	}

	public void setActivities(Collection<Activity> activities) {
		this.activities = activities;
	}

	public Long getId() {
		return id;
	}
	
	public void addMeal(Meal m) {
		meals.add(m);
		m.setPerson1(this);		
	}
	
	public void addActivity(Activity a) {		
		activities.add(a);
		a.setPerson(this);
	}
	
	public void addGoal(Goal g) {
		goals.add(g);
		g.setPerson(this);
	}
	
	// Database operations	
	public static Collection<Person> getAll() {
		EntityManager em = DbwsDao.instance.createEntityManager();
	    Collection<Person> list = em.createNamedQuery("Person.findAll", Person.class).getResultList();
	    DbwsDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Person savePerson(Person p) {
		
		// If present update person Meals
    	if (p.getMeals() != null) {
        	Iterator<Meal> i = p.getMeals().iterator();
        	while(i.hasNext()){
        		Meal m = i.next();
        		m.setPerson1(p);        		
        	}
    	}
    	
    	// If present update person Goals
    	if (p.getGoals() != null) {
        	Iterator<Goal> i = p.getGoals().iterator();
        	while(i.hasNext()){
        		Goal m = i.next();
        		m.setPerson(p);        		
        	}
    	}
    	
    	// If present update person Activities
    	if (p.getActivities() != null) {
        	Iterator<Activity> i = p.getActivities().iterator();
        	while(i.hasNext()){
        		Activity m = i.next();
        		m.setPerson(p);        		
        	}
    	}
		
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
