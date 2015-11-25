//이 클래스는 사실상 필요 없을듯... 자바는 다중상속이 안됩니당 ㅠㅠㅠㅠ

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
