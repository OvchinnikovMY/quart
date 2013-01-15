package main;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.table.AbstractTableModel;

public class WinAccount extends JFrame{

	private Integer acc;
    JTextField fId = new JTextField("", 12);
    JTextField fStreet = new JTextField("", 12);
    JTextField fHouse = new JTextField("", 12);
    JTextField fFlat = new JTextField("", 12);
    JFormattedTextField fSince = new JFormattedTextField(new SimpleDateFormat("dd.MM.yyyy"));
    JFormattedTextField fFinish = new JFormattedTextField(new SimpleDateFormat("dd.MM.yyyy"));
    JTextArea fCmt = new JTextArea(3, 12);

    JTextField fOwner = new JTextField("", 12);
    JTextField fSq = new JTextField("", 12);
    JFormattedTextField fOwn = new JFormattedTextField(new SimpleDateFormat("dd.MM.yyyy"));
    JTextField fPes = new JTextField("", 12);
    JTextPane fCmtHis = new JTextPane();

    private JTable tblServ = new JTable();

    JButton bSave = new JButton("Сохранить");
    
    JButton bPrin = new JButton("Печать");

	public WinAccount(Integer _acc){
        super("Редактирование лицевого счёта: список домов");
        acc=_acc;
        setLayout(new GridBagLayout());
        
        JPanel panelAcc = new JPanel();
        JPanel panelHis = new JPanel();

        final JTabbedPane tPane = new JTabbedPane();
    	
    	final JPanel pServ = new JPanel();
    	final JButton bServAdd = new JButton("Добавить");
        bServAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new WinServAppAdd(WinAccount.this.getAcc()).setVisible(true);
			}
		});
