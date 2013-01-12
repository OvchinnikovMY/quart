package main;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class WinService extends JFrame {
	
    public WinService(){
        super("Квартплата: услуги");

        final DefaultListModel<BeanService> listModel = new DefaultListModel<BeanService>();
    	
    	final JList<BeanService> list = new JList<BeanService>();
    	final JTextField fId = new JTextField("", 12);
        final JTextField fAbb = new JTextField("", 12);
        final JTextField fDesc = new JTextField("", 20);

        final ButtonGroup bGroup = new ButtonGroup();
    	final JRadioButton r0 = new JRadioButton("на лицевой счёт");  
    	final JRadioButton r1 = new JRadioButton("на общую площадь"); 
    	final JRadioButton r2 = new JRadioButton("на кол-во проживающих"); 

    	final JButton bPrice = new JButton("Цена"); 
        final JButton btnAdd = new JButton("Добавить");
        final JButton btnSave = new JButton("Сохранить");
        
        bPrice.setEnabled(false);
        
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
                                	BeanService selectedBeanService = (BeanService)list.getSelectedValue();
                					if (list.getSelectedIndex() != -1){
                						fId.setText(selectedBeanService.getId().toString()); 
                						fAbb.setText(selectedBeanService.getAbb()); 
                						fDesc.setText(selectedBeanService.getDescription()); 
               						
                						System.out.println(selectedBeanService.getKind());
                						
                						switch ((Integer) selectedBeanService.getKind()) {
                							case 0:
                								r0.setSelected(true);
                								break;
                							case 1:
                								r1.setSelected(true);
                								break;
                							case 2:
                								r2.setSelected(true);
                								break;
                						}
                						bPrice.setEnabled(true);
                 					} else {
                 						fId.setText("");
                						fAbb.setText(""); 
                						fDesc.setText("");
                						r0.setSelected(true);
                						bPrice.setEnabled(false);
                					}
                                }
                            });
                    } 
                }
            });
        
        setLayout(new GridBagLayout());
        
        JScrollPane scroll = new JScrollPane(list);
        scroll.setPreferredSize(new Dimension(300, 300));
        
        add(scroll, new GridBagConstraints(0, 0, 1, 10, 1, 0,
	            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        add(new JLabel("Код:"), new GridBagConstraints(1, 0, 1, 1, 1, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        add(fId, new GridBagConstraints(2, 0, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        add(new JLabel("Аббревиатура:"), new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 1, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        fId.setEditable(false);
        fId.setHorizontalAlignment(JTextField.RIGHT);
        add(fAbb, new GridBagConstraints(2, GridBagConstraints.RELATIVE, 1, 1, 1, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        
        
        add(new JLabel("Название:"), new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 1, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        add(fDesc, new GridBagConstraints(2, GridBagConstraints.RELATIVE, 1, 1, 1, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        
	    bGroup.add(r0);
	    bGroup.add(r1);
	    bGroup.add(r2);

	    r1.setSelected(true);
       
        add(new JLabel("Тип начисления:"), new GridBagConstraints(1, GridBagConstraints.RELATIVE, 2, 1, 1, 0,
	            GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        add(r0, new GridBagConstraints(2, GridBagConstraints.RELATIVE, 2, 1, 1, 0,
	            GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
        add(r1, new GridBagConstraints(2, GridBagConstraints.RELATIVE, 2, 1, 1, 0,
	            GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        add(r2, new GridBagConstraints(2, GridBagConstraints.RELATIVE, 2, 1, 1, 0,
	            GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 5, 0), 0, 0));

        add(bPrice, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 2, 1, 1, 1,
	            GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        bPrice.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new WinPrice(new Integer(fId.getText())).setVisible(true);
			}
		});
       
        add(btnAdd, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 1, 0, GridBagConstraints.SOUTHEAST,
            GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
	         public void actionPerformed(ActionEvent e)
	         {
	        	 Integer r = new Integer(0);
	        	 r = 0+(r1.isSelected()?1:0) + (r2.isSelected()?2:0);
	        	 
	        	 try {
	        		 if ( JOptionPane.showConfirmDialog( null, "INSERT INTO gks.services(abb, description, kind) SELECT '" 
        					 + fAbb.getText() 
        					 + "', '" + fDesc.getText()
        					 + "', " + r, "Добавить услугу?", 
	        				 JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION ) {
	        			 DbUtils.Conn.createStatement().executeUpdate("INSERT INTO gks.services(abb, description, kind) SELECT '" 
	        					 + fAbb.getText() 
	        					 + "', '" + fDesc.getText()
	        					 + "', " + r); 
	        			 refill(listModel);
	        		     list.setModel(listModel);
	        		 }
       		 
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "btnAdd: Ошибка SQL", JOptionPane.ERROR_MESSAGE);
				}
	         }
	        });
        
        add(btnSave, new GridBagConstraints(2, GridBagConstraints.RELATIVE, 1, 1, 1, 0, GridBagConstraints.SOUTHEAST,
            GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
	         public void actionPerformed(ActionEvent e)
	         {
	        	 try {
        		 if ( JOptionPane.showConfirmDialog(null, "UPDATE gks.services SET abb='" + fAbb.getText() 
							+ "', description='" + fDesc.getText()
							+ "', kind=" + 0+(r1.isSelected()?1:0) + (r2.isSelected()?2:0) 
							+ " WHERE id = " + fId.getText(), "Измененить услугу?", 
	        				 JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION ) {
	        			 DbUtils.Conn.createStatement().executeUpdate("UPDATE gks.services SET abb='" + fAbb.getText() 
							+ "', description='" + fDesc.getText()
							+ "', kind=" + 0+(r1.isSelected()?1:0) + (r2.isSelected()?2:0)
							+ " WHERE id = " + fId.getText());
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
    
    private void refill(DefaultListModel<BeanService> listModel ) {
    	ResultSet rs;
    	
        listModel.removeAllElements();
    	String query = "select * from gks.services ORDER BY abb";
        try{
        	rs = DbUtils.Conn.createStatement().executeQuery(query);
        	while(rs.next()){
        		listModel.addElement(new BeanService(rs.getInt("id"), 
        				rs.getString("abb"), 
        				rs.getString("description"), 
        				rs.getInt("kind"), 
        				rs.getInt("org_id")));
        	}
        	rs.close();
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(WinService.this, e.getMessage(), "WinService.refil SQLException", JOptionPane.ERROR_MESSAGE);
       }
	}
}
