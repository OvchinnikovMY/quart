package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

public class WinCounter extends JFrame{

	public WinCounter(final Integer id){
		super("Квартплата: счётчик");
		

		setLayout(new GridBagLayout());
		
		final JTextField fId = new JTextField(5);
		fId.setEditable(false);
		add(new JLabel("Код:"), 
				new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		add(fId, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

		final JTextField fNum = new JTextField(10);
		fNum.setEditable(false);
		add(new JLabel("Номер счётчика:"), 
				new GridBagConstraints(2, GridBagConstraints.RELATIVE, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		add(fNum, new GridBagConstraints(3, GridBagConstraints.RELATIVE, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
		final JTextField fAcc = new JTextField(10);
		fAcc.setEditable(false);
		add(new JLabel("Подключен к лицевому счёту:"), 
				new GridBagConstraints(0, GridBagConstraints.RELATIVE, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0)); 
		add(fAcc, new GridBagConstraints(2, GridBagConstraints.RELATIVE, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

		final JTextField fSrv = new JTextField(10);
		fSrv.setEditable(false);
		add(new JLabel("Подключен к услуге:"), 
				new GridBagConstraints(0, GridBagConstraints.RELATIVE, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0)); 
		add(fSrv, new GridBagConstraints(2, GridBagConstraints.RELATIVE, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
		final JPanel pAdd = new JPanel();
		pAdd.setLayout(new GridBagLayout());
		pAdd.setBorder(BorderFactory.createTitledBorder("[ Добавление показаний ]"));

		final JTextField fDate = new JTextField("");
		final JTextField fValue = new JTextField("");
		final JCheckBox chNew = new JCheckBox(""); 
		final JButton bAdd = new JButton("Добавить"); 
		
		pAdd.add(new JLabel("Дата"), 
				new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 
						new Insets(5, 0, 0, 0), 0, 0));		
		pAdd.add(new JLabel("Значение"), 
				new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 
						new Insets(5, 0, 0, 0), 0, 0));		
		pAdd.add(new JLabel("Установка?"), 
				new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 
						new Insets(5, 0, 0, 0), 0, 0));		
		pAdd.add(bAdd, new GridBagConstraints(3, 0, 1, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, 
				new Insets(0, 0, 0, 0), 0, 0));	
		
		pAdd.add(fDate, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 
				new Insets(0, 0, 0, 0), 0, 0));		
		pAdd.add(fValue, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 
				new Insets(0, 0, 0, 0), 0, 0));		
		pAdd.add(chNew, new GridBagConstraints(2, 1, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, 
				new Insets(0, 0, 0, 0), 0, 0));		

		add(pAdd, new GridBagConstraints(0, GridBagConstraints.RELATIVE, 4, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
				new Insets(0, 0, 0, 0), 0, 0));

		final JTabbedPane tPane = new JTabbedPane();
		final JTable tblReadings = new JTable();
		final JTable tblChecks = new JTable();
		final JTable tblValues = new JTable();
		
		tblReadings.setModel(new ReadingsTableModel(id));
		tblValues.setModel(new ValuesTableModel(id));
		
    	final JPopupMenu popup = new JPopupMenu();
        JMenuItem menuItem1 = new JMenuItem("Обновить");
        popup.add(menuItem1);
        popup.addSeparator();
        JMenuItem menuItem2 = new JMenuItem("Удалить показание");
        popup.add(menuItem2);
//        menuItem2.addActionListener(new SrvStopActionListener());
        
        tblReadings.setComponentPopupMenu(popup);
        tblReadings.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {
                int row = tblReadings.rowAtPoint(e.getPoint());
                int column = tblReadings.columnAtPoint(e.getPoint());
                if (row >= 0 && column >= 0) {
                    ((JTable)e.getSource()).changeSelection(row, column, false, false);
                }
			}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {}
		});        
		
		bAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
	        	 try {
	        		 String strSql = "INSERT INTO gks.counter_values(cnt_id, checkdate, isnew, reading) SELECT " 
	        				 + id.toString() 
	        				 + ", '" + fDate.getText() + "'"
	        				 + ", " + chNew.isSelected() 
	        				 + ", '" + fValue.getText() + "'";
	        		 if ( JOptionPane.showConfirmDialog( WinCounter.this, strSql, "Добавить показание?", 
	        				 JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION ) {
	        			 DbUtils.Conn.createStatement().executeUpdate(strSql);
	        			 tblReadings.setModel(new ReadingsTableModel(id));
	        		 }
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "btnAdd: Ошибка SQL", JOptionPane.ERROR_MESSAGE);
				} 
			}
		});

		tPane.add(new JScrollPane(tblReadings), "Показания");
		tPane.add(new JScrollPane(tblValues), "Расходы");
		tPane.add(new JScrollPane(tblChecks), "Поверки");
		//tPane.setTabPlacement(JTabbedPane.BOTTOM);
/*		tPane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				
				JOptionPane.showMessageDialog(WinCounter.this, tPane.getSelectedIndex(),  "2" , JOptionPane.ERROR_MESSAGE);
			}
		} );
*/		add(tPane, new GridBagConstraints(0, GridBagConstraints.RELATIVE, 4, 2, 1, 5, 
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
				new Insets(5, 5, 5, 5), 0, 0)); 
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

// Показания
	private class ReadingsTableModel extends AbstractTableModel {
			
		private List<BeanCounterValue> beans;
		
		public ReadingsTableModel(Integer _cnt_id){
			beans = new ArrayList<BeanCounterValue>();
			ResultSet rs;
			try {
				rs = DbUtils.Conn.createStatement().executeQuery("SELECT id, cnt_id, checkdate, isnew, reading, auto FROM gks.counter_values WHERE cnt_id=" + _cnt_id);
				while(rs.next()){
					BeanCounterValue b = new BeanCounterValue( 
						rs.getInt("id"),
						rs.getInt("cnt_id"),
						rs.getDate("checkdate"),
						rs.getDouble("reading"),
						rs.getBoolean("isnew"),
						rs.getBoolean("auto")
						);
					beans.add(b);
				}
				rs.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(WinCounter.this, e.getMessage(),  "ReadingsTableModel" , JOptionPane.ERROR_MESSAGE);
			}
		}

		@Override
		public int getColumnCount() {
			return 3;
		}

		@Override
		public String getColumnName(int columnIndex) {
			switch (columnIndex) {
				case 0:
					return "Дата";
				case 1:
					return "Показание";
				case 2:
					return "Установка?";
				}
			return "";
		}
	
		@Override
		public int getRowCount() {
			return beans.size();
		}
	
		@Override
		public Object getValueAt(int rowindex, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return beans.get(rowindex).getCheckdate();
			case 1:
				return beans.get(rowindex).getReading();
			case 2:
				return (beans.get(rowindex).getIsnew())?"Да":"";
			}
		return "";
		}
		
		@Override
		public Class<?> getColumnClass(int c) {
	        return getValueAt(0, c).getClass();
	    }
		
//		public BeanCounterValue getBean (int rowindex){
//			return beans.get(rowindex);
//		}
	}

// Объёмы
	private class ValuesTableModel extends AbstractTableModel {
			
