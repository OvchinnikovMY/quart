package main; 

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import javax.swing.JOptionPane;

public class BeanAccount {
	private Integer acc; 
	private String street; 
	private String num; 
	private String flat; 
	private Double sqstair; 
	private Double sqfooter; 
	private Date since; 
	private Date finish; 
	private String acmt; 
	private String howner; 
	private Double sql; 
	private Date own; 
	private Integer pes; 
	private String hcmt;
	
	public BeanAccount(Integer acc, String street, String num, String flat,
			Double sqstair, Double sqfooter, Date since, Date finish,
			String acmt, String howner, Double sql, Date own, Integer pes,
			String hcmt) {
		super();
		this.acc = acc;
		this.street = street;
		this.num = num;
		this.flat = flat;
		this.sqstair = sqstair;
		this.sqfooter = sqfooter;
		this.since = since;
		this.finish = finish;
		this.acmt = acmt;
		this.howner = howner;
		this.sql = sql;
		this.own = own;
		this.pes = pes;
		this.hcmt = hcmt;
	}
	
	public BeanAccount(Integer acc) {
		super();
//		Date c = Calendar.getInstance();
//		c.setTime("01.11.2012");
		
		try {
			ResultSet rs=DbUtils.Conn.createStatement().executeQuery(
				"SELECT acc, street, num, flat, sqstair, sqfooter, since, finish, acmt, howner, sql::numeric(10,2), own, pes, hcmt " +
				"FROM gks.v_account_current_details WHERE acc=" + acc);
			rs.next();
			this.acc = rs.getInt("acc");
			this.street = rs.getString("street");
			this.num = rs.getString("num");
			this.flat = rs.getString("flat");
			this.sqstair = rs.getDouble("sqstair");
			this.sqfooter = rs.getDouble("sqfooter");
			this.since = rs.getDate("since");
			this.finish = rs.getDate("finish");
			this.acmt = rs.getString("acmt");
			this.howner = rs.getString("howner");
			this.sql = rs.getDouble("sql");
			this.own = rs.getDate("own");
			this.pes = rs.getInt("pes");
			this.hcmt = rs.getString("hcmt");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(),  "Error message" , JOptionPane.ERROR_MESSAGE);
			//System.out.println("BeanAccount "+e.getMessage());
		}
		
	}
	
	public Integer getAcc() {
		return acc;
	}
	public void setAcc(Integer acc) {
		this.acc = acc;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getFlat() {
		return flat;
	}
	public void setFlat(String flat) {
		this.flat = flat;
	}
	public Double getSqstair() {
		return sqstair;
	}
	public void setSqstair(Double sqstair) {
		this.sqstair = sqstair;
	}
	public Double getSqfooter() {
		return sqfooter;
	}
	public void setSqfooter(Double sqfooter) {
		this.sqfooter = sqfooter;
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
	public String getAcmt() {
		return acmt;
	}
	public void setAcmt(String acmt) {
		this.acmt = acmt;
	}
	public String getHowner() {
		return howner;
	}
	public void setHowner(String howner) {
		this.howner = howner;
	}
	public Double getSql() {
		return sql;
	}
	public void setSql(Double sql) {
		this.sql = sql;
	}
	public Date getOwn() {
		return own;
	}
	public void setOwn(Date own) {
		this.own = own;
	}
	public Integer getPes() {
		return pes;
	}
	public void setPes(Integer pes) {
		this.pes = pes;
	}
	public String getHcmt() {
		return hcmt;
	}
	public void setHcmt(String hcmt) {
		this.hcmt = hcmt;
	}

	@Override
	public String toString() {
		return "BeanAccount [acc=" + acc + ", street=" + street + ", num="
				+ num + ", flat=" + flat + ", sqstair=" + sqstair
				+ ", sqfooter=" + sqfooter + ", since=" + since + ", finish="
				+ finish + ", acmt=" + acmt + ", howner=" + howner + ", sql="
				+ sql + ", own=" + own + ", pes=" + pes + ", hcmt=" + hcmt
				+ "]";
	}
}
