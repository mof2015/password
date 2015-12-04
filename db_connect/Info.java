package db_connec_test;
/*
 * Print Copyright
 * This file is unnecessary since the information to be printed is already done in Main1.java...
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
