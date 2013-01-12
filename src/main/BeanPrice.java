package main;

import java.sql.Date;

public class BeanPrice {
	private Integer id; 
	private Integer srv_id;
	private Date date;
	private Double sum;
	
	public BeanPrice(Integer id, Integer srv_id, Date date, Double sum) {
		super();
		this.id = id;
		this.srv_id = srv_id;
		this.date = date;
		this.sum = sum;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSrv_id() {
		return srv_id;
	}
	public void setSrv_id(Integer srv_id) {
		this.srv_id = srv_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getSum() {
		return sum;
	}
	public void setSum(Double sum) {
		this.sum = sum;
	}
	@Override
	public String toString() {
		return date + ": " + sum;
	}
}