//    	final JButton bServEdit = new JButton("Изменить");
//    	bServEdit.setEnabled(false);
    	
    	final JButton bServDel = new JButton("Удалить");
    	bServDel.addActionListener(new SrvDelActionListener());
    	
    	final JButton bServRefresh = new JButton("Обновить");
    	bServRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tblServ.setModel(new ServicesTableModel(acc));
			}
		});
    	
    	pServ.setLayout(new GridBagLayout());
    	pServ.add(new JScrollPane(tblServ), new GridBagConstraints(0, 0, 4, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    	pServ.add(bServAdd, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
//    	pServ.add(bServEdit, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    	pServ.add(bServDel, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    	pServ.add(bServRefresh, new GridBagConstraints(2, 1, 1, 1, 1, 0, GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    	
    	tblServ.setModel(new ServicesTableModel(acc));
    	
    	tPane.add(pServ, "Услуги");
     
    	final JPopupMenu popup = new JPopupMenu();
        JMenuItem menuItem1 = new JMenuItem("Обновить");
        popup.add(menuItem1);
        popup.addSeparator();
        JMenuItem menuItem2 = new JMenuItem("Изменить услугу...");
        popup.add(menuItem2);
        JMenuItem menuItem3 = new JMenuItem("Остановить предоставление услуги...");
        menuItem3.addActionListener(new SrvStopActionListener());
        popup.add(menuItem3);
        JMenuItem menuItem4 = new JMenuItem("Удалить услугу");
        menuItem4.addActionListener(new SrvDelActionListener());
        popup.add(menuItem4);
        popup.addSeparator();
        JMenuItem menuItem5 = new JMenuItem("Создать и подключить новый счётчик");
        menuItem5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BeanServiceApplication bsa = ((ServicesTableModel) tblServ.getModel()).getBean(tblServ.getSelectedRow());
				
				if ( bsa.getCnt_id() != 0 ) return; //	Выйти, если счётчик стоит.
				if ( bsa.getFinish() != null ) return; // Выйти, если есть дата окончания действия услуги.
				
//				Проерить дату начала действия услуги. Если она в текущем периоде, то тогда обновить бин. 
//				Если нет - создать копию услуги, эту закрыть запрощенной датой,. Сохранить. Поставить счётчик. Сделать копию.
				if (new BeanPeriod().isInPeriod(bsa.getSince())) {
					bsa.setCnt_id(BeanCounter.create().getId()); //	Создать счётчик
					bsa.update(); //	Установить счётчик
				} else {
					WinDate w = new WinDate();
					w.setVisible(true);
					BeanServiceApplication bnew = bsa.copy(); // Сделать копию услуги
					bsa.setFinish(w.getDate());
					bsa.update();
					bnew.setSince(w.getDate());
					bnew.setCnt_id(BeanCounter.create().getId()); // Создать счётчик
					bnew.add();
				}
		    	tblServ.setModel(new ServicesTableModel(acc));

			}
		});
       popup.add(menuItem5);
        JMenuItem menuItem6 = new JMenuItem("Подключить существующий счётчик...");
        popup.add(menuItem6);
        JMenuItem menuItem7 = new JMenuItem("Показания счётчика...");
        menuItem7.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if ( ((ServicesTableModel) tblServ.getModel()).getBean(tblServ.getSelectedRow()).getCnt_id() != 0 )  {
					new WinCounter(((ServicesTableModel) tblServ.getModel()).getBean(tblServ.getSelectedRow()).getCnt_id()).setVisible(true);
				}
			}
		});
        popup.add(menuItem7);
        
        tblServ.setComponentPopupMenu(popup);
        tblServ.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {
                int row = tblServ.rowAtPoint(e.getPoint());
                int column = tblServ.columnAtPoint(e.getPoint());
                if (row >= 0 && column >= 0) {
                    ((JTable)e.getSource()).changeSelection(row, column, false, false);
                }
			}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {}
		});        

    	final ButtonGroup bGroup = new ButtonGroup();
     	final JToggleButton r0 = new JToggleButton("Сальдо");  
    	final JToggleButton r1 = new JToggleButton("Свод за период");   
    	final JToggleButton r2 = new JToggleButton("Детально");   

    	final JTable tblCred = new JTable();
    	
    	bGroup.add(r0);
    	bGroup.add(r1);
    	bGroup.add(r2);
    	
    	final JPanel pCred = new JPanel();
    	pCred.setLayout(new GridBagLayout());
    	pCred.add(new JScrollPane(tblCred), new GridBagConstraints(0, 0, 3, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    	pCred.add(r0, new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
    	pCred.add(r1, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
    	pCred.add(r2, new GridBagConstraints(2, GridBagConstraints.RELATIVE, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        r0.setSelected(true);
        tblCred.setModel(new SaldoTableModel(WinAccount.this.acc));
        
        r0.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
		        tblCred.setModel(new SaldoTableModel(WinAccount.this.acc));
			}
		});

        r1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
		        tblCred.setModel(new CredTableModel(WinAccount.this.acc, new Integer(0)));
			}
		});

        r2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
		        tblCred.setModel(new CalcTableModel(WinAccount.this.acc));
			}
		});
        tPane.add(pCred, "Начисления");
        
    	final JTable tblDebt = new JTable();
    	tPane.add(new JScrollPane(tblDebt), "Оплата");

    	final JTable tblCntr = new JTable();
    	tPane.add(new JScrollPane(tblCntr), "Счётчики");

    	final JTable tblRclc = new JTable();
    	tPane.add(new JScrollPane(tblRclc), "Перерасчёты");

    	final JTable tblHist = new JTable();
    	tPane.add(new JScrollPane(tblHist), "История изменений");
    	tblHist.setModel(new HistoriesTableModel(WinAccount.this.acc));
    	
        BeanAccount beanacc = new BeanAccount(acc);
        
        fId.setText(beanacc.getAcc().toString()); fId.setEditable(false); fId.setHorizontalAlignment(JTextField.RIGHT);
        fStreet.setText(beanacc.getStreet()); fStreet.setEditable(false);
        fHouse.setText(beanacc.getNum()); fHouse.setEditable(false); fHouse.setHorizontalAlignment(JTextField.RIGHT);
        fFlat.setText(beanacc.getFlat()); fFlat.setEditable(false); fFlat.setHorizontalAlignment(JTextField.RIGHT);
        fSince.setValue(beanacc.getSince()); fSince.setEditable(false); fSince.setHorizontalAlignment(JTextField.RIGHT);
        fFinish.setValue(beanacc.getFinish()); fFinish.setHorizontalAlignment(JTextField.RIGHT);
        fCmt.setText(beanacc.getAcmt());
