package main;

import java.sql.Date;

public class BeanServApp {
	private Integer id;
	private Integer acc_id;
	private Integer srv_id;
	private Date since;
	private Date finish;
	private Double coef; 
	private Integer cnt_id;
	private Boolean isod;

	public BeanServApp(Integer id, Integer acc_id, Integer srv_id, Date since,
			Date finish, Double coef, Integer cnt_id, Boolean isod) {
		super();
		this.id = id;
		this.acc_id = acc_id;
		this.srv_id = srv_id;
		this.since = since;
		this.finish = finish;
		this.coef = coef;
		this.cnt_id = cnt_id;
		this.isod = isod;
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
	public Integer getSrv_id() {
		return srv_id;
	}
	public void setSrv_id(Integer srv_id) {
		this.srv_id = srv_id;
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
	public Double getCoef() {
		return coef;
	}
	public void setCoef(Double coef) {
		this.coef = coef;
	}
	public Integer getCnt_id() {
		return cnt_id;
	}
	public void setCnt_id(Integer cnt_id) {
		this.cnt_id = cnt_id;
	}
	public Boolean getIsod() {
		return isod;
	}
	public void setIsod(Boolean isod) {
		this.isod = isod;
	}
	@Override
	public String toString() {
		return "BeanServApp [id=" + id + ", acc_id=" + acc_id + ", srv_id="
				+ srv_id + ", since=" + since + ", finish=" + finish
				+ ", coef=" + coef + ", cnt_id=" + cnt_id + ", isod=" + isod
				+ "]";
	} 
}
