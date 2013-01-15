package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.AbstractTableModel;

public class WinDebet extends JFrame{

	public WinDebet(Integer _pack_id) throws HeadlessException {
		super("Квартплата: пакет оплаты");
		final BeanDebetPack dbp;
		if (_pack_id == null || _pack_id <= 0){
			dbp = BeanDebetPack.create();
		} else {
			dbp = new BeanDebetPack(_pack_id);
		}
		
		final JTextField fId = new JTextField(dbp.getId().toString(), 10);
		final JFormattedTextField fDate = new JFormattedTextField(new SimpleDateFormat("dd.MM.yyyy"));
		fDate.setValue(dbp.getPdate());
//		fDate.setPreferredSize(fId.getPreferredSize());
		
		final DefaultComboBoxModel<BeanOrg> cBoxModel = new DefaultComboBoxModel<BeanOrg>();
		final JComboBox<BeanOrg> cOrg = new JComboBox<BeanOrg>();
				
		ResultSet rs;
		
    	String query = "select id, name from gks.orgs ORDER BY name ";
        try{
        	rs = DbUtils.Conn.createStatement().executeQuery(query);
        	while(rs.next()){
        		cBoxModel.addElement(new BeanOrg(
    				rs.getInt("id"), 
    				rs.getString("name")));
        	}
        	rs.close();
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, e.getMessage() + "\n" + query, "WinDebet.DefaultComboBoxModel", JOptionPane.ERROR_MESSAGE);
	    }
		cBoxModel.setSelectedItem(cBoxModel.getElementAt(dbp.getOrg_id()));
		cOrg.setModel(cBoxModel);
        
		final JTextPane fDesc = new JTextPane();
		fDesc.setText(dbp.getDescription());
		
		final JPanel pCash = new JPanel();
		final JPanel pAdd = new JPanel();

		final JTextField fAddDate = new JTextField(10);
		final JTextField fAddSumm = new JTextField(10);
		final JTextField fAddPena = new JTextField(10);
		final JButton bAdd = new JButton("Добавить");

		final JTable tblList = new JTable();
		
		fDate.setValue(Calendar.getInstance().getTime());
		setLayout(new GridBagLayout());

        add(new JLabel("Номер:"), 	new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        add(fId, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        add(new JLabel("Дата:"), 	new GridBagConstraints(2, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        add(fDate, new GridBagConstraints(3, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        add(new JLabel("Организация:"), 	new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        add(cOrg, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 3, 1, 1, 1,
	            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        add(new JLabel("Описание пакета:"), 	new GridBagConstraints(0, GridBagConstraints.RELATIVE, 4, 1, 1, 1,
	            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
        add(new JScrollPane(fDesc), new GridBagConstraints(0, GridBagConstraints.RELATIVE, 4, 1, 1, 1,
	            GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 5, 5, 5), 0, 0));
        
        pCash.setBorder(BorderFactory.createTitledBorder("Ввод и печать кассовой квитанции"));
        pCash.add(new JButton("Поиск и внесение кассовой квитанции"));
        add(pCash, new GridBagConstraints(0, GridBagConstraints.RELATIVE, 4, 1, 1, 1,
	           GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        
        pAdd.setBorder(BorderFactory.createTitledBorder("Добавление записи"));
        pAdd.setLayout(new GridBagLayout());
        pAdd.add(new JLabel("Дата"), 	new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
        pAdd.add(new JLabel("Сумма"), 	new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
        pAdd.add(new JLabel("Пеня"), 	new GridBagConstraints(2, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
        
        pAdd.add(bAdd, 	new GridBagConstraints(3, GridBagConstraints.RELATIVE, 1, 2, 1, 1,
	            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        
        pAdd.add(fAddDate, 	new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 5, 5), 0, 0));
        pAdd.add(fAddSumm, 	new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 5, 5), 0, 0));
        pAdd.add(fAddPena, 	new GridBagConstraints(2, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 5, 5), 0, 0));
        
        add(pAdd, new GridBagConstraints(0, GridBagConstraints.RELATIVE, 4, 1, 1, 1,
 	           GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        
        tblList.setModel(new DebetTableModel(dbp.getId()));
        add(new JScrollPane(tblList), new GridBagConstraints(0, GridBagConstraints.RELATIVE, 4, 1, 1, 1,
	            GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 5, 5, 5), 0, 0));
        
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	// Услуги	
		private class DebetTableModel extends AbstractTableModel{
		
			private List<BeanDebet> beans;
			
			public DebetTableModel(Integer pack_id){
				beans = new ArrayList<BeanDebet>();
				ResultSet rs;
				
				String strQuery = "SELECT id, acc_id, srv_id, since, finish, coef, description, abb, kind, org_id, queue, isod, cnt_id FROM gks.v_service_applications WHERE acc_id =" + pack_id;
				
				try {
					rs = DbUtils.Conn.createStatement().executeQuery(strQuery);
					while(rs.next()){
						BeanDebet b = new BeanDebet(
								rs.getInt("id"),
								rs.getInt("pack_id"),
								rs.getInt("acc_id"),
								rs.getInt("srv_id"),
								rs.getDouble("sum"), 
								rs.getDouble("pen"), 
								rs.getTimestamp("since"),
								rs.getString("oper"), 
								rs.getTimestamp("finish"),
								rs.getString("cont"), 
								rs.getString("description"));
						beans.add(b);
					}
					rs.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage() + "\n" + strQuery,  "DebetTableModel" , JOptionPane.ERROR_MESSAGE);
				}
			}
			
			@Override
			public String getColumnName(int columnIndex) {
				switch (columnIndex) {
					case 0:
						return "№";
					case 1:
						return "ЛСчёт";
					case 2:
						return "Сумма";
					case 3:
						return "Пеня";
					case 4:
						return "Время";
					case 5:
						return "Пометка";
					}
				return "";
			}

			@Override
			public int getColumnCount() {
				return 6;
			}

			@Override
			public int getRowCount() {
				return beans.size();
			}

			@Override
			public Object getValueAt(int rowindex, int columnIndex) {
				switch (columnIndex) {
				case 0:
					return beans.get(rowindex).getId();
				case 1:
					return beans.get(rowindex).getAcc_id();
				case 2:
					return beans.get(rowindex).getSum();  
				case 3:
					return beans.get(rowindex).getPen();
				case 4:
					return beans.get(rowindex).getSince();
				case 5:
					return beans.get(rowindex).getDescription();
				}
			return "";
				
			}
	    
			@Override
			public Class<?> getColumnClass(int c) {
				if (getValueAt(0, c) == null) return String.class;
//				System.out.println(c +": "+ getValueAt(0, c).getClass() + " " + getValueAt(0, c));
		        return getValueAt(0, c).getClass();
		    }
			
			public BeanDebet getBean (int rowindex){
				return beans.get(rowindex);
			}
	/*	    public boolean isCellEditable(int row, int col)
	        { 
		    	return true; 
		    }
		    public void setValueAt(Object value, int row, int col) {
		    	rowData[row][col] = value;
		    	fireTableCellUpdated(row, col);
		    }
	*/	}

}
