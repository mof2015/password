import java.awt.*;
import java.awt.event.*;
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

	public Intro(){

		setLayout(new FlowLayout());
		menubar = new JMenuBar();
		add(menubar);
		help=new JMenu ("Help");
		menubar.add(help);
		info=new JMenuItem("Info");
		help.add(info);
		setJMenuBar(menubar);
		event e = new event();
		info.addActionListener(e);

        idField=new JTextField(10);
        passwordField = new JPasswordField(10);
        passwordField.setActionCommand(OK);

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
		buttonPane = createButtonPanel();
		add(buttonPane);

	}
	protected JComponent createButtonPanel() {
		
        JPanel p = new JPanel(new GridLayout(0,1));
        JButton okButton = new JButton("OK");
        JButton registerButton = new JButton("REGISTER");

        okButton.setActionCommand(OK);
        registerButton.setActionCommand(REGISTER);

        pwEvent f=new pwEvent();
        
        okButton.addActionListener(f);
        registerButton.addActionListener(f);

        p.add(okButton);
        p.add(registerButton);

        return p;
    }

	public class event implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Info gui = new Info(Intro.this);
			gui.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			gui.setSize(300,100);
			gui.setLocation(300,300);
			gui.setVisible(true);

		}
	}

	public class pwEvent implements ActionListener{
		public void actionPerformed(ActionEvent e){
			 String cmd = e.getActionCommand();

		        if (OK.equals(cmd)) { //Process the password.
		            char[] input = passwordField.getPassword();
		            if (isPasswordCorrect(input)) {
		            	new Main1();
		    			dispose();

		            } 
		            else {
		                JOptionPane.showMessageDialog(controllingFrame,
		                    "Invalid password. Try again.",
		                    "Error Message",
		                    JOptionPane.ERROR_MESSAGE);
		            }

		            //Zero out the possible password, for security.
		            Arrays.fill(input, '0');

		            passwordField.selectAll();
		            resetFocus();
		        } else { //user asked for register
		        	new registerForm();
		    
		        }
		}
	}
    private static boolean isPasswordCorrect(char[] input) {
        boolean isCorrect = true;
        char[] correctPassword = { 'a', 's', 'd', 'f', 'g', 'h' };

        if (input.length != correctPassword.length) {
            isCorrect = false;
        } else {
            isCorrect = Arrays.equals (input, correctPassword);
        }

        //Zero out the password.
        Arrays.fill(correctPassword,'0');

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

		gui.setTitle("Intro");
	}
}
