 package main;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class WinPrice extends JFrame{
	private JComboBox<BeanService> cSrv = new JComboBox<BeanService>();
	
	public WinPrice(Integer _srv_id) throws HeadlessException {
		super("Квартплата: цены");
		
		final DefaultComboBoxModel<BeanService> cBoxModel = new DefaultComboBoxModel<BeanService>(); 
		final DefaultListModel<BeanPrice> listModel = new DefaultListModel<BeanPrice>();   
		final JList<BeanPrice> lPrice = new JList<BeanPrice>();
		final JFormattedTextField fDate = new JFormattedTextField(new SimpleDateFormat("dd.MM.yyyy"));
		final JTextField fSum = new JTextField(10);
		fDate.setPreferredSize(fSum.getPreferredSize());
		final JButton bAdd = new JButton("Добавить");
		final JButton bDel = new JButton("Удалить");
    	
		fDate.setValue(Calendar.getInstance().getTime());
		
		ResultSet rs;
		
    	String query = "select id, abb, description, kind, org_id  from gks.services " +
    		((_srv_id != 0)?"WHERE id = "+ _srv_id:"");
        try{
        	rs = DbUtils.Conn.createStatement().executeQuery(query);
        	while(rs.next()){
        		cBoxModel.addElement(new BeanService(
    				rs.getInt("id"), 
    				rs.getString("abb"), 
    				rs.getString("description"), 
    				rs.getInt("kind"), 
    				rs.getInt("org_id")));
        	}
        	rs.close();
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, e.getMessage(), "WinPrice.DefaultComboBoxModel", JOptionPane.ERROR_MESSAGE);
	    }
	    
	    cSrv.setModel(cBoxModel);
		refill(listModel);
		lPrice.setModel(listModel);
		lPrice.setPreferredSize(new Dimension(100, 100));
		lPrice.setBorder(BorderFactory.createEtchedBorder());
		cSrv.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
					refill(listModel);
			}
		});
		
		bAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
	        	 try {
	        		 String strSql = "INSERT INTO gks.prices(srv_id, date, sum) SELECT " 
	        				 + ((BeanService) cSrv.getSelectedItem()).getId()
	        				 + ", '" + fDate.getValue() + "'"
	        				 + ", " + fSum.getText().replace(",", ".");
	        		 if ( JOptionPane.showConfirmDialog( WinPrice.this, strSql, "Добавить цену?", 
	        				 JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION ) {
	        			 DbUtils.Conn.createStatement().executeUpdate(strSql);
	        			 refill(listModel);
	        		 }
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "bAdd: Ошибка SQL", JOptionPane.ERROR_MESSAGE);
				} 
			}
		});
		bDel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
	        	 try {
	        		 String strSql = "DELETE FROM gks.prices WHERE id = " + 
	        				 lPrice.getModel().getElementAt(lPrice.getSelectedIndex()).getId(); 
	        		 if ( JOptionPane.showConfirmDialog( WinPrice.this, strSql, "Удалить цену?", 
	        				 JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION ) {
	        			 DbUtils.Conn.createStatement().executeUpdate(strSql);
	        			 refill(listModel);
	        		 }
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "bDel: Ошибка SQL", JOptionPane.ERROR_MESSAGE);
				} 
			}
		});
		
		setLayout(new GridBagLayout());
        add(cSrv, new GridBagConstraints(0, GridBagConstraints.RELATIVE, 3, 1, 1, 1,
	            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        
        add(new JLabel("Дата"), new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 1, 0,
	            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
        add(fDate, new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 1, 0,
	            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 5, 5), 0, 0));
        
        add(new JLabel("Цена"), new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 1, 0,
	            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
        add(fSum, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 1, 0,
	            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 5, 5), 0, 0));
        
        add(bAdd, new GridBagConstraints(2, GridBagConstraints.RELATIVE, 1, 2, 1, 1,
	            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        
        add(new JLabel("Цены"), new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 1, 0,
	            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
        add(lPrice, new GridBagConstraints(0, GridBagConstraints.RELATIVE, 3, 1, 1, 1,
	            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 5, 5), 0, 0));
        add(bDel, new GridBagConstraints(0, GridBagConstraints.RELATIVE, 3, 1, 1, 1,
	            GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        
       pack();
       setLocationRelativeTo(null);
       setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private void refill(DefaultListModel<BeanPrice> listModel) {
    	ResultSet rs;
    	
        listModel.removeAllElements();
    	String query = "SELECT id, srv_id, date, sum::NUMERIC(10, 2) FROM gks.prices WHERE srv_id=" + ((BeanService) cSrv.getSelectedItem()).getId(); 

        try{
        	rs = DbUtils.Conn.createStatement().executeQuery(query);
        	while(rs.next()){
        		listModel.addElement(new BeanPrice(
    				rs.getInt("id"),
    				rs.getInt("srv_id"),
    				rs.getDate("date"),
    				rs.getDouble("sum"))
        		);
        	}
        	rs.close();
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, e.getMessage(), "WinPrice.refill SQLException", JOptionPane.ERROR_MESSAGE);
       }
	}
	
}
