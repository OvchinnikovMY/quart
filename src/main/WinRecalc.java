package main;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class WinRecalc extends JFrame{

	public WinRecalc() {
		super();
	    final JTextField fId = new JTextField("", 18);
	    final JFormattedTextField fDate = new JFormattedTextField(new SimpleDateFormat("dd.MM.yyyy"));
	    final JTextPane fCmt = new JTextPane();
        final DefaultListModel<BeanRecalcs> listModel = new DefaultListModel<BeanRecalcs>();
        final JList<BeanRecalcs> list = new JList<BeanRecalcs>();
        
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
                                	BeanRecalcs selectedBean = (BeanRecalcs)list.getSelectedValue();
                					if (list.getSelectedIndex() != -1){
                						fId.setText(selectedBean.getId().toString());
                						fDate.setValue(selectedBean.getRdate());
                						fCmt.setText(selectedBean.getDescription()); 
                 					} else {
                 						fId.setText("");
                 						fDate.setText("");
                						fCmt.setText(""); 
                					}
                                }
                            });
                    } 
                }
            });
        
        setLayout(new GridBagLayout());
        
        JScrollPane scroll = new JScrollPane(list);
        scroll.setPreferredSize(new Dimension(300, 300));
//        scroll.setMaximumSize(new Dimension(100, 100));
        add(scroll, new GridBagConstraints(0, 0, 1, 10, 1, 1,
	            GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));

        add(new JLabel("Код:"), new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        fId.setEditable(false);
        fId.setHorizontalAlignment(JTextField.RIGHT);
        add(fId, new GridBagConstraints(2, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        add(new JLabel("Дата перерасчёта:"), new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        fDate.setHorizontalAlignment(JTextField.RIGHT);
        add(fDate, new GridBagConstraints(2, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        add(new JLabel("Описание:"), new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
        fCmt.setPreferredSize(new Dimension(100, 100));
//        fCmt.setLineWrap(true);
        add(new JScrollPane(fCmt), new GridBagConstraints(1, GridBagConstraints.RELATIVE, 2, 2, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 5, 5), 0, 0));
        
        JButton btnMoney = new JButton("Перерасчёт суммой");
        add(btnMoney, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 2, 1, 1, 1, GridBagConstraints.SOUTHEAST,
                GridBagConstraints.NONE, new Insets(50, 5, 5, 5), 0, 0));

        JButton btnSrv = new JButton("Перерасчёт по услуге");
        add(btnSrv, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 2, 1, 1, 1, GridBagConstraints.SOUTHEAST,
                GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

        JButton btnPes = new JButton("Изменение поголовья проживающих");
        add(btnPes, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 2, 1, 1, 1, GridBagConstraints.SOUTHEAST,
                GridBagConstraints.NONE, new Insets(5, 5, 50, 5), 0, 0));
        btnMoney.setPreferredSize(btnPes.getPreferredSize());
        btnSrv.setPreferredSize(btnPes.getPreferredSize()); 
        
        JButton btnAdd = new JButton("Добавить!");
        add(btnAdd, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 1, 1, GridBagConstraints.SOUTHEAST,
            GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
	         public void actionPerformed(ActionEvent e)
	         {
	        	 try {
	        		 String strSql = "INSERT INTO gks.recalcs(rdate, description) SELECT " 
	        				 + "'" +fDate.getValue() + "', "
	        				 + "'" +fCmt.getText() + "'";
	        		 if ( JOptionPane.showConfirmDialog( null, strSql, "Добавить Перерасчёт?", 
	        				 JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION ) {
	        			 DbUtils.Conn.createStatement().executeUpdate(strSql); 
	        			 refill(listModel);
	        		     list.setModel(listModel);
	        		 }
       		 
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "btnAdd: Ошибка SQL", JOptionPane.ERROR_MESSAGE);
				}
	         }
	        });
        
        JButton btnSave = new JButton("Сохранить");
        add(btnSave, new GridBagConstraints(2, GridBagConstraints.RELATIVE, 1, 1, 1, 1, GridBagConstraints.SOUTHEAST,
            GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
	         public void actionPerformed(ActionEvent e)
	         {
	        	 try {
					String strSql = "UPDATE gks.recalcs SET rdate='" + fDate.getValue() + "', " +
							"description='" + fCmt.getText() + "' WHERE id = " + fId.getText();
					if ( JOptionPane.showConfirmDialog(null, strSql, "Измененить Перерасчёт?", 
							JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION ) {
						System.out.println(DbUtils.Conn.createStatement().executeUpdate(strSql));
					//	        			 System.out.println(sqlStr);
						refill(listModel);
						list.setModel(listModel);
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "btnSave: Ошибка SQL", JOptionPane.ERROR_MESSAGE);
				}
	         }
	        });

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private void refill(DefaultListModel<BeanRecalcs> listModel ) {
    	ResultSet rs;
    	
        listModel.removeAllElements();
    	String query = "SELECT id, rdate, description FROM gks.recalcs ORDER BY id";
        try{
        	rs = DbUtils.Conn.createStatement().executeQuery(query);
        	while(rs.next()){
        		listModel.addElement(new BeanRecalcs(
    				rs.getInt("id"),
    				rs.getDate("rdate"),
    				rs.getString("description"))
        		);
        	}
        	rs.close();
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, e.getMessage(), "WinRecalc.refill SQLException", JOptionPane.ERROR_MESSAGE);
       }
	}
}
