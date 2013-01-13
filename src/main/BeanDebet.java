package main;

import java.sql.SQLException;
import java.sql.Timestamp;

import javax.swing.JOptionPane;

public class BeanDebet {
	  private Integer id;
	  private Integer pack_id;
	  private Integer acc_id;
	  private Integer srv_id;
	  private Double sum;
	  private Double pen;
	  private Timestamp since;
	  private String oper;
	  private Timestamp finish;
	  private String cont;
	  private String description;
	  
	public BeanDebet(Integer id, Integer pack_id, Integer acc_id,
			Integer srv_id, Double sum, Double pen, Timestamp since,
			String oper, Timestamp finish, String cont, String description) {
		super();
		this.id = id;
		this.pack_id = pack_id;
		this.acc_id = acc_id;
		this.srv_id = srv_id;
		this.sum = sum;
		this.pen = pen;
		this.since = since;
		this.oper = oper;
		this.finish = finish;
		this.cont = cont;
		this.description = description;
	}
	
	public void add() {
		try {
			String strSql = "INSERT INTO gks.debets(pack_id, acc_id, sum, pen, description) SELECT " 
				+ ", " + this.pack_id 
				+ ", " + this.acc_id 
				+ ", " + this.sum 
				+ ", " + this.pen 
				+ ", '" + this.description + "'";
			DbUtils.Conn.createStatement().executeUpdate(strSql); 
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "BeanDebet.add: Ошибка SQL", JOptionPane.ERROR_MESSAGE);
		}
		return;
	}
	
	public void update() {
		try {
			String strSql = "UPDATE gks.debets SET " 
				+ " pack_id=" + this.pack_id 
				+ ", acc_id=" + this.acc_id
				+ ", srv_id=" + this.srv_id
				+ ", sum=" + this.sum
				+ ", pen=" + this.pen
				+ ((this.since!=null)?", since='" + this.since + "' ":"")
				+ ", oper='" + this.oper + "'"
				+ ((this.finish!=null)?", finish='" + this.finish + "' ":"")
				+ ", cont='" + this.cont + "'"
				+ ", description='" +  this.description + "'" 
				+ " WHERE id=" + this.id;
			DbUtils.Conn.createStatement().executeUpdate(strSql); 
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "BeanDebet.update: Ошибка SQL", JOptionPane.ERROR_MESSAGE);
		}
		return;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPack_id() {
		return pack_id;
	}

	public void setPack_id(Integer pack_id) {
		this.pack_id = pack_id;
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

	public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	public Double getPen() {
		return pen;
	}

	public void setPen(Double pen) {
		this.pen = pen;
	}

	public Timestamp getSince() {
		return since;
	}

	public void setSince(Timestamp since) {
		this.since = since;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public Timestamp getFinish() {
		return finish;
	}

	public void setFinish(Timestamp finish) {
		this.finish = finish;
	}

	public String getCont() {
		return cont;
	}

	public void setCont(String cont) {
		this.cont = cont;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "BeanDebet [id=" + id + ", pack_id=" + pack_id + ", acc_id="
				+ acc_id + ", srv_id=" + srv_id + ", sum=" + sum + ", pen="
				+ pen + ", since=" + since + ", oper=" + oper + ", finish="
				+ finish + ", cont=" + cont + ", description=" + description
				+ "]";
	}
	
}
