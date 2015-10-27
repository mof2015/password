
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class registerForm extends JFrame{
 
	 JLabel la_mail, la_pw, la_confirm, la_favo;
	 JTextField tf_mail;
	 JPasswordField tf_pw, tf_confirm;
	 JButton bt_input, bt_cancel;
	
	 public registerForm() {
			String[] favorites = { "Sports", "Programming", "Information security", "Music"};
			JComboBox favoList = new JComboBox(favorites);
		
	  setTitle("Register");
	  la_mail = new JLabel("e-Mail address");
	  la_pw = new JLabel("Master password");
	  la_confirm = new JLabel ("Confirm");
	  la_favo = new JLabel ("Favorite");
	  
	  tf_mail = new JTextField();
	  tf_pw = new JPasswordField();
	  tf_confirm = new JPasswordField();
	  
	  bt_input = new JButton("OK");
	  bt_cancel = new JButton("Cancel");
	 
	  setLayout(null);
	  
	  la_mail.setBounds(30, 30, 120, 30);
	  la_pw.setBounds(30, 70, 120, 30);
	  la_confirm.setBounds(30,110,120,30);
	  la_favo.setBounds(30,150,120,30);
	  
	  tf_mail.setBounds(160, 30, 120, 30);
	  tf_pw.setBounds(160, 70, 120, 30);
	  tf_confirm.setBounds(160,110,120,30);
	  
	  bt_input.setBounds(80, 200, 60, 30);
	  bt_cancel.setBounds(150, 200, 100, 30);
	  favoList.setBounds(160,150,120,30);
	  
	  add(la_mail);
	  add(la_pw);
	  add(la_confirm);
	  add(la_favo);
	
	  add(tf_mail);
	  add(tf_pw);
	  add(tf_confirm);
	  add(favoList);
	
	  add(bt_input);  
	  add(bt_cancel);
	        
	  setBounds(0, 0, 330, 300);
	  setLocationRelativeTo(null);
	
	  setResizable(false);  //fix frame size
	  setVisible(true);

	 }
 } 

