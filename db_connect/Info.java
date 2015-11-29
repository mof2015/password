package db_connec_test;
//�씠 �겢�옒�뒪�뒗 �궗�떎�긽 �븘�슂 �뾾�쓣�벏... �옄諛붾뒗 �떎以묒긽�냽�씠 �븞�맗�땲�떦 �뀪�뀪�뀪�뀪

import java.awt.*;
import javax.swing.*;

public class Info extends JDialog	{
	JLabel label;
	
	public Info(JFrame frame){
		super(frame, "Info", true);
		setLayout(new FlowLayout());
		label = new JLabel("Copyright (C) Korea University Information Security MOF Team");
		add(label);
	}

}
