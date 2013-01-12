package main;

import java.sql.Date;

public class BeanCalc {
	private Integer acc;
	private String name;
	private Date since;
	private Integer days;
	private Integer srv_id;
	private Boolean isod;
	private Double credsum;
	private Double countsum;
	private Double rclcsum;
	
	public BeanCalc(Integer acc, String name, Date since, Integer days, Integer srv_id,
			Boolean isod, Double credsum, Double countsum, Double rclcsum) {
		super();
		this.acc = acc;
		this.name = name; 
		this.since = since;
		this.days = days;
		this.srv_id = srv_id;
		this.isod = isod;
		this.credsum = credsum;
		this.countsum = countsum;
		this.rclcsum = rclcsum;
	}
	
	public Integer getAcc() {
		return acc;
	}
	public void setAcc(Integer acc) {
		this.acc = acc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getSince() {
		return since;
	}
	public void setSince(Date since) {
		this.since = since;
	}
	public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days = days;
	}
	public Integer getSrv_id() {
		return srv_id;
	}
	public void setSrv_id(Integer srv_id) {
		this.srv_id = srv_id;
	}
	public Boolean getIsod() {
		return isod;
	}
	public void setIsod(Boolean isod) {
		this.isod = isod;
	}
	public Double getCredsum() {
		return credsum;
	}
	public void setCredsum(Double credsum) {
		this.credsum = credsum;
	}
	public Double getCountsum() {
		return countsum;
	}
	public void setCountsum(Double countsum) {
		this.countsum = countsum;
	}
	public Double getRclcsum() {
		return rclcsum;
	}
	public void setRclcsum(Double rclcsum) {
		this.rclcsum = rclcsum;
	}
	@Override
	public String toString() {
		return "BeanCalc [acc=" + acc + ", name=" + name + ", since=" + since + ", days=" + days
				+ ", srv_id=" + srv_id + ", isod=" + isod + ", credsum="
				+ credsum + ", countsum=" + countsum + ", rclcsum=" + rclcsum
				+ "]";
	}
}
