package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class WinServAppAdd extends JDialog{

	private JTextField fId = new JTextField ("", 12);
	private JTextField fAcc = new JTextField ("", 12);
	private JComboBox<BeanService> cSrv =  new JComboBox<BeanService>();
	private JFormattedTextField fSince = new JFormattedTextField(new SimpleDateFormat("dd.MM.yyyy"));
	private JFormattedTextField fFinish = new JFormattedTextField(new SimpleDateFormat("dd.MM.yyyy"));
	private JTextField fCoef = new JTextField ("", 12);
	private JTextField fCnt = new JTextField ("", 12);
	private ButtonGroup bGroup = new ButtonGroup();
	private JRadioButton r0 = new JRadioButton("�������������� ������");  
	private JRadioButton r1 = new JRadioButton("����������� ������");  
	
	public WinServAppAdd(Integer acc){
//        super("����������: ���������� ������");
		super();
		setModal(true);
        this.setAlwaysOnTop(true);
        final DefaultComboBoxModel<BeanService> listModel = new DefaultComboBoxModel<BeanService>();
    	ResultSet rs;
    	
    	String query = "select * from gks.services";
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
        	JOptionPane.showMessageDialog(null, e.getMessage(), "������ SQL", JOptionPane.ERROR_MESSAGE);
	    }
	    
	    cSrv.setModel(listModel);

	    bGroup.add(r0);
	    bGroup.add(r1);

	    fSince.setValue(Calendar.getInstance().getTime());
	    
        setLayout(new GridBagLayout());
        
        add(new JLabel("���:"), new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 0, 0,
	            GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        fId.setHorizontalAlignment(JTextField.RIGHT);
        fId.setEditable(false);
        add(fId, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 0, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        add(new JLabel("����� ��:"), new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 0, 0,
	            GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        fAcc.setHorizontalAlignment(JTextField.RIGHT);
        fAcc.setText(acc.toString());
        fAcc.setEditable(false);
        
        add(fAcc, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 0, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        add(new JLabel("����� ������:"), new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 0, 0,
	            GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        add(cSrv, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 0, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        add(new JLabel("���� ������ ����������:"), new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 0, 0,
	            GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        fSince.setHorizontalAlignment(JTextField.RIGHT);
        add(fSince, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 0, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
 
        add(new JLabel("���� ��������� ����������:"), new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 0, 0,
	            GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        fFinish.setHorizontalAlignment(JTextField.RIGHT);
        add(fFinish, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 0, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        
        JPanel pIsod = new JPanel();
        pIsod.setBorder(BorderFactory.createTitledBorder("[ ��� ]"));
        pIsod.add(r0);
        pIsod.add(r1);
        r0.setSelected(true);
// ����������� ���������� �����������
        
        add(pIsod, new GridBagConstraints(0, GridBagConstraints.RELATIVE, 2, 1, 0, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        JButton btnAdd = new JButton("��������!");
        //btnAdd.setPreferredSize(btnAdd.getPreferredSize());

        add(new JLabel("����� �� �����:"), new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 0, 0,
	            GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        fCoef.setHorizontalAlignment(JTextField.RIGHT);
        add(fCoef, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 0, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        
        add(new JLabel("��� ��������:"), new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 0, 0,
	            GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        fCnt.setHorizontalAlignment(JTextField.RIGHT);
        add(fCnt, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 0, 0,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        
        add(btnAdd, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 1, 0, GridBagConstraints.SOUTHEAST,
            GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
	         public void actionPerformed(ActionEvent e)
	         {
	        	 setAlwaysOnTop(false);
        	 
        		 if ( JOptionPane.showConfirmDialog(null, "���������� "+((BeanService) cSrv.getSelectedItem()).getDescription() +
        				 " �� " + fAcc.getText() + " � " + fSince.getText(), "�������� ������?", 
        				 JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION ) {

        			 BeanServiceApplication.create(
	        			 Integer.parseInt(fAcc.getText()), 
	        			 ((BeanService) cSrv.getSelectedItem()).getId(), 
	        			 fSince.getText(), 
	        			 fFinish.getText(), 
	        			 Double.parseDouble(fCoef.getText().replace(",", ".")), 
	        			 r1.isSelected());

        			 JOptionPane.showMessageDialog(null, "��� ����������� ������ �������� �������", "������ ���������!", JOptionPane.INFORMATION_MESSAGE );
	        		 dispose();
        		 }
	         }
	        });
         
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}
	
}
