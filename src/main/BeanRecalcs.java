package main;

import java.sql.Date;

public class BeanRecalcs {
	private Integer id;
	private Date rdate;
	private String description;
	
	public BeanRecalcs(Integer id, Date rdate, String description) {
		super();
		this.id = id;
		this.rdate = rdate;
		this.description = description;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return id + ", " + rdate + ", (" + description + ")";
	}
	
	
}
