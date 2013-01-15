package main;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
//import javax.swing.UIManager.*;

 

public class win_main extends JFrame{
	public static String strConn;
	
    public static void main(String[] args) {
    	
//        JFrame.setDefaultLookAndFeelDecorated(true);

    	new WinLogin().setVisible(true); 
        new win_main().setVisible(true);
    }
    
    public win_main() {
	        super("����������");

	        setLayout(new GridBagLayout());
	        

	        //if conn == null 
	        // JOptionPane.showMessageDialog(this, "111", "222", 1);
	        
	        //������� �������� ����
	        JMenuBar menuBar = new JMenuBar();
	        //������� ��� �������
	        JMenu menuList = new JMenu("�����������");
	        JMenu menuCntr = new JMenu("��������");
	        JMenu menuRclc = new JMenu("�����������");
	        JMenu menuCash = new JMenu("������");
	        
	        JMenu menuPret = new JMenu("������������� ������");
	        menuPret.setEnabled(false);
	        
	        JMenu menuReps = new JMenu("������");
	        menuReps.setEnabled(false);
	        
	        JMenu menuTune = new JMenu("���������");

	        JMenu menuAcc = new JMenu("������� �����");
	        JMenuItem item00 = new JMenuItem("��������������");
	        JMenuItem item01 = new JMenuItem("����������");
	        
	        item00.addActionListener(new java.awt.event.ActionListener() {
		         public void actionPerformed(ActionEvent e)
		         {
		        	 new WinSearch().setVisible(true);
		        	 //new WinAccount(40101).setVisible(true);
		         }
		        });
	        
	        item01.addActionListener(new java.awt.event.ActionListener() {
		         public void actionPerformed(ActionEvent e)
		         {
		        	 
		        	 new WinAccountAdd().setVisible(true);
		         }
		        });
	        
	        		
	        JMenuItem item02 = new JMenuItem("����");
	        JMenuItem item03 = new JMenuItem("������");
	        JMenuItem item04 = new JMenuItem("����");
	        JMenuItem item05 = new JMenuItem("������� ����������");
	        JMenuItem item06 = new JMenuItem("�����������");

	        item02.addActionListener(new java.awt.event.ActionListener() {
		         public void actionPerformed(ActionEvent e)
		         {
		        	 
		        	 new WinHouse().setVisible(true);
		         }
		        });
	        
	        item03.addActionListener(new java.awt.event.ActionListener() {
		         public void actionPerformed(ActionEvent e)
		         {
		        	 
		        	 new WinService().setVisible(true);
		         }
		        });	        
	        

	        item04.addActionListener(new java.awt.event.ActionListener() {
		         public void actionPerformed(ActionEvent e)
		         {
		        	 
		        	 new WinPrice(new Integer(0)).setVisible(true);
		         }
		        });	        
	        
	        		
	        menuAcc.add(item00);
	        menuAcc.add(item01);
	        menuList.add(menuAcc);

	        menuList.add(item02);
	        menuList.add(item03);
	        menuList.add(item04);
	        menuList.add(item05);
	        menuList.add(item06);
	        
	        JMenuItem item11 = new JMenuItem("�������� ���������");
	        JMenuItem item12 = new JMenuItem("��������� ���������");
	        
	        item11.addActionListener(new java.awt.event.ActionListener() {
		         public void actionPerformed(ActionEvent e)
		         {
		        	 
		        	 new WinCounterList().setVisible(true);
		         }
		        });	        

	        item12.addActionListener(new java.awt.event.ActionListener() {
		         public void actionPerformed(ActionEvent e)
		         {
		        	 
		        	 new WinCounterLinked().setVisible(true);
		         }
		        });	        
	        	        
	        menuCntr.add(item11);
	        menuCntr.add(item12);

	        JMenuItem item21 = new JMenuItem("�����������");
	        item21.addActionListener(new java.awt.event.ActionListener() {
		         public void actionPerformed(ActionEvent e)
		         {
		        	 
		        	 new WinRecalc().setVisible(true);
		         }
		        });
	        
	        
	        menuRclc.add(item21);

	        JMenuItem item31 = new JMenuItem("������� ����� ����� ������");
	        JMenuItem item32 = new JMenuItem("������ �������");
	        JMenuItem item33 = new JMenuItem("�������� �� �����");
	        item33.setEnabled(false);
	        JMenuItem item34 = new JMenuItem("�������� � ����");
	        item34.setEnabled(false);
	        JMenuItem item35 = new JMenuItem("������ �� ����");
	        item35.setEnabled(false);
	        JMenuItem item36 = new JMenuItem("������ �� ������");
	        item36.setEnabled(false);
	        
	        item31.addActionListener(new java.awt.event.ActionListener() {
		         public void actionPerformed(ActionEvent e)
		         {
		        	 
		        	 new WinDebet(1).setVisible(true);
		         }
		        });
	        
	        
	        menuCash.add(item31); 
	        menuCash.add(item32);
	        menuCash.addSeparator();
	        menuCash.add(item33);
	        menuCash.add(item34);
	        menuCash.addSeparator();
	        menuCash.add(item35);
	        menuCash.add(item36);

	        JMenuItem item41 = new JMenuItem("����������");
	        JMenuItem item42 = new JMenuItem("������ �� �����");
	        JMenuItem item43 = new JMenuItem("������ �� �����");
	        JMenuItem item44 = new JMenuItem("���������");
	        JMenuItem item45 = new JMenuItem("��������������");
	        JMenuItem item46 = new JMenuItem("������ ���������� �� ������");
	        
	        menuPret.add(item41);
	        menuPret.addSeparator();
	        menuPret.add(item42);
	        menuPret.add(item43);
	        menuPret.addSeparator();
	        menuPret.add(item44);
	        menuPret.add(item45);
	        menuPret.addSeparator();
	        menuPret.add(item46);

	        JMenu menuOut = new JMenu("������� �� �������� �����");
	        JMenuItem item51 = new JMenuItem("� �����");
	        JMenuItem item52 = new JMenuItem("�� ����������� ��");
	        
	        JMenuItem item53 = new JMenuItem("������ �� ������");
	        JMenuItem item54 = new JMenuItem("1");
	        JMenuItem item55 = new JMenuItem("2");
	        JMenuItem item56 = new JMenuItem("3");
	        
	        menuOut.add(item51);
	        menuOut.add(item52);
	        menuReps.add(menuOut);
	        menuReps.addSeparator();	
	        menuReps.add(item53);
	        menuReps.add(item54);
	        menuReps.add(item55);
	        menuReps.add(item56);
	        
	        JMenuItem itemConnect = new JMenuItem("����������� � ��");
	        itemConnect.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
			    	new WinLogin().setVisible(true); 
				}
			});
	        
	        JMenuItem itemPerCur = new JMenuItem("������� ������ ����������");
	        JMenuItem itemExit = new JMenuItem("�����");
	        itemExit.addActionListener(new java.awt.event.ActionListener() {
	         public void actionPerformed(ActionEvent e)
	         {
	        	 System.exit(0);//����� �� �������
	         }
	        });
	        
	        menuTune.add(itemConnect);
	        menuTune.add(itemPerCur);
	        menuTune.addSeparator();
	        menuTune.add(itemExit);
        
	        
	        menuBar.add(menuList);
	        menuBar.add(menuCntr);
	        menuBar.add(menuRclc);
	        menuBar.add(menuCash);
	        menuBar.add(menuPret);
	        menuBar.add(menuReps);
	        menuBar.add(menuTune);
    
	        setJMenuBar(menuBar);
