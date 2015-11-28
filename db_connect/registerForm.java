package db_connec_test;

import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

	 public static Connection con;
	 public static Statement st;
	 public static ResultSet rs;
	
	 public registerForm() throws SQLException {
			String[] favorites = { "Sports", "Programming", "Information security", "Music"};
			JComboBox favoList = new JComboBox(favorites);
			
		con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/mof?useUnicode=true&characterEncoding=euckr", "root", "1234");
		st = con.createStatement();
		
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
				
				
			  String sql = "INSERT INTO `account` (`id`, `pw`, `name`, `email`) VALUES ('"+id_input+"','"+pw_input+"','"+name_input+"','"+mail_input+"')";
				try {
					st.executeUpdate(sql);
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
