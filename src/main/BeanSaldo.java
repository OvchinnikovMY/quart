package main;

import java.sql.Date;

public class BeanSaldo {
	private Integer acc;
	private Integer per_id; 
	private Date since; 
	private Double rest; 
	private Double cred; 
	private Double rclc; 
	private Double debt;
	
	public BeanSaldo(Integer acc, Integer per_id, Date since, Double rest, Double cred,
			Double rclc, Double debt) {
		super();
		this.acc = acc;
		this.per_id = per_id;
		this.since = since;
		this.rest = rest;
		this.cred = cred;
		this.rclc = rclc;
		this.debt = debt;
	}
	
	public Integer getAcc() {
		return acc;
	}
	public void setAcc(Integer acc) {
		this.acc = acc;
	}
	public Integer getPer_id() {
		return per_id;
	}
	public void setPer_id(Integer per_id) {
		this.per_id = per_id;
	}
	public Date getSince() {
		return since;
	}

	public void setSince(Date since) {
		this.since = since;
	}
	public Double getRest() {
		return rest;
	}
	public void setRest(Double rest) {
		this.rest = rest;
	}
	public Double getCred() {
		return cred;
	}
	public void setCred(Double cred) {
		this.cred = cred;
	}
	public Double getRclc() {
		return rclc;
	}
	public void setRclc(Double rclc) {
		this.rclc = rclc;
	}
	public Double getDebt() {
		return debt;
	}
	public void setDebt(Double debt) {
		this.debt = debt;
	}
	@Override
	public String toString() {
		return "BeanSaldo [acc=" + acc + ", per_id=" + per_id + ", since=" + since + ", rest="
				+ rest + ", cred=" + cred + ", rclc=" + rclc + ", debt=" + debt
				+ "]";
	}
}
