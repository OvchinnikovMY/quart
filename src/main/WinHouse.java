package main;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

//import ListSample.ListSample.DataBean;


public class WinHouse extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JList<HouseDataBean> list = new JList<HouseDataBean>();
    JTextField fId = new JTextField("", 12);
    JTextField fStreet = new JTextField();
    JTextField fNum = new JTextField();
    JTextField fStair = new JTextField();
    JTextField fFooter = new JTextField();
    JTextField fSqTotal = new JTextField();

    private void refill(DefaultListModel<HouseDataBean> listModel ) {
    	ResultSet rs;
    	
        listModel.removeAllElements();
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
    	   	System.out.println("HouseListModel"+e.getMessage());
       }
	}
    
    public WinHouse(){
        super("Квартплата: список домов");

        final DefaultListModel<HouseDataBean> listModel = new DefaultListModel<HouseDataBean>();
        this.refill(listModel);
        
        list.setModel(listModel);
        
        list.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.getSelectionModel().addListSelectionListener(
            new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent event) {
                    if (event.getValueIsAdjusting()) {
                        return;
                    }
                    if (list.getSelectedValue() != null) {
                        SwingUtilities.invokeLater(
                            new Runnable() {
                                public void run() {
                                	HouseDataBean selectedDataBean = (HouseDataBean)list.getSelectedValue();
                					if (list.getSelectedIndex() != -1){
                						fId.setText(selectedDataBean.getId().toString()); 
                						fStreet.setText(selectedDataBean.getName()); 
                						fNum.setText(selectedDataBean.getNum()); 
                						fStair.setText(selectedDataBean.getSqstair().toString()); 
                						fFooter.setText(selectedDataBean.getSqfooter().toString() );
                				        try {
                				           	ResultSet rs = DbUtils.Conn.createStatement().executeQuery("SELECT sum FROM gks.v_house_sql_current WHERE hou_id="+selectedDataBean.getId().toString());
                				        	rs.next();
                				        	fSqTotal.setText( (new Double(rs.getDouble("sum"))).toString() );                				        	
                				        	rs.close();
                						} catch (SQLException e) {
                							// TODO: handle exception
                						}
                 					} else {
                						fId.setText(""); 
                						fStreet.setText(""); 
                						fNum.setText(""); 
                						fStair.setText(""); 
                						fFooter.setText("");
                						fSqTotal.setText("");
                					}
                                }
                            });
                    } 
                }
            });
        
        setLayout(new GridBagLayout());
        
        JScrollPane scroll = new JScrollPane(list);
        scroll.setPreferredSize(new Dimension(200, 300));
        
        add(scroll, new GridBagConstraints(0, 0, 1, 7, 1, 1,
	            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        JLabel lId = new JLabel("Код дома:");
        add(lId, new GridBagConstraints(1, 0, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
 //       fId.setSize(20);
        fId.setEditable(false);
        fId.setHorizontalAlignment(JTextField.RIGHT);
        add(fId, new GridBagConstraints(2, 0, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        
        JLabel lStreet = new JLabel("Улица:");
        add(lStreet, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        add(fStreet, new GridBagConstraints(2, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        JLabel lNum = new JLabel("Номер дома:");
        add(lNum, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        add(fNum, new GridBagConstraints(2, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        JLabel lStair = new JLabel("Площадь лестниц и коридоров:");
        add(lStair, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        add(fStair, new GridBagConstraints(2, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        fStair.setHorizontalAlignment(JTextField.RIGHT);
        
        JLabel lFooter = new JLabel("Площадь подвалов:");
        add(lFooter, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        add(fFooter, new GridBagConstraints(2, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        fFooter.setHorizontalAlignment(JTextField.RIGHT);
        
        JLabel lSqTotal = new JLabel("Общая площадь квартир:");
        add(lSqTotal, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        add(fSqTotal, new GridBagConstraints(2, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        fSqTotal.setHorizontalAlignment(JTextField.RIGHT);
        fSqTotal.setEditable(false);

        JButton btnAdd = new JButton("Добавить!");
        //btnAdd.setPreferredSize(btnAdd.getPreferredSize());
        add(btnAdd, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 1, 1, GridBagConstraints.SOUTHEAST,
            GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
	         public void actionPerformed(ActionEvent e)
	         {
	        	 try {
	        		 if ( JOptionPane.showConfirmDialog(null, "Добавить информацию о доме?", "Добавление", 
	        				 JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION ) {
	        			 DbUtils.Conn.createStatement().executeUpdate("INSERT INTO gks.houses(street, num, sqstair, sqfooter) SELECT '" 
	        					 + fStreet.getText() 
	        					 + "', '" + fNum.getText()
	        					 + "', " + fStair.getText() 
	        					 + ", " + fFooter.getText()); 
	        			 refill(listModel);
	        		 }
       		 
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
	         }
	        });
        
        JButton btnSave = new JButton("Сохранить");
        add(btnSave, new GridBagConstraints(2, GridBagConstraints.RELATIVE, 1, 1, 1, 1, GridBagConstraints.SOUTHEAST,
            GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
	         public void actionPerformed(ActionEvent e)
	         {
	        	 try {
        		 if ( JOptionPane.showConfirmDialog(null, "Исправить информацию о доме?", "Изменение", 
	        				 JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION ) {
	        			 DbUtils.Conn.createStatement().executeUpdate("UPDATE gks.houses SET street='" + fStreet.getText() 
							+ "', num='" + fNum.getText()
							+ "', sqstair=" + fStair.getText() 
							+ ", sqfooter=" + fFooter.getText() 
							+ " WHERE id = " + fId.getText());
 						refill(listModel);
	        		 }
	        		 
				} catch (SQLException e1) {
					System.out.println("actionPerformed" + e1.getMessage());
				}
	         }
	        });
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
