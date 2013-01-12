package main;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class WinCounterList extends JFrame{
	
	public WinCounterList (){
		super("Квартплата:  список счётчиков");
	    final JTextField fId = new JTextField("", 18);
	    final JTextArea fCmt = new JTextArea(3, 18);
        final DefaultListModel<BeanCounter> listModel = new DefaultListModel<BeanCounter>();
        final JList<BeanCounter> list = new JList<BeanCounter>();
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
                                	BeanCounter selectedBean = (BeanCounter)list.getSelectedValue();
                					if (list.getSelectedIndex() != -1){
                						fId.setText(selectedBean.getId().toString()); 
                						fCmt.setText(selectedBean.getDescription()); 
                 					} else {
                 						fId.setText("");
                						fCmt.setText(""); 
                					}
                                }
                            });
                    } 
                }
            });
        
        setLayout(new GridBagLayout());
        
        JScrollPane scroll = new JScrollPane(list);
        //scroll.setSize(100);
        scroll.setPreferredSize(new Dimension(400, 400));
        scroll.setMinimumSize(new Dimension(400, 400));
        add(scroll, new GridBagConstraints(0, 0, 1, 7, 1, 1,
	            GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));

        add(new JLabel("Код:"), new GridBagConstraints(1, 0, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        fId.setEditable(false);
        fId.setHorizontalAlignment(JTextField.RIGHT);
        add(fId, new GridBagConstraints(2, 0, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        add(new JLabel("Описание:"), new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
//        fId.setSize(20);
//        fCmt.setPreferredSize(new Dimension(200, 200));
        fCmt.setLineWrap(true);
        add(fCmt, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 2, 2, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 5, 5), 0, 0));
        
        JButton btnRead = new JButton("Показания и поверки");
        add(btnRead, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 2, 1, 1, 1, GridBagConstraints.SOUTHEAST,
                GridBagConstraints.HORIZONTAL, new Insets(50, 5, 5, 5), 0, 0));
        btnRead.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
 				new WinCounter(list.getSelectedValue().getId()).setVisible(true);
			}
		});


        JButton btnAdd = new JButton("Добавить!");
        //btnAdd.setPreferredSize(btnAdd.getPreferredSize());
        add(btnAdd, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 1, 1, GridBagConstraints.SOUTHEAST,
            GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
	         public void actionPerformed(ActionEvent e)
	         {
	        	 try {
	        		 String strSql = "INSERT INTO gks.counters(description) SELECT '" + fCmt.getText() + "'";
	        		 if ( JOptionPane.showConfirmDialog( null, strSql, "Добавить Счётчик?", 
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
					String strSql = "UPDATE gks.counters SET description='" + fCmt.getText() + "' WHERE id = " + fId.getText();
					if ( JOptionPane.showConfirmDialog(null, strSql, "Измененить счётчик?", 
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

	private void refill(DefaultListModel<BeanCounter> listModel ) {
    	ResultSet rs;
    	
        listModel.removeAllElements();
    	String query = "select * from gks.counters ORDER BY id";
        try{
        	rs = DbUtils.Conn.createStatement().executeQuery(query);
        	while(rs.next()){
        		listModel.addElement(new BeanCounter(rs.getInt("id"), 
        				rs.getString("description"))
        		);
        	}
        	rs.close();
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, e.getMessage(), "WinCounterList.refill SQLException", JOptionPane.ERROR_MESSAGE);
       }
	}
	
}
