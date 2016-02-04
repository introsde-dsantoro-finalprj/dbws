package introsde.dsantoro.dbws;

import java.util.Collection;

import javax.jws.WebService;

import introsde.dsantoro.model.Activity;
import introsde.dsantoro.model.Goal;
import introsde.dsantoro.model.Meal;
import introsde.dsantoro.model.Person;

@WebService(endpointInterface = "introsde.dsantoro.dbws.Dbws", serviceName="dbwsService")
public class DbwsImpl implements Dbws {

	@Override
	public Person readPerson(Long id) {
		System.out.println("---> Reading Person by id = "+id);
        Person p = Person.getPersonById(id);
        if (p!=null) {
            System.out.println("---> Found Person by id = "+id+" => "+p);
        } else {
            System.out.println("---> Didn't find any Person with  id = "+id);
        }
        return p;
	}

	@Override
	public Collection<Person> readPersonList() {
		return Person.getAll();
	}

	@Override
	public Person createPerson(Person person) {
		return Person.savePerson(person);
	}
	

	@Override
	public Meal readMeal(Long id) {
		System.out.println("---> Reading Meal by id = "+id);
        Meal m= Meal.getMealById(id);
        if (m!=null) {
            System.out.println("---> Found Meal by id = "+id+" => "+m);
        } else {
            System.out.println("---> Didn't find any Meal with  id = "+id);
        }
        return m;
	}

	@Override
	public Collection<Meal> readMealList() {
		return Meal.getAll();
	}

	@Override
	public Meal createMeal(Meal meal, Person person) {
		return Meal.saveMeal(meal, person);
	}
	

	@Override
	public Activity readActivity(Long id) {
		System.out.println("---> Reading Activity by id = "+id);
        Activity a = Activity.getActivityById(id);
        if (a!=null) {
            System.out.println("---> Found Activity by id = "+id+" => "+a);
        } else {
            System.out.println("---> Didn't find any Activity with  id = "+id);
        }
        return a;
	}

	@Override
	public Collection<Activity> readActivityList() {
		return Activity.getAll();
	}

	@Override
	public Activity createActivity(Activity activity, Person person) {
		return Activity.saveActivity(activity, person);
	}
	

	@Override
	public Goal readGoal(Long id) {
		System.out.println("---> Reading Goal by id = "+id);
        Goal g = Goal.getGoalById(id);
        if (g!=null) {
            System.out.println("---> Found Goal by id = "+id+" => "+g);
        } else {
            System.out.println("---> Didn't find any Goal with  id = "+id);
        }
        return g;
	}

	@Override
	public Collection<Goal> readGoalList() {
		return Goal.getAll();
	}

	@Override
	public Goal createGoal(Goal goal, Person person) {
		return Goal.saveGoal(goal, person);
	}
	
}