/*	        
	        add(new JLabel("First name:"), new GridBagConstraints(1, 0, 1, 1, 0, 0,
		            GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 0, 5, 0), 0, 0));
	        add(new JTextField("<enter first name here>", 20), new GridBagConstraints(2, 0, 2, 1, 1, 0,
		            GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 0, 5, 5), 0, 0));
	        add(new JLabel("Last name:"), new GridBagConstraints(1, 1, 1, 1, 0, 1,
		            GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0));
	        add(new JTextField("<enter last name here>", 20), new GridBagConstraints(2, 1, 2, 1, 1, 1,
		            GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));
	        
	        JButton btnOk = new JButton("Ok");
	        JButton btnCancel = new JButton("Cancel");
	        btnOk.setPreferredSize(btnCancel.getPreferredSize());
	        btnOk.setMinimumSize(btnOk.getPreferredSize());
	        this.add(btnOk, new GridBagConstraints(2, 2, 1, 1, 1, 0, GridBagConstraints.LINE_END,
	            GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
	        this.add(btnCancel, new GridBagConstraints(3, 2, 1, 1, 0, 0, GridBagConstraints.CENTER,
	            GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
*/
	        /*	        
	        final JPanel content = new JPanel(new GridBagLayout());
//	        JLabel lblImage = new JLabel(new ImageIcon(getClass().getResource("/image.png")));
	        JLabel lblImage = new JLabel("/image.png");
	        content.add(lblImage, new GridBagConstraints(0, 0, 1, 2, 0, 0, GridBagConstraints.NORTH,
	            GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
	        content.add(new JLabel("First name:"), new GridBagConstraints(1, 0, 1, 1, 0, 0,
	            GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 0, 5, 0), 0, 0));
	        content.add(new JTextField("<enter first name here>", 20), new GridBagConstraints(2, 0, 2, 1, 1, 0,
	            GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 0, 5, 5), 0, 0));
	        content.add(new JLabel("Last name:"), new GridBagConstraints(1, 1, 1, 1, 0, 1,
	            GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0));
	        content.add(new JTextField("<enter last name here>", 20), new GridBagConstraints(2, 1, 2, 1, 1, 1,
	            GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));
	        JButton btnOk = new JButton("Ok");
	        JButton btnCancel = new JButton("Cancel");
	        btnOk.setPreferredSize(btnCancel.getPreferredSize());
	        btnOk.setMinimumSize(btnOk.getPreferredSize());
	        content.add(btnOk, new GridBagConstraints(2, 2, 1, 1, 1, 0, GridBagConstraints.LINE_END,
	            GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
	        content.add(btnCancel, new GridBagConstraints(3, 2, 1, 1, 0, 0, GridBagConstraints.CENTER,
	            GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
	        final JCheckBox chkOrientation = new JCheckBox("Right-to-left orientation");
	        chkOrientation.addChangeListener(new ChangeListener(){
	            public void stateChanged(ChangeEvent e){
	                content.setComponentOrientation( chkOrientation.isSelected() ?
	                                                 ComponentOrientation.RIGHT_TO_LEFT :
	                                                 ComponentOrientation.LEFT_TO_RIGHT);
	                content.doLayout();
	            }
	        });
	        content.setBorder(BorderFactory.createLineBorder(Color.red));
	        getContentPane().add(content, BorderLayout.CENTER);
	        getContentPane().add(chkOrientation, BorderLayout.SOUTH);
*/
//	        setSize(1024, 768);
/*	        try {
	            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
	                if ("Nimbus".equals(info.getName())) {//Nimbus
	                    UIManager.setLookAndFeel(info.getClassName());
	                    break;
	                }
	            }
	        } catch (Exception e) {
	        	JOptionPane.showMessageDialog(null, e.getMessage(), "win_main: ����� ����", JOptionPane.ERROR_MESSAGE);
	        }
*/	        pack();
//	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	    }
}
