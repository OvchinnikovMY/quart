package main;

import java.util.Date;

public class BeanHistory {
	private Integer id; 
	private Integer acc_id;
	private Date chdate;
	private Integer id_owner;
	private Double sql;
	private Date own;
	private Integer pes;
	private String cmt;
	private String howner;

	public BeanHistory(Integer id, Integer acc_id, Date chdate,
			String howner, Double sql, Date own, Integer pes, String cmt) {
//		super();
		this.id = id;
		this.acc_id = acc_id;
		this.chdate = chdate;
		this.howner = howner;
		this.sql = sql;
		this.own = own;
		this.pes = pes;
		this.cmt = cmt;
	}
	  
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAcc_id() {
		return acc_id;
	}
	public void setAcc_id(Integer acc_id) {
		this.acc_id = acc_id;
	}
	public Date getChdate() {
		return chdate;
	}
	public void setChdate(Date chdate) {
		this.chdate = chdate;
	}
	public Integer getId_owner() {
		return id_owner;
	}
	public void setId_owner(Integer id_owner) {
		this.id_owner = id_owner;
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
	public String getCmt() {
		return cmt;
	}
	public void setCmt(String cmt) {
		this.cmt = cmt;
	}
	public String getHowner() {
		return howner;
	}
	public void setHowner(String howner) {
		this.howner = howner;
	}

	@Override
	public String toString() {
		return id + ", " +
				", "	+ chdate + 
				", " + howner +
				", " + sql + 
				", " + own + 
				", " + pes + 
				", " + cmt;
	}
	
}
