package main;

public class BeanService {
  private Integer id; 
  private String description; 
  private String abb;
  private Integer kind;
	private Integer org_id;
	  
	public BeanService(Integer id, String abb, String description, 
			Integer kind, Integer org_id) {
		super();
		this.id = id;
		this.abb = abb;
		this.description = description;
		this.kind = kind;
		this.org_id = org_id;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAbb() {
		return abb;
	}
	public void setAbb(String abb) {
		this.abb = abb;
	}
	public Integer getKind() {
		return kind;
	}
	public void setKind(Integer kind) {
		this.kind = kind;
	}
	public Integer getOrg_id() {
		return org_id;
	}
	public void setOrg_id(Integer org_id) {
		this.org_id = org_id;
	}
	@Override
	public String toString() {
		return abb + ": " + description;
	}
	  
	  
}
