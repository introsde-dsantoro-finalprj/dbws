package introsde.dsantoro.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Goal
 *
 */
@Entity
public class Goal implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	//@Id
	private Date day;
	private String name;
	private Integer calories;
	
	public Goal() {
		super();
		Long time = new Date().getTime();
		day = new Date(time - time % (24 * 60 * 60 * 1000));		
	}
   
}
