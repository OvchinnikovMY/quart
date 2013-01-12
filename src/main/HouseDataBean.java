package main;

public class HouseDataBean {
	private Integer id;
	private String name;
	private String num;
	private Double sqstair;
	private Double sqfooter;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
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

    public HouseDataBean(Integer id, String name, String num, Double sqstair, Double sqfooter) {
        this.id = id;
        this.name = name;
        this.num = num;
        this.sqstair = sqstair;
        this.sqfooter = sqfooter;
    }
    
    public String toString() {
        return name + ", " + num;
    }
}
