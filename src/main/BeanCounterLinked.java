package main;

public class BeanCounterLinked {
	private Integer cnt_id;
	private Integer acc_id;
	private String address;
	private String srv_name;
	private Boolean isod;
	private String description;
	 
	public BeanCounterLinked(Integer cnt_id, Integer acc_id, String address,
			String srv_name, Boolean isod, String description) {
		super();
		this.cnt_id = cnt_id;
		this.acc_id = acc_id;
		this.address = address;
		this.srv_name = srv_name;
		this.isod = isod;
		this.description = description;
	}
	
	public Integer getCnt_id() {
		return cnt_id;
	}
	public void setCnt_id(Integer cnt_id) {
		this.cnt_id = cnt_id;
	}
	public Integer getAcc_id() {
		return acc_id;
	}
	public void setAcc_id(Integer acc_id) {
		this.acc_id = acc_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSrv_name() {
		return srv_name;
	}
	public void setSrv_name(String srv_name) {
		this.srv_name = srv_name;
	}
	public Boolean getIsod() {
		return isod;
	}
	public void setIsod(Boolean isod) {
		this.isod = isod;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
