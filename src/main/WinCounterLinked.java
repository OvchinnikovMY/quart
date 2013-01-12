package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

public class WinCounterLinked extends JFrame{

	public WinCounterLinked() throws HeadlessException {
		super("Квартплата: состояние счётчиков");
		final JPanel pFilter = new JPanel();
        pFilter.setBorder(BorderFactory.createTitledBorder("<html><i>Отбор по количеству месяцев</i></html>"));

        final JLabel lMon = new JLabel("Непереданных месяцев:");
		final JTextField fMon = new JTextField(12);
	
		final JButton bFilter = new JButton("Фильтр");
		final JTable tblInfo = new JTable();
		tblInfo.setModel(new ConterLinkedTableModel());
//		tblInfo.setPreferredSize(new Dimension(200, 200));
		
		final JButton bSet = new JButton("Среднее значение");
		final JButton bStop = new JButton("По норме");
		bStop.setPreferredSize(bSet.getPreferredSize());
		
        setLayout(new GridBagLayout());
		
        pFilter.add(lMon);
        pFilter.add(fMon);
        pFilter.add(bFilter);
        add(pFilter, new GridBagConstraints(0, GridBagConstraints.RELATIVE, 2, 1, 1, 1,
	            GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));
        
        add(new JScrollPane(tblInfo), new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 4, 1, 1,
	            GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));
        add(bSet, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 0, 0,
	            GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        add(bStop, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 0, 0,
	            GridBagConstraints.NORTH , GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	// Услуги	
	private class ConterLinkedTableModel extends AbstractTableModel{
		
		private List<BeanCounterLinked> beans;
		
		public ConterLinkedTableModel(){
			beans = new ArrayList<BeanCounterLinked>();
			ResultSet rs;
			try {
				rs = DbUtils.Conn.createStatement().executeQuery("SELECT cnt_id, acc_id, address, srv_name, isod, description FROM gks.v_counters_linked");
				while(rs.next()){
					BeanCounterLinked b = new BeanCounterLinked(
						rs.getInt("cnt_id"),
						rs.getInt("acc_id"),
						rs.getString("address"),
						rs.getString("srv_name"),
						rs.getBoolean("isod"),
						rs.getString("description")
						);
					beans.add(b);
				}
				rs.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),  "ServicesTableModel" , JOptionPane.ERROR_MESSAGE);
			}
		}
		
		@Override
		public String getColumnName(int columnIndex) {
			switch (columnIndex) {
				case 0:
					return "Код счётчика";
				case 1:
					return "Лицевой счёт";
				case 2:
					return "Адрес";
				case 3:
					return "Услуга";
				case 4:
					return "Тип";
				case 5:
					return "Описание";
				}
			return "";
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 6;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return beans.size();
		}

		@Override
		public Object getValueAt(int rowindex, int columnIndex) {
			// TODO Auto-generated method stub
			switch (columnIndex) {
			case 0:
				return beans.get(rowindex).getCnt_id();
			case 1:
				return beans.get(rowindex).getAcc_id();
			case 2:
				return beans.get(rowindex).getAddress();
			case 3:
				return beans.get(rowindex).getSrv_name();
			case 4:
				return (beans.get(rowindex).getIsod())?"Да":"";
			case 5:
				return beans.get(rowindex).getDescription();
			}
		return "";
			
		}
		
	}


}
