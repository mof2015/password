package db_connec_test;

import java.sql.*;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class Main1 extends Intro implements MouseListener, ActionListener {
	JFrame jp = new JFrame("IPMS");
	JTable jt;
	JComboBox box;
	DefaultTableModel dtm;
	InputForm form;
	SearchForm form_search;
	int srow;
	JMenuBar menuBar = new JMenuBar();
	JMenu mainMenu = new JMenu("Menu");
	JMenu helpMenu = new JMenu("Help");
	JMenuItem logOut = new JMenuItem("Log Out");
	JMenuItem exit = new JMenuItem("Exit");
	JMenuItem version = new JMenuItem("Version");
	JMenuItem info = new JMenuItem("Info");
	JScrollPane scroll;
	JLabel label, label_info;
	JPanel northp,southp;
	JButton bt_add, bt_del, bt_up, bt_search;
	
	Object[][] rowData={};
	
	Object[] columnNames={"Name", "ID", "Password", "link"};

	// connecting database
	 public static Connection con;
	 public static Statement st;
	 public static ResultSet rs;
	 public static int id_num;

	public Main1(int no_id) throws SQLException {
				
		id_num = no_id;
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/mof", "root", "1234");
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			st = con.createStatement();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		dtm = new DefaultTableModel(rowData, columnNames);
		
		String sql = "select * from `keys` where `acnt_no` = "+no_id+"";
		rs = st.executeQuery(sql);
				
		if (st.execute(sql)) {
			rs = st.getResultSet();
		}

		while (rs.next()) {
			String str = rs.getString("title");
			String name = str;
			
			str = rs.getString("url");
			String link = str;
			
			str = rs.getString("id_sequence");
			String id = str;
			
			str = rs.getString("pw_sequence");
			String pw = str;
			
			Object rowData[] = {name, id, pw, link};
			dtm.addRow(rowData);
		}		
		
		form=new InputForm();
		form_search = new SearchForm();
		box = form_search.box;
		 
		jt=new JTable (dtm);
		scroll = new JScrollPane(jt);
		label = new JLabel("IPMS");
		northp = new JPanel();
		northp.add(label);

		bt_add = new JButton("Add new");  
		bt_del = new JButton("Delete");  
		bt_up = new JButton("Edit");  
		bt_search = new JButton("Search");  

		southp = new JPanel();
		southp.add(bt_add);
		southp.add(bt_del);
		southp.add(bt_up);
		southp.add(bt_search);		 
		 
		jp.add("Center",scroll);
		jp.add("North",northp);
		jp.add("South",southp);
		jp.setBounds(400,300,300,300);
		jp.setSize(1000, 700);
		jp.setVisible(true);
		jp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		eventUp();

		//create menu
		mainMenu.add(new JMenuItem("Edit master password"));
		mainMenu.getItem(0).setAccelerator(KeyStroke.getKeyStroke('E')); //Hot key Setting
		mainMenu.add(new JMenuItem("Edit favorites"));
		mainMenu.getItem(1).setAccelerator(KeyStroke.getKeyStroke('F')); //Hot key Setting
		mainMenu.add(new JMenuItem("Backup"));
		mainMenu.getItem(2).setAccelerator(KeyStroke.getKeyStroke('B', InputEvent.ALT_MASK)); //Hot key Setting
		mainMenu.add(new JMenuItem("Restore"));
		mainMenu.getItem(3).setAccelerator(KeyStroke.getKeyStroke('R', InputEvent.ALT_MASK)); //Hot key Setting
		mainMenu.addSeparator(); //separator
		
		mainMenu.add(logOut);
		mainMenu.getItem(0).setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_MASK ^ InputEvent.ALT_MASK)); //Hot key Setting
		mainMenu.addSeparator();
		mainMenu.add(exit);
		mainMenu.getItem(0).setAccelerator(KeyStroke.getKeyStroke('Q', InputEvent.ALT_MASK)); //Hot key Setting
		//mainMenu.add(version);
		//mainMenu.getItem(0).setAccelerator(KeyStroke.getKeyStroke('V', InputEvent.CTRL_MASK)); //Hot key Setting
		helpMenu.add(info);
		helpMenu.getItem(0).setAccelerator(KeyStroke.getKeyStroke('I', InputEvent.CTRL_MASK)); //Hot key Setting

		//add menu to menubar
		menuBar.add(mainMenu);
		menuBar.add(helpMenu);

		//add menubar
		jp.setJMenuBar(menuBar);
		
	    jp.setLocationRelativeTo(null);
	    jp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void eventUp(){
	    jt.addMouseListener(this);
	    
	    mainMenu.addActionListener(this);
	    helpMenu.addActionListener(this);
	    bt_add.addActionListener(this);
	    bt_del.addActionListener(this);
	    bt_up.addActionListener(this);
	    bt_search.addActionListener(this);
	    
	    form.bt_input.addActionListener(this);
	    form.bt_cancel.addActionListener(this);
	    
	    form_search.bt_input.addActionListener(this);
	    form_search.bt_cancel.addActionListener(this);
	    
	    logOut.addActionListener(this);
	    exit.addActionListener(this);
	    info.addActionListener(this);
	 }
	
	 public void actionPerformed(ActionEvent e) {

		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/mof", "root", "1234");
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			st = con.createStatement();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		Object ob=e.getSource();
		if(ob==bt_add){		//add button
			form.initTF();
			form.setVisible(true);
		}
		else if(ob==form.bt_input){		//input button (insert / edit)
		
			String name= form.tf_name.getText();
			String id= form.tf_id.getText();
			String pw= form.tf_pw.getText();
			String link= form.tf_link.getText();
		      
			if(name == null  || name.length() == 0){
		      //check nullable	
				JOptionPane.showMessageDialog(form, "Input name!!"); 
				form.tf_name.requestFocus();
				return;
			}
		      
			if(id.length()==0){
				JOptionPane.showMessageDialog(form, "Input ID!!"); 
				form.tf_id.requestFocus();
				return;
			}
		      
			if(pw.length()==0){
				JOptionPane.showMessageDialog(form, "Input password!!");
				form.tf_pw.requestFocus();
				return;
			}
			
			if(link.length()==0){
				JOptionPane.showMessageDialog(form, "Input link!!");
				form.tf_link.requestFocus();
				return;
			}

			String query_name = "'"+name+"'";
			String query_id = "'"+id+"'";
			String query_pw = "'"+pw+"'";
			String query_link = "'"+link+"'";
					
			if(form.getTitle().equals("Add new")){		//add new form
				
				String sql = "INSERT INTO `keys` (`acnt_no`, `title`, `url`, `id_sequence`, `pw_sequence`) VALUES ("+id_num+","+query_name+","+query_link+","+query_id+","+query_pw+")";
				try {
					st.executeUpdate(sql);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				Object rowData[] = {name, id, pw, link};
				dtm.addRow(rowData);
				try {
					fileSave();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else {	//edit form
				jt.setValueAt(name, srow, 0);	//edit name
				jt.setValueAt(id, srow, 1);		//edit id
				jt.setValueAt(pw, srow, 2);		//edit pw
				jt.setValueAt(link, srow, 3);	//edit link
				try {
					fileSave();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			form.setVisible(false);
			jp.setVisible(true);
		}
		
		else if(ob==form.bt_cancel){	//cancel button
			form.setVisible(false);
			jp.setVisible(true);
		}
		
		else if(ob==bt_del){	//delete button
			srow = jt.getSelectedRow();
			if(srow==-1){
				JOptionPane.showMessageDialog(jp,"Select row to delete!!");
				return;
			}
			String name = (String) jt.getValueAt(srow,0);
			String id  = (String) jt.getValueAt(srow,1);
			String pw  = (String) jt.getValueAt(srow,2);
			String link  = (String) jt.getValueAt(srow,3);
			for(int i=0; i<jt.getRowCount(); i++){
				if(name.equals(jt.getValueAt(i,0)) && id.equals(jt.getValueAt(i, 1)) && pw.equals(jt.getValueAt(i, 2)) && link.equals(jt.getValueAt(i,3))){
					dtm.removeRow(i);
				}
			}
			try {
				fileSave();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(ob==bt_up){		//edit button
			srow = jt.getSelectedRow(); 
     
			if(srow==-1){		//if unselected, srow value is -1
				JOptionPane.showMessageDialog(jp, "Select row to edit!!");
				return;
			}
		     
			String name = (String) jt.getValueAt(srow,0);
			String id  = (String) jt.getValueAt(srow,1);
			String pw  = (String) jt.getValueAt(srow,2);
			String link  = (String) jt.getValueAt(srow,3);
			
			form.tf_name.setText(name);
			form.tf_id.setText(id);
			form.tf_pw.setText(pw);
			form.tf_link.setText(link);
			form.initUp();
			form.setVisible(true);
		}
		else if(ob==bt_search){		//search button
		     
			String tf  = form_search.tf.getText();
			
			form_search.tf.setText(tf);
			form_search.initSearch();
			form_search.setVisible(true);
		}
		else if(ob==form_search.bt_input) {
			String input = form_search.tf.getText();
			
			if(input == null  || input.length() == 0){
			      //check nullable
					JOptionPane.showMessageDialog(form, "Input anything please!!"); 
					form_search.tf.requestFocus();
					return;
				}
			else
			{
				if(box.getSelectedItem() == "Name")
				{
					for(int i = 0; i < rowData.length; i++)
					{
						if(input.compareTo((String)rowData[i][0]) == 0)
						{
							form_search.setVisible(false);
							jp.setVisible(true);
							jt.setRowSelectionInterval(i, i);
							return;
						}
					}
					form_search.setVisible(false);
					jp.setVisible(true);
				}
				else if(box.getSelectedItem() == "ID")
				{
					for(int i = 0; i < rowData.length; i++)
					{
						if(input.compareTo((String)rowData[i][1]) == 0)
						{
							form_search.setVisible(false);
							jp.setVisible(true);
							jt.setRowSelectionInterval(i, i);
							return;
						}
					}
					form_search.setVisible(true);
					jp.setVisible(true);
				}
				else if(box.getSelectedItem() == "Password")
				{
					for(int i = 0; i < rowData.length; i++)
					{
						if(input.compareTo((String)rowData[i][2]) == 0)
						{
							form_search.setVisible(false);
							jp.setVisible(true);
							jt.setRowSelectionInterval(i, i);
							return;
						}
					}
					form_search.setVisible(false);
					jp.setVisible(true);
				}
				else
				{
					for(int i = 0; i < rowData.length; i++)
					{
						if(input.compareTo((String)rowData[i][3]) == 0 || ((String) rowData[i][3]).contains(input))
						{
							form_search.setVisible(false);
							jp.setVisible(true);
							jt.setRowSelectionInterval(i, i);
							return;
						}
					}
					form_search.setVisible(false);
					jp.setVisible(true);
				}
			}
			
		}
		else if(ob==form_search.bt_cancel){
			form_search.setVisible(false);
			jp.setVisible(true);
		}
	
		//DB�뿰�룞�씠 �븘吏� �븞�릱�쑝誘�濡� 濡쒓렇�븘�썐 �썑 �옱濡쒓렇�씤�떆 �닔�젙 �뜲�씠�꽣�뒗 紐⑤몢 ���옣�릺吏� �븡�쓬
		else if(ob==logOut){
			jp.setVisible(false);
			Intro gui =new Intro();
			gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			gui.setSize(500,300);
			gui.setLocationRelativeTo(null);

			gui.setVisible(true);
			gui.setResizable(false);

			gui.setTitle("IMPS Ver.1.0");
		}
		else if(ob==exit){
			jp.setVisible(false);
		}
		else if(ob==info){
			
			JOptionPane.showMessageDialog(helpMenu, "Copyright (C) Korea University Information Security MOF Team", "Info", JOptionPane.INFORMATION_MESSAGE);

		}
	}

	 public void mouseClicked(MouseEvent e) {
	     int srow= jt.getSelectedRow();		//row index
	     Object name=jt.getValueAt(srow, 0);
	     Object id=jt.getValueAt(srow, 1);
	     Object pw=jt.getValueAt(srow, 2);
	     
	     label.setText(name+" - ID: "+id+", PW: "+pw);
	     
	 }
	 
	 @Override
	 public void mousePressed(MouseEvent e) {
	  // TODO Auto-generated method stub
	  
	 }
	 @Override
	 public void mouseReleased(MouseEvent e) {
	  // TODO Auto-generated method stub
	  
	 }
	 @Override
	 public void mouseEntered(MouseEvent e) {
	  // TODO Auto-generated method stub
	  
	 }
	 @Override
	 public void mouseExited(MouseEvent e) {	
	  // TODO Auto-generated method stub
	  
	 }
	 
	 public void fileSave() throws IOException {
	        FileOutputStream output = new FileOutputStream("out.txt");
	        int row=jt.getRowCount();
	        int col=jt.getColumnCount();
	        
	        for(int i=0; i<row; i++) {
	        	for(int j=0;j<col;j++){
	        		String data=(String) jt.getValueAt(i, j);
	        		output.write(data.getBytes());
	        		String d1=" ";
	        		output.write(d1.getBytes());
	        	}
	        	String d2=(String)"\n";
	        	output.write(d2.getBytes());
	        }

	        output.close();

	 }

}
