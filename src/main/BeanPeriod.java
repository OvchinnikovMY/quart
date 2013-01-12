package main;

import java.sql.Date;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class BeanPeriod {
	private Integer id;
	private Date since;
	private Date finish;
	private String description;;
	private String serial_number;
	
	public BeanPeriod() {
		super();
		createBeanPeriod(0);
	}
	
	public BeanPeriod(Integer _id) {
		super();
		createBeanPeriod(_id);
	}

	public BeanPeriod(Integer id, Date since, Date finish, String description,
			String serial_number) {
		super();
		this.id = id;
		this.since = since;
		this.finish = finish;
		this.description = description;
		this.serial_number = serial_number;
	}

	private void createBeanPeriod(Integer _id){
		
		ResultSet rs;
		try {
			rs = DbUtils.Conn.createStatement().executeQuery("SELECT id, since, finish, description, serial_number FROM gks.counters WHERE id = " + 
				((_id == 0)?"gks.f_period_current()":_id));
			while(rs.next()){
				id = rs.getInt("id");
				since = rs.getDate("since");
				finish = rs.getDate("finish");
				description = rs.getString("description");
				serial_number = rs.getString("serial_number"); 
			}
			rs.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),  "BeanPeriod" , JOptionPane.ERROR_MESSAGE);
		}
		return;
	}
	
	public Boolean isInPeriod(Date _date){
		ResultSet rs;
		Boolean ret=false;
		try {
			rs = DbUtils.Conn.createStatement().executeQuery("SELECT gks.f_is_in_period('" + _date + "'::date)");
			while(rs.next()){
				ret = rs.getBoolean(1);
			}
			rs.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),  "isInPeriod" , JOptionPane.ERROR_MESSAGE);
		}
		return ret;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getSince() {
		return since;
	}

	public void setSince(Date since) {
		this.since = since;
	}

	public Date getFinish() {
		return finish;
	}

	public void setFinish(Date finish) {
		this.finish = finish;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSerial_number() {
		return serial_number;
	}

	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number;
	}

	@Override
	public String toString() {
		return "BeanPeriod [id=" + id + ", since=" + since + ", finish="
				+ finish + ", description=" + description + ", serial_number="
				+ serial_number + "]";
	}
	
}
