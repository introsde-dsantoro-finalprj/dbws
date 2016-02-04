package introsde.dsantoro.test;

import java.net.MalformedURLException;
import java.net.URL;


import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import introsde.dsantoro.dbws.Dbws;
import introsde.dsantoro.model.Activity;
import introsde.dsantoro.model.Goal;
import introsde.dsantoro.model.Meal;
import introsde.dsantoro.model.Person;

public class DbwsTest {

	
	public static void main(String[] args) throws MalformedURLException {
	    final String DBWS_HOST = System.getenv("DBWS_HOST");
	    final String DBWS_PORT = System.getenv("DBWS_PORT");
	    URL url = new URL("http://"+DBWS_HOST+":"+DBWS_PORT+"/ws/dbws?wsdl");
	    //1st argument service URI, refer to wsdl document above
	    //2nd argument is service name, refer to wsdl document above
	    QName qname = new QName("http://ws.dsantoro.introsde/", "dbwsService");
	    Service service = Service.create(url, qname);

	    Dbws dbws = service.getPort(Dbws.class);
	    
	    Activity a = new Activity();
	    a.setName("acitivity inside person");
	    
	    Goal g = new Goal();
	    g.setName("goal in person");
	    g.setCalories(300);
	    
	    Meal m = new Meal();
	    m.setName("Pranzo ristorante");
	    m.setCalories(10);	    
	    
	    Person p = new Person();	    
	    p.setFirstname("Daniele");
	    p.addMeal(m);
	    m.setPerson1(p);
	    p.addGoal(g);
	    p.addActivity(a);
	    p = dbws.createPerson(p);
	    
	    
	    System.out.println("Person just created ==> "+p);
	    g = new Goal();
	    g.setName("My first goal");
	    g = dbws.createGoal(g, p);
	    System.out.println("Goal just created ==> "+g);
	    
	    m = new Meal();
	    m.setName("Fruits");
	    m.setCalories(1000);
	    m = dbws.createMeal(m, p);
	    System.out.println("Meal just created ==> "+m);
	    
	    a = new Activity();
	    a.setName("Today run");
	    a.setCalories(900);
	    a = dbws.createActivity(a, p);
	    System.out.println("Activity just created ==> "+a);
	}
}