//        fChdate.setValue(Calendar.getInstance().getTime()); 
        fOwner.setText(beanacc.getHowner()); 
        fSq.setText(beanacc.getSql().toString()); fSq.setHorizontalAlignment(JTextField.RIGHT);
        fOwn.setValue(beanacc.getOwn()); fOwn.setHorizontalAlignment(JTextField.RIGHT); 
        fPes.setText(beanacc.getPes().toString()); fPes.setHorizontalAlignment(JTextField.RIGHT); 
        fCmtHis.setText(beanacc.getHcmt()); fCmtHis.setPreferredSize(fCmt.getPreferredSize());
        
        add(panelAcc, new GridBagConstraints(0, 0, 1, 1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 5, 0, 0), 0, 0));
        add(panelHis, new GridBagConstraints(0, 1, 1, 1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 5, 0, 0), 0, 0));

        panelAcc.setLayout(new GridBagLayout());
        panelAcc.setBorder(BorderFactory.createTitledBorder("Лицевой счёт"));

        panelHis.setLayout(new GridBagLayout());
        panelHis.setBorder(BorderFactory.createTitledBorder("История изменений"));
 
        panelAcc.add(new JLabel("Номер:"), new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        panelAcc.add(fId, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        panelAcc.add(new JLabel("Улица:"), 			new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        panelAcc.add(fStreet, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        
        panelAcc.add(new JLabel("Дом:"), 				new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        panelAcc.add(fHouse, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        panelAcc.add(new JLabel("Квартира:"), 			new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        panelAcc.add(fFlat, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        panelAcc.add(new JLabel("Дата открытия ЛС:"), 	new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        panelAcc.add(fSince, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        panelAcc.add(new JLabel("Дата закрытия ЛС:"), 	new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        panelAcc.add(fFinish, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        panelAcc.add(new JLabel("Комментарий:"), 	new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.SOUTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        panelAcc.add(new JScrollPane(fCmt), new GridBagConstraints(0, GridBagConstraints.RELATIVE, 2, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 5, 5, 5), 0, 0));

        panelHis.add(new JLabel("Владелец:"), 			new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        fOwner.setHorizontalAlignment(JTextField.RIGHT);
        panelHis.add(fOwner, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        panelHis.add(new JLabel("Площадь:"), 			new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        panelHis.add(fSq, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        panelHis.add(new JLabel("Дата приватизации:"), 	new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        panelHis.add(fOwn, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        panelHis.add(new JLabel("Кол-во проживающих:"), new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        panelHis.add(fPes, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        panelHis.add(new JLabel("Коментарий:"), new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        panelHis.add(new JScrollPane(fCmtHis), new GridBagConstraints(0, GridBagConstraints.RELATIVE, 2, 1, 1, 1,
	            GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 5, 5), 0, 0));

        JButton bSave = new JButton("Сохранить");        
        add(bSave, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        bSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				WinDate w = new WinDate();
				w.setVisible(true);
				
				String strSQL = "INSERT INTO gks.histories(acc_id, chdate, howner, own, sql, pes, cmt) SELECT " 
   					 + acc
   					 + ", '" + w.toString() + "'"
   					 + ", '" + fOwner.getText() + "'" 
   					 + ((fOwn.getText().isEmpty())? ", null" :", '" + fOwn.getText() + "'")
   					 + ", " + fSq.getText()  
   					 + ", " + (( fPes.getText().isEmpty() ) ? 0 : fPes.getText()) 
   					 + ((fCmtHis.getText().isEmpty())? ", null" :", '" + fCmtHis.getText() + "'");
				try {
	        		 if ( JOptionPane.showConfirmDialog(WinAccount.this, "К лицевому счёту №" 
	        				 + acc + ", на дату: " + w.toString() 
	        				 , "Добавить?", 
	        				 JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION ) {

	        			 DbUtils.Conn.createStatement().executeUpdate(strSQL);
	        			 tblHist.setModel(new HistoriesTableModel(WinAccount.this.acc));
	        		 }
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(WinAccount.this, e.getMessage(),  "WinAccount - Сохранить" , JOptionPane.ERROR_MESSAGE);
				}
			}
		});
        
		add(tPane, new GridBagConstraints(2, 0, 1, 4, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 5, 0, 0), 0, 0));
		setPreferredSize(new Dimension(1000, 600));
		pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public Integer getAcc() {
		return acc;
	}
	//@SuppressWarnings("unused")

// Услуги	
	private class ServicesTableModel extends AbstractTableModel{
	
		private List<BeanServiceApplication> beans;
		
		public ServicesTableModel(Integer acc){
			beans = new ArrayList<BeanServiceApplication>();
			ResultSet rs;
			try {
				rs = DbUtils.Conn.createStatement().executeQuery("SELECT id, acc_id, srv_id, since, finish, coef, description, abb, kind, org_id, queue, isod, cnt_id FROM gks.v_service_applications WHERE acc_id =" + acc);
				while(rs.next()){
					BeanServiceApplication b = new BeanServiceApplication(
						rs.getInt("id"),
						rs.getInt("acc_id"),
						rs.getInt("srv_id"),
						rs.getDate("since"),
						rs.getDate("finish"),
						rs.getDouble("coef"),
						rs.getString("description"),
						rs.getString("abb"), 
						rs.getInt("kind"),
						rs.getInt("org_id"),
						rs.getInt("queue"),
						rs.getBoolean("isod"),
						rs.getInt("cnt_id")
						);
					beans.add(b);
				}
				rs.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),  "ServicesTableModel" , JOptionPane.ERROR_MESSAGE);
			}
		}
		
		@Override
		public String getColumnName(int columnIndex) {
			switch (columnIndex) {
				case 0:
					return "Название";
				case 1:
					return "С";
				case 2:
					return "По";
				case 3:
					return "Тип";
				case 4:
					return "Норм";
				case 5:
					return "ОД?";
				case 6:
					return "Счётчик";
				}
			return "";
		}

		@Override
		public int getColumnCount() {
			return 7;
		}

		@Override
		public int getRowCount() {
			return beans.size();
		}

		@Override
		public Object getValueAt(int rowindex, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return beans.get(rowindex).getDescription();
			case 1:
				return beans.get(rowindex).getSince();
			case 2:
				return beans.get(rowindex).getFinish(); //((beans.get(rowindex).getFinish() == null)?Date.valueOf(""): beans.get(rowindex).getFinish()); 
			case 3:
				return DbUtils.strKind[beans.get(rowindex).getKind()];
			case 4:
				return beans.get(rowindex).getCoef();
			case 5:
				return beans.get(rowindex).getIsod();
			case 6:
				return ((beans.get(rowindex).getCnt_id() != 0)?beans.get(rowindex).getCnt_id():null);
			}
		return "";
			
		}
    
		@Override
		public Class<?> getColumnClass(int c) {
			
			if (getValueAt(0, c) == null) return String.class;
//			System.out.println(c +": "+ getValueAt(0, c).getClass() + " " + getValueAt(0, c));
	        return getValueAt(0, c).getClass();
	    }
		
		public BeanServiceApplication getBean (int rowindex){
			return beans.get(rowindex);
		}
/*	    public boolean isCellEditable(int row, int col)
        { 
	    	return true; 
	    }
	    public void setValueAt(Object value, int row, int col) {
	    	rowData[row][col] = value;
	    	fireTableCellUpdated(row, col);
	    }
*/	}

// Сальдо
	private class SaldoTableModel extends AbstractTableModel{

		private List<BeanSaldo> beans;
		
		public SaldoTableModel(Integer acc){
			beans = new ArrayList<BeanSaldo>();
			ResultSet rs;
			try {
				rs = DbUtils.Conn.createStatement().executeQuery("SELECT acc, per_id, gks.f_period_begin(per_id) as since, rest, cred::NUMERIC(10,2), rclc, debt " + 
						"FROM gks.v_saldo_current WHERE acc = " + acc + " ORDER BY per_id");
				while(rs.next()){
					BeanSaldo b = new BeanSaldo(
						rs.getInt("acc"),
						rs.getInt("per_id"),
						rs.getDate("since"),
						rs.getDouble("rest"),
						rs.getDouble("cred"),
						rs.getDouble("rclc"),
						rs.getDouble("debt")
						);
					beans.add(b);
				}
				rs.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),  "SaldoTableModel" , JOptionPane.ERROR_MESSAGE);
			}
		}
		
		@Override
		public String getColumnName(int columnIndex) {
			switch (columnIndex) {
				case 0:
					return "Начало периода";
				case 1:
					return "Остаток";
				case 2:
					return "Начислено";
				case 3:
					return "Перерасчёт";
				case 4:
					return "Оплачено";
				}
			return "";
		}

		@Override
		public int getColumnCount() {
			return 4;
		}

		@Override
		public int getRowCount() {
			return beans.size();
		}

		@Override
		public Object getValueAt(int rowindex, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return beans.get(rowindex).getSince();
			case 1:
				return beans.get(rowindex).getRest();
			case 2:
				return beans.get(rowindex).getCred();
			case 3:
				return beans.get(rowindex).getRclc();
			case 4:
				return beans.get(rowindex).getDebt();
			}
		return "";
		}
	}
		
// История	
	private class HistoriesTableModel extends AbstractTableModel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private List<BeanHistory> beans;
		
		public HistoriesTableModel(Integer acc){
			beans = new ArrayList<BeanHistory>();
			ResultSet rs;
			try {
				rs = DbUtils.Conn.createStatement().executeQuery("SELECT id, acc_id, chdate, id_owner, sql::numeric(10,2), own, pes, cmt, howner FROM gks.histories WHERE acc_id =" + acc);
				while(rs.next()){
					BeanHistory b = new BeanHistory(
						rs.getInt("id"),
						rs.getInt("acc_id"),
						rs.getDate("chdate"),
						rs.getString("howner"),
						rs.getDouble("sql"),
						rs.getDate("own"),
						rs.getInt("pes"),
						rs.getString("cmt")
						);
					beans.add(b);
				}
				rs.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),  "ServicesTableModel" , JOptionPane.ERROR_MESSAGE);
			}
		}
		
		@Override
		public String getColumnName(int columnIndex) {
			switch (columnIndex) {
				case 0:
					return "Код";
				case 1:
					return "Дата";
				case 2:
					return "Владелец";
				case 3:
					return "Площ.";
				case 4:
					return "Приват.";
				case 5:
					return "Прописанных";
				case 6:
					return "Коммент.";
				}
			return "";
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 7;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return beans.size();
		}

		@Override
		public Object getValueAt(int rowindex, int columnIndex) {
			// TODO Auto-generated method stub
			switch (columnIndex) {
			case 0:
				return beans.get(rowindex).getId();
			case 1:
				return beans.get(rowindex).getChdate();
			case 2:
				return beans.get(rowindex).getHowner();
			case 3:
				return beans.get(rowindex).getSql();
			case 4:
				return beans.get(rowindex).getOwn();
			case 5:
				return beans.get(rowindex).getPes();
			case 6:
				return beans.get(rowindex).getCmt();
			}
		return "";
			
		}
		
	}

// Расчёт
	private class CalcTableModel extends AbstractTableModel{
		private List<BeanCalc> beans;
		
		public CalcTableModel(Integer acc){
			beans = new ArrayList<BeanCalc>();
			ResultSet rs;
			try {
				rs = DbUtils.Conn.createStatement().executeQuery("SELECT acc, description as name, srv_id, isod, since, days, credsum, countsum, rclcsum " + 
						"FROM gks.calc_account(" + acc + ") LEFT JOIN gks.services ON srv_id = services.id" );
				while(rs.next()){
					BeanCalc b = new BeanCalc( 
						rs.getInt("acc"),
						rs.getString("name"),
						rs.getDate("since"),
						rs.getInt("days"),
						rs.getInt("srv_id"),
						rs.getBoolean("isod"),
						rs.getDouble("credsum"),
						rs.getDouble("countsum"),
						rs.getDouble("rclcsum")
						);
					beans.add(b);
				}
				rs.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),  "ServicesTableModel" , JOptionPane.ERROR_MESSAGE);
			}
		}
		
		@Override
		public String getColumnName(int columnIndex) {
			switch (columnIndex) {
				case 0:
					return "Услуга";
				case 1:
					return "ОД";
				case 2:
					return "Дата начала";
				case 3:
					return "Дней";
				case 4:
					return "Начисл.";
				case 5:
					return "Счётчик";
				case 6:
					return "Перерасчёт";
				}
			return "";
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 7;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return beans.size();
		}

		@Override
		public Object getValueAt(int rowindex, int columnIndex) {
			// TODO Auto-generated method stub
			switch (columnIndex) {
			case 0:
				return beans.get(rowindex).getName();
			case 1:
				return  (beans.get(rowindex).getIsod())?"Да":"";
			case 2:
				return beans.get(rowindex).getSince();
			case 3:
				return beans.get(rowindex).getDays();
			case 4:
				return beans.get(rowindex).getCredsum();
			case 5:
				return beans.get(rowindex).getCountsum();
			case 6:
				return beans.get(rowindex).getRclcsum();
			}
		return "";
			
		}
		
	}

// Начисление по услугам
	private class CredTableModel extends AbstractTableModel{
		private List<BeanCred> beans;
		
		public CredTableModel(Integer acc_id, Integer per_id){
			beans = new ArrayList<BeanCred>();
			ResultSet rs;
			try {
				rs = DbUtils.Conn.createStatement().executeQuery("SELECT acc, per_id, srv_id, description, isod, cred::numeric(10, 2), count::numeric(10, 2), rclc::numeric(10, 2) " +
					"FROM gks.v_cred_by_serv_current WHERE acc=" + acc + " and per_id=" + ((per_id != 0)? per_id : "gks.f_period_current()" ));
				while(rs.next()){
					BeanCred b = new BeanCred( 
						rs.getInt("acc"),
						rs.getInt("per_id"),
						rs.getInt("srv_id"),
						rs.getString("description"),
						rs.getBoolean("isod"),
						rs.getDouble("cred"),
						rs.getDouble("count"),
						rs.getDouble("rclc")
						);
					beans.add(b);
				}
				rs.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),  "CredTableModel" , JOptionPane.ERROR_MESSAGE);
			}
		}
		
		@Override
		public String getColumnName(int columnIndex) {
			switch (columnIndex) {
				case 0:
					return "Услуга";
				case 1:
					return "ОД";
				case 2:
					return "Начисл.";
				case 3:
					return "Счётчик";
				case 4:
					return "Перерасчёт";
				}
			return "";
		}

		@Override
		public int getColumnCount() {
			return 5;
		}

		@Override
		public int getRowCount() {
			return beans.size();
		}

		@Override
		public Object getValueAt(int rowindex, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return beans.get(rowindex).getDescription();
			case 1:
				return  (beans.get(rowindex).getIsod())?"Да":"";
			case 2:
				return beans.get(rowindex).getCred();
			case 3:
				return beans.get(rowindex).getCount();
			case 4:
				return beans.get(rowindex).getRclc();
			}
		return "";
		}
	}
	
//	Удаление услуги
	private class SrvDelActionListener extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			String strSQL = "DELETE FROM gks.serv_applications WHERE id = " + 
				((ServicesTableModel) tblServ.getModel()).getBean(tblServ.getSelectedRow()).getId();
			try {
        		 if ( JOptionPane.showConfirmDialog(null, strSQL, "Удалить услугу?", 
        				 JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION ) {

        			 DbUtils.Conn.createStatement().executeUpdate(strSQL);
        			 tblServ.setModel(new ServicesTableModel(WinAccount.this.acc));
        		 }
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(WinAccount.this, e1.getMessage(),  "Ошибка удаления услуги" , JOptionPane.ERROR_MESSAGE);
			}
		}
	}

//	Остановка услуги
	private class SrvStopActionListener extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			WinDate w = new WinDate();
			w.setVisible(true);
			
			String strSQL = "UPDATE gks.serv_applications SET finish = '" + w.toString() + "' WHERE id = " + 
				((ServicesTableModel) tblServ.getModel()).getBean(tblServ.getSelectedRow()).getId();
			try {
        		 if ( JOptionPane.showConfirmDialog(null,  "Остановить услугу с " + w.toString() + "\n" + strSQL, "Остановить услугу?", 
        				 JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION ) {

        			 DbUtils.Conn.createStatement().executeUpdate(strSQL);
        			 tblServ.setModel(new ServicesTableModel(WinAccount.this.acc));
        		 }
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(WinAccount.this, e1.getMessage(),  "Ошибка прекращения услуги" , JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
