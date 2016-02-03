package introsde.dsantoro.ws;

import java.util.Collection;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import introsde.dsantoro.model.Activity;
import introsde.dsantoro.model.Goal;
import introsde.dsantoro.model.Meal;
import introsde.dsantoro.model.Person;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL)
public interface Dbws {

	// Person methods
	@WebMethod(operationName="readPerson")
	@WebResult(name="person") 
	public Person readPerson(@WebParam(name="personId") Long id);

	@WebMethod(operationName="readPersonList")
	@WebResult(name="person") 
	public Collection<Person> readPersonList();

	@WebMethod(operationName="createPerson")
	@WebResult(name="person") 
	public Person createPerson(@WebParam(name="person") Person person);

	// Goal methods
	@WebMethod(operationName="readGoal")
	@WebResult(name="goal") 
	public Goal readGoal(@WebParam(name="goalId") Long id);

	@WebMethod(operationName="readGoalList")
	@WebResult(name="goalList") 
	public Collection<Goal> readGoalList();

	@WebMethod(operationName="createGoal")
	@WebResult(name="goal") 
	public Goal createGoal(@WebParam(name="goal") Goal goal, @WebParam(name="person") Person person);

	// Activity methods
	@WebMethod(operationName="readActivity")
	@WebResult(name="activity") 
	public Activity readActivity(@WebParam(name="activityId") Long id);

	@WebMethod(operationName="readActivityList")
	@WebResult(name="activityList") 
	public Collection<Activity> readActivityList();

	@WebMethod(operationName="createActivity")
	@WebResult(name="activity") 
	public Activity createActivity(@WebParam(name="activity") Activity activity, @WebParam(name="person") Person person);
	
	// Meal methods
	@WebMethod(operationName="readMeal")
	@WebResult(name="meal") 
	public Meal readMeal(@WebParam(name="mealId") Long id);

	@WebMethod(operationName="readMealList")
	@WebResult(name="mealList") 
	public Collection<Meal> readMealList();

	@WebMethod(operationName="createMeal")
	@WebResult(name="meal") 
	public Meal createMeal(@WebParam(name="meal") Meal meal, @WebParam(name="person") Person person);

	

}
