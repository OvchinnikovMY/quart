package main;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.JOptionPane;

public class BeanDebetPack {
	private Integer id;
	private Date pdate;
	private Integer org_id;
	private String description;
	
	public BeanDebetPack(Integer id, Date pdate, Integer org_id,
			String description) {
		super();

		this.id = id;
		this.pdate = pdate;
		this.org_id = org_id;
		this.description = description;
	}
	
	public BeanDebetPack(Integer id) {
		super();
		
		ResultSet rs;
		try {
			rs = DbUtils.Conn.createStatement().executeQuery("SELECT id, pdate, org_id, description FROM gks.debet_packs WHERE id =" + id);
			rs.next();
			this.id = rs.getInt("id");
			this.pdate = rs.getDate("pdate");
			this.org_id = rs.getInt("org_id");
			this.description = rs.getString("description");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),  "BeanDebetPack(" + id + ")" , JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static BeanDebetPack create() {
		BeanDebetPack b = null;
   		String strDesc = "Пакет создан " + Calendar.getInstance().getTime().toString();

		String strSql = "INSERT INTO gks.debet_packs(description) SELECT '" + strDesc + "'";
		try {
			DbUtils.Conn.createStatement().executeUpdate(strSql); 
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage() + "\n" + strSql, "BeanDebetPack.create.insert: Ошибка SQL", JOptionPane.ERROR_MESSAGE);
		}
		
    	ResultSet rs;
    	String query = "select max(id) FROM gks.debet_packs WHERE description = '" + strDesc + "'";
        try{
        	rs = DbUtils.Conn.createStatement().executeQuery(query);
        	while(rs.next()){
        		b = new BeanDebetPack(rs.getInt(1));
        	}
        	rs.close();
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, e.getMessage()+ "\n" + query, "BeanDebetPack.create.select: Ошибка SQL", JOptionPane.ERROR_MESSAGE);
        }

        return b;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getPdate() {
		return pdate;
	}

	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}

	public Integer getOrg_id() {
		return org_id;
	}

	public void setOrg_id(Integer org_id) {
		this.org_id = org_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "BeanDebetPack [id=" + id + ", pdate=" + pdate + ", org_id="
				+ org_id + ", description=" + description + "]";
	}

	
}
