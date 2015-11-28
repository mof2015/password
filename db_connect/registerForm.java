package db_connec_test;

import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class registerForm extends JFrame{
 
	 JLabel la_id, la_mail, la_pw, la_confirm, la_favo, la_name;
	 JTextField tf_id, tf_name;
	 JTextField tf_mail;
	 JPasswordField tf_pw, tf_confirm;
	 JButton bt_input, bt_cancel;
	 private JFrame controllingFrame;

	 public static Connection con;
	 public static PreparedStatement st;
	 public static ResultSet rs;
	
	 public registerForm() throws SQLException {
			String[] favorites = { "Sports", "Programming", "Information security", "Music"};
			JComboBox favoList = new JComboBox(favorites);
			
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/mof?useUnicode=true&characterEncoding=euckr", "root", "1234");
		
	  setTitle("Register");
	  la_id = new JLabel("Id");
	  la_mail = new JLabel("e-Mail address");
	  la_pw = new JLabel("Master password");
	  la_name = new JLabel("Name");
	  la_confirm = new JLabel ("Confirm");
	  la_favo = new JLabel ("Favorite");
	  
	  tf_id = new JTextField();
	  tf_mail = new JTextField();
	  tf_pw = new JPasswordField();
	  tf_name = new JTextField();
	  tf_confirm = new JPasswordField();
	  
	  bt_input = new JButton("OK");
	  bt_cancel = new JButton("Cancel");
	 
	  setLayout(null);
	  
	  la_id.setBounds(30, 20, 120, 30);
	  la_mail.setBounds(30, 60, 120, 30);
	  la_pw.setBounds(30, 100, 120, 30);
	  la_confirm.setBounds(30,140,120,30);
	  la_name.setBounds(30, 180, 120, 30);
	  la_favo.setBounds(30,220,120,30);
	  
	  tf_id.setBounds(160, 20, 120, 30);
	  tf_mail.setBounds(160, 60, 120, 30);
	  tf_pw.setBounds(160, 100, 120, 30);
	  tf_confirm.setBounds(160,140,120,30);
	  tf_name.setBounds(160, 180, 120, 30);
	  
	  bt_input.setBounds(80, 260, 60, 30);
	  bt_cancel.setBounds(150, 260, 100, 30);
	  favoList.setBounds(160,220,120,30);
	  
	  add(la_name);
	  add(la_id);
	  add(la_mail);
	  add(la_pw);
	  add(la_confirm);
	  add(la_favo);
	
	  add(tf_name);
	  add(tf_id);
	  add(tf_mail);
	  add(tf_pw);
	  add(tf_confirm);
	  add(favoList);
	
	  add(bt_input);  
	  add(bt_cancel);
	        
	  bt_cancel.addActionListener(new ActionListener (){
		  public void actionPerformed(ActionEvent e) {
				setVisible(false);
		  }
	  });

	  bt_input.addActionListener(new ActionListener () {
		  public void actionPerformed(ActionEvent e) {
			  String id_input = tf_id.getText();
			  String mail_input = tf_mail.getText();
			  String name_input = tf_name.getText();
			  String pw_input = tf_pw.getText();
			  String confirm_input = tf_confirm.getText();
			  
			  if(id_input.length() == 0) {
				  JOptionPane.showMessageDialog(controllingFrame,
							"Please enter your ID!",
							"Error Message",
							JOptionPane.ERROR_MESSAGE);
					return;
			  } else if(mail_input.length() == 0) {
				  JOptionPane.showMessageDialog(controllingFrame,
							"Please enter your email!",
							"Error Message",
							JOptionPane.ERROR_MESSAGE);
					return;				  
			  } else if(name_input.length() == 0) {
				  JOptionPane.showMessageDialog(controllingFrame,
							"Please enter your name!",
							"Error Message",
							JOptionPane.ERROR_MESSAGE);
					return;				  
			  } else if(pw_input.length() == 0) {
				  JOptionPane.showMessageDialog(controllingFrame,
							"Please enter your password!",
							"Error Message",
							JOptionPane.ERROR_MESSAGE);
					return;				  
			  } else if(confirm_input.length() == 0) {
				  JOptionPane.showMessageDialog(controllingFrame,
							"Please enter password confirm!",
							"Error Message",
							JOptionPane.ERROR_MESSAGE);
					return;				  
			  } else if(!pw_input.equals(confirm_input)) {
				  JOptionPane.showMessageDialog(controllingFrame,
							"Pasword and confirm doesn't match!",
							"Error Message",
							JOptionPane.ERROR_MESSAGE);
					return;				  
			  }
			  
			  // prepare sql statement
			  st = con.prepareStatement("INSERT INTO `account` (`id`, `pw`, `name`, `email`) VALUES (?, ?, ?, ?)");
			  
			  
			  try {
				  // bind params into sql statement
				  st.setString(1, id_input);
				  st.setString(2, pw_input);
				  st.setString(3, name_input);
				  st.setString(4, mail_input);
				  
				  // execute query
				  st.executeUpdate();
			  } catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			  }
			  
			  setVisible(false);
		  }
	  });

	  setBounds(0, 0, 330, 330);
	  setLocationRelativeTo(null);
	
	  setResizable(false);  //fix frame size
	  setVisible(true);

	 }
	
		
 } 
