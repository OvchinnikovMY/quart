package main;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class WinSearch extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WinSearch() {
		super(" вартплата: поиск");
		final JLabel lId = new JLabel("¬ведите номер лицевого счЄта:");
		final JTextField fSearch = new JTextField("", 10); 
		final JButton bFind = new JButton("ќкрыть");
		
        setLayout(new FlowLayout()); 
        add(lId);
        add(fSearch);
        add(bFind);
        
        bFind.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new WinAccount(new Integer(fSearch.getText())).setVisible(true);
				WinSearch.this.dispose();
			}
		});

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