		private List<BeanCounterValue> beans;
		
		public ValuesTableModel(Integer _cnt_id){
			beans = new ArrayList<BeanCounterValue>();
			ResultSet rs;
			try {
				rs = DbUtils.Conn.createStatement().executeQuery("SELECT cnt_id, checkdate, gks.f_get_counter_value(cnt_id, checkdate) as value FROM gks.counter_values WHERE cnt_id=" + _cnt_id);
				while(rs.next()){
					BeanCounterValue b = new BeanCounterValue( 
						new Integer(0),
						rs.getInt("cnt_id"),
						rs.getDate("checkdate"),
						rs.getDouble("value"),
						false,
						false
						);
					beans.add(b);
				}
				rs.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(WinCounter.this, e.getMessage(),  "ReadingsTableModel" , JOptionPane.ERROR_MESSAGE);
			}
		}

		@Override
		public int getColumnCount() {
			return 2;
		}

		@Override
		public String getColumnName(int columnIndex) {
			switch (columnIndex) {
				case 0:
					return "Дата";
				case 1:
					return "Показание";
				}
			return "";
		}
	
		@Override
		public int getRowCount() {
			return beans.size();
		}
	
		@Override
		public Object getValueAt(int rowindex, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return beans.get(rowindex).getCheckdate();
			case 1:
				return beans.get(rowindex).getReading();
			}
		return "";
		}
	}
}
