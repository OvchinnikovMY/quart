package main;

public class BeanCred {
	private Integer acc; 
	private Integer per_id; 
	private Integer srv_id; 
	private String description; 
	private Boolean isod; 
	private Double cred; 
	private Double count; 
	private Double rclc;
	
	public BeanCred(Integer acc, Integer per_id, Integer srv_id,
			String description, Boolean isod, Double cred, Double count,
			Double rclc) {
		super();
		this.acc = acc;
		this.per_id = per_id;
		this.srv_id = srv_id;
		this.description = description;
		this.isod = isod;
		this.cred = cred;
		this.count = count;
		this.rclc = rclc;
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
	public Integer getSrv_id() {
		return srv_id;
	}
	public void setSrv_id(Integer srv_id) {
		this.srv_id = srv_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getIsod() {
		return isod;
	}
	public void setIsod(Boolean isod) {
		this.isod = isod;
	}
	public Double getCred() {
		return cred;
	}
	public void setCred(Double cred) {
		this.cred = cred;
	}
	public Double getCount() {
		return count;
	}
	public void setCount(Double count) {
		this.count = count;
	}
	public Double getRclc() {
		return rclc;
	}
	public void setRclc(Double rclc) {
		this.rclc = rclc;
	}
	@Override
	public String toString() {
		return "BeanCred [acc=" + acc + ", per_id=" + per_id + ", srv_id="
				+ srv_id + ", description=" + description + ", isod=" + isod
				+ ", cred=" + cred + ", count=" + count + ", rclc=" + rclc
				+ "]";
	}
}
