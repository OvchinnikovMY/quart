package main;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

public class WinDate extends JDialog{
    private JFormattedTextField fSince = new JFormattedTextField(new SimpleDateFormat("dd.MM.yyyy"));
    private JButton bOk = new JButton("Подтвердить");

	public WinDate() {
		super();
		setLayout(new FlowLayout());
		setModal(true);
		fSince.setValue(Calendar.getInstance().getTime());
		
		add(new JLabel("Дата совершения операции:"));
		add(fSince);
		add(bOk);
		
		bOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public Date getDate(){
		return new Date(((java.util.Date) fSince.getValue()).getTime());
	}
	
	@Override
	public String toString() {
		return fSince.getText();
	}
}
