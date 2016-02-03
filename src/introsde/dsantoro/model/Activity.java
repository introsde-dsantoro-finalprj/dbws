package introsde.dsantoro.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Activity
 *
 */
@Entity

public class Activity implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private Date datetime;
	private Integer calories;
	
	public Activity() {
		super();
	}
   
}
