package introsde.dsantoro.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Person
 *
 */
@Entity

public class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String firstname;
	private String lastname;
	private Date birthday;
	private String email;
	private String weight;
	private String height;
	private Integer daycalories;

	public Person() {
		super();
	}
	
	@Override
	public String toString() {
		return 
				"Firstname: " + firstname +
				"Lastname: " + lastname +
				"Id: " + id;
	}
   
}
