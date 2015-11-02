﻿import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
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



public class Main1 implements MouseListener, ActionListener {
	JFrame jp = new JFrame("IPMS");
	JTable jt;
	DefaultTableModel dtm;
	InputForm form;
	SearchForm form_search;
	int srow;
	JMenuBar menuBar = new JMenuBar();
	JMenu mainMenu = new JMenu("Menu");
	JMenu helpMenu = new JMenu("Help");
	JScrollPane scroll;
	JLabel label;
	JPanel northp,southp;
	JButton bt_add, bt_del, bt_up, bt_search;

	public Main1() {
		form=new InputForm();
		form_search = new SearchForm();
		
		Object[][] rowData={
				{"네이버", "test","testtest", "http://www.naver.com/"},
				{"블랙보드", "black","fortest", "http://kulms.korea.ac.kr/"},
				{"다음", "daumdaum","daumtest", "http://www.daum.net/"}
		};
		
		Object[] columnNames={"Name","id","pw","link"};
		 
		dtm = new DefaultTableModel(rowData, columnNames);
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
		mainMenu.add(new JMenuItem("Log out"));
		mainMenu.getItem(0).setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_MASK ^ InputEvent.ALT_MASK)); //Hot key Setting
		mainMenu.addSeparator();
		mainMenu.add(new JMenuItem("Exit"));
		mainMenu.getItem(0).setAccelerator(KeyStroke.getKeyStroke('Q', InputEvent.ALT_MASK)); //Hot key Setting
		helpMenu.add(new JMenuItem("Version"));
		mainMenu.getItem(0).setAccelerator(KeyStroke.getKeyStroke('V', InputEvent.CTRL_MASK)); //Hot key Setting
		helpMenu.add(new JMenuItem("Info"));
		mainMenu.getItem(0).setAccelerator(KeyStroke.getKeyStroke('I', InputEvent.CTRL_MASK)); //Hot key Setting

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
	    bt_add.addActionListener(this);
	    bt_del.addActionListener(this);
	    bt_up.addActionListener(this);
	    bt_search.addActionListener(this);
	    
	    form.bt_input.addActionListener(this);
	    form.bt_cancel.addActionListener(this);
	    
	 }
	
	 public void actionPerformed(ActionEvent e) {

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
			
			if(form.getTitle().equals("Add new")){		//add new form
				Object rowData[] = {name, id, pw, link};
				dtm.addRow(rowData);
			}
			else {	//edit form
				jt.setValueAt(name, srow, 0);	//edit name
				jt.setValueAt(id, srow, 1);		//edit id
				jt.setValueAt(pw, srow, 2);		//edit pw
				jt.setValueAt(link, srow, 3);	//edit link

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
		     
			String name = (String) jt.getValueAt(srow,0);
			String id  = (String) jt.getValueAt(srow,1);
			//String pw  = (String) jt.getValueAt(srow,2);
			String link  = (String) jt.getValueAt(srow,3);
			
			form_search.tf_name.setText(name);
			form_search.tf_id.setText(id);
			//form.tf_pw.setText(pw);
			form_search.tf_link.setText(link);
			form_search.initSearch();
			form_search.setVisible(true);
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

}
