package main;

import java.sql.Date;

public class BeanCounterValue {
	private Integer id;
	private Integer cnt_id;
	private Date checkdate; 
	private Double reading; 
	private Boolean isnew;
	private Boolean auto;
	
	public BeanCounterValue(Integer id, Integer cnt_id, Date checkdate,
			Double reading, Boolean isnew, Boolean auto) {
		super();
		this.id = id;
		this.cnt_id = cnt_id;
		this.checkdate = checkdate;
		this.reading = reading;
		this.isnew = isnew;
		this.auto = auto;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCnt_id() {
		return cnt_id;
	}
	public void setCnt_id(Integer cnt_id) {
		this.cnt_id = cnt_id;
	}
	public Date getCheckdate() {
		return checkdate;
	}
	public void setCheckdate(Date checkdate) {
		this.checkdate = checkdate;
	}
	public Double getReading() {
		return reading;
	}
	public void setReading(Double reading) {
		this.reading = reading;
	}
	public Boolean getIsnew() {
		return isnew;
	}
	public void setIsnew(Boolean isnew) {
		this.isnew = isnew;
	}
	public Boolean getAuto() {
		return auto;
	}
	public void setAuto(Boolean auto) {
		this.auto = auto;
	}
	@Override
	public String toString() {
		return "BeanCounterValue [id=" + id + ", cnt_id=" + cnt_id
				+ ", checkdate=" + checkdate + ", reading=" + reading
				+ ", isnew=" + isnew + ", auto=" + auto + "]";
	}
	
}
