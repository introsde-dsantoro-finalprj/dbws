package introsde.dsantoro.ws;

import java.util.List;

import javax.jws.WebService;

import introsde.dsantoro.model.Person;

@WebService(endpointInterface = "introsde.dsantoro.ws.Dbws", serviceName="dbws")
public class DbwsImpl implements Dbws {

	@Override
	public Person readPerson(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> readPersonList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createPerson(Person person) {
		// TODO Auto-generated method stub
		return 0;
	}
}