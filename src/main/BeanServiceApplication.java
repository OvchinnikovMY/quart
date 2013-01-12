package main;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class BeanServiceApplication {
	private Integer id; 
	private Integer acc_id; 
	private Integer srv_id; 
	private Date since; 
	private Date finish; 
	private Double coef; 
	private String description; 
	private String abb; 
	private Integer kind; 
	private Integer org_id; 
	private Integer queue;
	private Boolean isod;
	private Integer cnt_id; 
  
	public BeanServiceApplication(Integer id, Integer acc_id, Integer srv_id,
			Date since, Date finish, Double coef, String description,
			String abb, Integer kind, Integer org_id, Integer queue, Boolean isod, Integer cnt_id) {
//		super();
		try {
			this.id = id;
			this.acc_id = acc_id;
			this.srv_id = srv_id;
			this.since = since;
			this.finish = finish;
			this.coef = coef;
			this.description = description;
			this.abb = abb;
			this.kind = kind;
			this.org_id = org_id;
			this.queue = queue;
			this.isod = isod;
			this.cnt_id = cnt_id;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),  "BeanServiceApplication..." , JOptionPane.ERROR_MESSAGE);
		}
	}

	public BeanServiceApplication(Integer id) {
		super();
		
		ResultSet rs;
		try {
			rs = DbUtils.Conn.createStatement().executeQuery("SELECT id, acc_id, srv_id, since, finish, coef, description, abb, kind, org_id, queue, isod, cnt_id FROM gks.v_service_applications WHERE id =" + id);
			rs.next();
			this.id = rs.getInt("id");
			this.acc_id = rs.getInt("acc_id");
			this.srv_id = rs.getInt("srv_id");
			this.since = rs.getDate("since");
			this.finish = rs.getDate("finish");
			this.coef = rs.getDouble("coef");
			this.description = rs.getString("description");
			this.abb = rs.getString("abb");
			this.kind = rs.getInt("kind");
			this.org_id = rs.getInt("org_id");
			this.queue = rs.getInt("queue");
			this.isod = rs.getBoolean("isod");
			this.cnt_id = rs.getInt("cnt_id");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(),  "BeanServiceApplication(" + id + ")" , JOptionPane.ERROR_MESSAGE);
			//e.printStackTrace();
		}
	}
	
	public static BeanServiceApplication create(Integer acc_id, Integer srv_id,
			String since, String finish, Double coef, Boolean isod) {
		BeanServiceApplication b = null;
		
		String strSql = "INSERT INTO gks.serv_applications( acc_id, srv_id, since, finish, coef, isod) SELECT " 
				+ acc_id + ", " 
				+ srv_id + ", " 
				+ ((since.isEmpty())? "null, ": " '" + since + "', ") 
				+ ((finish.isEmpty())? "null, ": " '" + finish + "', ") 
				+ coef + ", "
				+ isod;
		try {
			DbUtils.Conn.createStatement().executeUpdate(strSql); 
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage() + "\n" + strSql, "BeanServiceApplication.create: Ошибка SQL", JOptionPane.ERROR_MESSAGE);
		}
		
    	ResultSet rs;
    	String query = "select max(id) FROM gks.serv_applications WHERE acc_id=" + acc_id
				+ " AND srv_id=" + srv_id
				+ " AND since " + ((since.isEmpty())? "is null ": " = '" + since + "' ") 
				+ " AND finish " + ((finish.isEmpty())? "is null ": " = '" + finish + "' ") 
				+ " AND coef=" + coef + "::real"
				+ " AND isod=" + isod;
    	
        try{
        	rs = DbUtils.Conn.createStatement().executeQuery(query);
        	while(rs.next()){
        		b = new BeanServiceApplication(rs.getInt(1));
        	}
        	rs.close();
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, e.getMessage()+ "\n" + query, "BeanServiceApplication.create.select: Ошибка SQL", JOptionPane.ERROR_MESSAGE);
        }

        return b;
	}
	
	public void add() {
		try {
			String strSql = "INSERT INTO gks.serv_applications( acc_id, srv_id, since, finish, coef, cnt_id, isod) SELECT " 
				+ this.acc_id 
				+ ", "	+ this.srv_id 
				+ ((this.since  != null)?", '" + this.since + "'":", null")
				+ ((this.finish != null)?", '" + this.finish + "'":", null")
				+ ", "  + this.coef 
				+ ", "  + this.cnt_id 
				+ ", "  + this.isod;
			DbUtils.Conn.createStatement().executeUpdate(strSql); 
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "BeanServiceApplication.add: Ошибка SQL", JOptionPane.ERROR_MESSAGE);
		}
		return;
	}
	
	public BeanServiceApplication copy() {
		return new BeanServiceApplication(this.id, this.acc_id, this.srv_id, this.since, this.finish, this.coef, this.description, this.abb, this.kind, this.org_id, this.queue, this.isod, this.cnt_id);
	}
	
	public void update() {
		try {
			String strSql = "UPDATE gks.serv_applications SET " 
				+ " id=" + this.id 
				+ ", acc_id=" + this.acc_id
				+ ", srv_id=" + this.srv_id
				+ ((this.since!=null)?", since='" + this.since + "' ":"")
				+ ((this.finish!=null)?", finish='" + this.finish + "' ":"")
				+ ", coef=" + this.coef
				+ ", cnt_id=" + this.cnt_id
				+ ", isod=" + this.isod
				+ " WHERE id=" + this.id;
			DbUtils.Conn.createStatement().executeUpdate(strSql); 
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "BeanServiceApplication.update: Ошибка SQL", JOptionPane.ERROR_MESSAGE);
		}
		return;
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
	public void setFinish(java.sql.Date finish) {
		this.finish = finish;
	}
	public Double getCoef() {
		return coef;
	}
	public void setCoef(Double coef) {
		this.coef = coef;
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
	public Integer getQueue() {
		return queue;
	}
	public void setQueue(Integer queue) {
		this.queue = queue;
	}
	public Boolean getIsod() {
		return isod;
	}
	public void setIsod(Boolean isod) {
		this.isod = isod;
	}

	public Integer getCnt_id() {
		return cnt_id;
	}
	public void setCnt_id(Integer cnt_id) {
		this.cnt_id = cnt_id;
	}

	@Override
	public String toString() {
		return "BeanServiceApplication [id=" + id + ", abb=" + abb + ", since="
				+ since + ", finish=" + finish + ", coef=" + coef + ", kind="
				+ kind  + ", isod=" + isod + "]";
	}
}
