package main;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class WinAccountAdd extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WinAccountAdd(){
		super("Квартплата: добавление лицевого счёта");
		
	    final JTextField fId = new JTextField("", 12);
        final DefaultComboBoxModel<HouseDataBean> listModel = new DefaultComboBoxModel<HouseDataBean>();
        final JComboBox<HouseDataBean> cbHouse = new JComboBox<HouseDataBean>();
//	    listModel.addElement(new HouseDataBean(new Integer(1), "name", "num", new Double(2.3), new Double(4.5)));
    	ResultSet rs;
    	
    	String query = "select * from gks.houses ORDER BY street, num";
        try{
        	rs = DbUtils.Conn.createStatement().executeQuery(query);
        	while(rs.next()){
        		listModel.addElement(new HouseDataBean(rs.getInt("id"), 
        				rs.getString("street"), 
        				rs.getString("num"), 
        				rs.getDouble("sqstair"), 
        				rs.getDouble("sqfooter")));
        	}
        	rs.close();
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, e.getMessage(), "WinAccountAdd SQLException", JOptionPane.ERROR_MESSAGE);
	    }
	    
	    cbHouse.setModel(listModel);
	    
	    final JTextField fFlat = new JTextField("", 18);
	    final JFormattedTextField fSince = new JFormattedTextField(new SimpleDateFormat("dd.MM.yyyy"));
	    final JFormattedTextField fFinish = new JFormattedTextField(new SimpleDateFormat("dd.MM.yyyy"));
	    final JTextArea fCmt = new JTextArea(3, 18);
	    fSince.setValue(Calendar.getInstance().getTime());
	    
        setLayout(new GridBagLayout());
        
        add(new JLabel("Номер ЛС:"), new GridBagConstraints(0, 0, 1, 1, 0, 0,
	            GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        fId.setHorizontalAlignment(JTextField.RIGHT);
        add(fId, new GridBagConstraints(1, 0, 1, 1, 0, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        add(new JLabel("Адрес дома:"), new GridBagConstraints(0, 1, 1, 1, 0, 0,
	            GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        add(cbHouse, new GridBagConstraints(1, 1, 1, 1, 0, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        add(new JLabel("Номер квартиры:"), new GridBagConstraints(0, 2, 1, 1, 0, 0,
	            GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        fFlat.setHorizontalAlignment(JTextField.RIGHT);
        add(fFlat, new GridBagConstraints(1, 2, 1, 1, 0, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
 
        add(new JLabel("Дата начала начисления:"), new GridBagConstraints(0, 3, 1, 1, 0, 0,
	            GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        fSince.setHorizontalAlignment(JTextField.RIGHT);
        add(fSince, new GridBagConstraints(1, 3, 1, 1, 0, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
 
        add(new JLabel("Дата окончания начисления:"), new GridBagConstraints(0, 4, 1, 1, 0, 0,
	            GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        fFinish.setHorizontalAlignment(JTextField.RIGHT);
        add(fFinish, new GridBagConstraints(1, 4, 1, 1, 0, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
 
        add(new JLabel("Коментарий:"), new GridBagConstraints(0, 5, 1, 1, 0, 0,
	            GridBagConstraints.SOUTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        add(fCmt, new GridBagConstraints(0, 6, 2, 1, 0, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
 
        JButton btnAdd = new JButton("Добавить!");
        //btnAdd.setPreferredSize(btnAdd.getPreferredSize());
        
        add(btnAdd, new GridBagConstraints(1, 7, 1, 1, 1, 0, GridBagConstraints.SOUTHEAST,
            GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
	         public void actionPerformed(ActionEvent e)
	         {
	        	 HouseDataBean selectedDataBean = (HouseDataBean)cbHouse.getSelectedItem();
	        	 try {
	        		 if ( JOptionPane.showConfirmDialog(null, "Добавить лицевой счёт?", "Добавление", 
	        				 JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION ) {
	        			 System.out.println("INSERT INTO gks.accounts(acc, hou_id, flat, since, finish, cmt) SELECT " 
	        					 + fId.getText()
	        					 + ", " + selectedDataBean.getId()
	        					 + ", '" + fFlat.getText() + "'" 
	        					 + ", '" + fSince.getText() + "'"
	        					 + ((fFinish.getText().isEmpty())? ", null" :", '" + fFinish.getText() + "'")
	        					 + ", '" + fCmt.getText()+ "'");
	        			 
	        			 DbUtils.Conn.createStatement().executeUpdate("INSERT INTO gks.accounts(acc, hou_id, flat, since, finish, cmt) SELECT " 
	        					 + fId.getText()
	        					 + ", " + selectedDataBean.getId()
	        					 + ", '" + fFlat.getText() + "'" 
	        					 + ", '" + fSince.getText() + "'"
	        					 + ((fFinish.getText().isEmpty())? ", null" :", '" + fFinish.getText() + "'")
	        					 + ", '" + fCmt.getText()+ "'"); 
	        		 }
       		 
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
//	        	 JOptionPane.showMessageDialog(null, selectedDataBean.getId().toString() );
	         }
	        });
        
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
