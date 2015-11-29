import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EditEmailForm extends JFrame {
	JLabel la_box;
	JTextField tf_email;
	JButton bt_input, bt_cancel;
	ChangeListener listener;
	 
	public EditEmailForm() {

		setTitle("Edit Master Email");
		  
		la_box = new JLabel ("Edit Email");
		  
		tf_email = new JTextField();
		  
		bt_input = new JButton("OK");
		bt_cancel = new JButton("Cancel");
		 
		setLayout(null);
		
		//Component setting and addition
		la_box.setBounds(30, 30, 120, 30);
		tf_email.setBounds(30, 80 , 250, 30);
		bt_input.setBounds(30, 300, 60, 30);
		bt_cancel.setBounds(100, 300, 120, 30);
	  
		add(la_box);
		add(tf_email);
		add(bt_input);  
		add(bt_cancel);
		        
		setBounds(500, 500, 400, 400);
		setResizable(false);    
	 }
	 
	//Edit master email form
	public void initEditEmail(){
		setTitle("Edit Master email");

		tf_email.setText("");
		tf_email.requestFocus();
	}
}
