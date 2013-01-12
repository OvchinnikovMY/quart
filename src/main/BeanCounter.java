package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.JOptionPane;

public class BeanCounter {
	private Integer id; 
	private String description;

	public BeanCounter(Integer id, String description) {
		super();
		this.id = id;
		this.description = description;
	}

	public static BeanCounter create() throws NullPointerException {
		BeanCounter b = null;
   		String strDesc = "Счётчик создан " + Calendar.getInstance().getTime().toString();
		try {
			String strSql = "INSERT INTO gks.counters(description) SELECT '" + strDesc + "'";
			if ( JOptionPane.showConfirmDialog( null, strSql, "Добавить Счётчик?", 
				 JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION ) {
				 DbUtils.Conn.createStatement().executeUpdate(strSql); 
			} else {
				throw new NullPointerException("Счётчик не создан!");
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "BeanCounter.set: Ошибка SQL", JOptionPane.ERROR_MESSAGE);
		}

    	ResultSet rs;
    	String query = "select id, description from gks.counters WHERE description = '" + strDesc + "'";
        try{
        	rs = DbUtils.Conn.createStatement().executeQuery(query);
        	while(rs.next()){
        		b = new BeanCounter(rs.getInt("id"), rs.getString("description"));
        	}
        	rs.close();
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, e.getMessage(), "BeanCounter.get: Ошибка SQL", JOptionPane.ERROR_MESSAGE);
        }

        return b;
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
	@Override
	public String toString() {
		return id + " (" + description + ")";
	}
}
