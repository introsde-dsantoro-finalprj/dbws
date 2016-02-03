package introsde.dsantoro.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import introsde.dsantoro.model.Goal;
import introsde.dsantoro.model.Person;
import introsde.dsantoro.ws.Dbws;

public class DbwsTest {

	
	public static void main(String[] args) throws MalformedURLException {
		// Client test
	    
	    URL url = new URL("http://192.168.5.3:6902/ws/dbws?wsdl");
	    //1st argument service URI, refer to wsdl document above
	    //2nd argument is service name, refer to wsdl document above
	    QName qname = new QName("http://ws.dsantoro.introsde/", "dbwsService");
	    Service service = Service.create(url, qname);

	    Dbws dbws = service.getPort(Dbws.class);
	    Person p = new Person();
	    p.setFirstname("Daniele");
	    p = dbws.createPerson(p);
	    List<Person> pList = dbws.readPersonList();
	    System.out.println("First Person in the list ==> "+pList.get(0));
	    
	    Goal g = new Goal();
//	    g = dbws.
	}
}
