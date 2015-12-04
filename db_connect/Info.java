package db_connec_test;
/*
 * Print Copyright
 */
import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class Info extends JDialog	{
	JLabel label;
	
	public Info(JFrame frame){
		super(frame, "Info", true);
		setLayout(new FlowLayout());
		label = new JLabel("Copyright (C) Korea University Information Security MOF Team");
		add(label);
	}

}
