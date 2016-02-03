package introsde.dsantoro.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Use;
import javax.jws.soap.SOAPBinding.Style;

import introsde.dsantoro.model.Person;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL)
public interface Dbws {

	@WebMethod(operationName="readPerson")
	@WebResult(name="person") 
	public Person readPerson(@WebParam(name="personId") int id);

	@WebMethod(operationName="readPersonList")
	@WebResult(name="personList") 
	public List<Person> readPersonList();

	@WebMethod(operationName="createPerson")
	@WebResult(name="person") 
	public int createPerson(@WebParam(name="person") Person person);



}
