package db_connec_test;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.awt.event.*;

import javax.swing.*;

public class Intro extends JFrame {
	JMenuBar menubar;
	JMenu help;
	JMenuItem info;
	JComponent buttonPane;
	private static String OK = "ok";
	private static String REGISTER = "register";
	private JFrame controllingFrame; //needed for dialogs
	private JTextField idField;
	private JPasswordField passwordField;
	 public static Connection con;
	 public static Statement st;
	 public static ResultSet rs;
	 
	 public static String correct_id;
	 public static int no_id;

	public Intro(){

	pwEvent f=new pwEvent();

	setLayout(new FlowLayout());
	menubar = new JMenuBar();
	add(menubar);
	help=new JMenu ("Help");
	menubar.add(help);
	info=new JMenuItem("IPMS Ver.1.0");
	help.add(info);
	setJMenuBar(menubar);
	event e = new event();
	info.addActionListener(e);

        idField=new JTextField(10);
        passwordField = new JPasswordField(10);
        passwordField.addActionListener(f);

        JLabel label1 = new JLabel("id: ");
        label1.setLabelFor(idField);
        
        JLabel label = new JLabel("password: ");
        label.setLabelFor(passwordField);

        
        JPanel textPane = new JPanel();
        textPane.setLayout(new GridLayout(2,1));
        
        textPane.add(label1);
        textPane.add(idField);
        
        textPane.add(label);
        textPane.add(passwordField);

        add(textPane);
        JPanel p = new JPanel(new GridLayout(0,1));
        JButton okButton = new JButton("OK");
        JButton registerButton = new JButton("REGISTER");

        okButton.setActionCommand(OK);
        registerButton.setActionCommand(REGISTER);

        
        okButton.addActionListener(f);
        registerButton.addActionListener(f);

        p.add(okButton);
        p.add(registerButton);
        
		add(p);
    	}

	public class event implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Info gui = new Info(Intro.this);
			gui.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			gui.setSize(300,100);
			gui.setLocation(300,500);
			gui.setVisible(true);

		}
	}
	//checks both ID and password
	public class pwEvent implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String cmd = e.getActionCommand();
			
			if(REGISTER.equals(cmd))
				new registerForm();
			else{
				@SuppressWarnings("deprecation")
				String input = passwordField.getText();
				String id_input = idField.getText();
	        		try {
						if (isIDCorrect(id_input, input)) {
						try {
							System.out.println(no_id);
							new Main1(no_id);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
							dispose();
} 
						else {
							JOptionPane.showMessageDialog(controllingFrame,
							"Invalid ID or Password!! Try again!",
							"Error Message",
							JOptionPane.ERROR_MESSAGE);
						}
					} catch (HeadlessException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

	    			//Zero out the possible password, for security.
	    			input = null;
	    			passwordField.selectAll();
	    			resetFocus();
	    		}
			 
		    
	    	}
	}
	
	
	private static boolean isIDCorrect(String input, String input_pw) throws SQLException {
		boolean isCorrect = true;

		con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/mof", "root", "1234");
		st = con.createStatement();

		String sql = null;
		
		sql = "select * from account";

		rs = st.executeQuery(sql);
		
		if (st.execute(sql)) {
			rs = st.getResultSet();
		}


		while (rs.next()) {
			String str = rs.getString("id");
			
			if(input.length() != str.length()) {
	            isCorrect = false;
	        } else {
	            isCorrect = input.equals(str);
	            if(input.equals(str))
	            {
	        		correct_id = str;
	        		break;
	            }
	        }	
		}
		
		if(isCorrect)
		{
			isCorrect = false;
			
			sql = "select * from account where id = '"+correct_id+"'";
	
			rs = st.executeQuery(sql);
			
			if (st.execute(sql)) {
				rs = st.getResultSet();
			}
	
			while (rs.next()) {
				String str = rs.getString("pw");
				
				if (input_pw.length() != str.length()) {
					isCorrect = false;
				} else {
					isCorrect = input_pw.equals(str);
					if(isCorrect)
					{
						sql = "select no from account where id = '"+correct_id+"' and pw = '"+str+"'";
						rs = st.executeQuery(sql);
						
						if (st.execute(sql)) {
							rs = st.getResultSet();
						}
				
						while (rs.next()) {
							int number = rs.getInt("no");
							no_id = number;
							break;
						}							
				
						break;
					}
				}	
			}
		}		
		
		return isCorrect;
	}

	protected void resetFocus() {
		passwordField.requestFocusInWindow();
	}

	public static void main(String args[]){
		Intro gui =new Intro();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setSize(500,300);
		gui.setLocationRelativeTo(null);

		gui.setVisible(true);
		gui.setResizable(false);

		gui.setTitle("IMPS Ver.1.0");
	}
}
