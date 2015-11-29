package db_connec_test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class decryptForm extends JFrame {
	JLabel la_key;
	JTextField tf_key;
	JButton bt_input, bt_cancel;
	 
	public decryptForm(int no_id) {
		setTitle("Input decrypt key");
		la_key = new JLabel ("Key: ");
		tf_key = new JTextField();
		  
		bt_input = new JButton("OK");
		bt_cancel = new JButton("Cancel");
		tf_key.setText("");
		tf_key.requestFocus();

		setLayout(null);
		
		la_key.setBounds(30, 20, 120, 30);
		tf_key.setBounds(30, 60 , 250, 30);
		bt_input.setBounds(30, 110, 60, 30);
		bt_cancel.setBounds(100, 110, 120, 30);
	  
		add(la_key);
		add(tf_key);
		add(bt_input);  
		add(bt_cancel);
		        
		setBounds(500, 500, 300, 200);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		bt_input.addActionListener(new ActionListener (){
			public void actionPerformed(ActionEvent e) {
				//add DB here!!
				String key = tf_key.getText();
					try {
						new Main1(no_id, key);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				dispose();
			}
		});
		bt_input.addActionListener(new ActionListener (){
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

	}
	 
}
