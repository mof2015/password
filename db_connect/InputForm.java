package db_connec_test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LinearGradientPaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicProgressBarUI;


public class InputForm extends JFrame{
	JLabel la_name, la_id, la_pw, la_link, la_stren, la_len;
	JTextField tf_name, tf_id, tf_pw, tf_link, tf_len;
	JButton bt_input, bt_cancel;
	JSlider slider, tmpslider;
	ChangeListener listenForSlider;
	DocumentListener listenForProgressBar;
	JProgressBar strength;
	JRadioButton selector1, selector2;
	JPanel radioPanel;
	JTextField includeChar=new JTextField();
	JTextField includeNum=new JTextField();
	
	public InputForm() {
		la_name = new JLabel("Name");
		la_id = new JLabel("id");
		la_pw = new JLabel("pw");
		la_link = new JLabel ("link");
		la_stren = new JLabel ("Strength");
		la_len = new JLabel ("Length");
		

		tf_name = new JTextField();
		tf_id = new JTextField();
		tf_pw = new JTextField();
		tf_link = new JTextField();
		tf_len = new JTextField();
		
		bt_input = new JButton("OK");
		bt_cancel = new JButton("Cancel");
		
		selector1 = new JRadioButton("random");
		selector1.setActionCommand("random");
		selector2 = new JRadioButton("custom");
		selector2.setActionCommand("custom");
		ButtonGroup group = new ButtonGroup();
		group.add(selector1);
		group.add(selector2);
		radioPanel = new JPanel(new GridLayout(1, 0));
		radioPanel.add(selector1);
		radioPanel.add(selector2);
		radioPanel.setBounds(100,180,300,30);
		add(radioPanel);
        
		//password strength measurement
		listenForProgressBar=new DocumentListener(){
			public void stren(){
				Checker check = new Checker();
				strength.setValue(check.checker(tf_pw.getText()));
			}

			public void changedUpdate(DocumentEvent e) {
				stren();
			}

			public void insertUpdate(DocumentEvent e) {
				stren();
			}

			public void removeUpdate(DocumentEvent e) {
				stren();
			}
		};
		
		strength = new JProgressBar(0, 100); //The strength status bar range
		strength.setBounds(230, 140, 150, 10);
		strength.setUI(new ProgressBarUI());
		tf_pw.getDocument().addDocumentListener(listenForProgressBar);
        
		tmpslider = new JSlider(8,20,10);
		tmpslider.setPaintTicks(true);
		tmpslider.setMajorTickSpacing(5);
		tmpslider.setMinorTickSpacing(1);     
		tmpslider.addChangeListener(listenForSlider);
		tmpslider.setBounds(75,210,230,50);
		add(tmpslider);

		includeChar.setBounds(70,260,150,30);
		includeNum.setBounds(250,260,70,30);
		includeChar.setText("");
		includeNum.setText("");
		add(includeChar);
		add(includeNum);
		includeChar.setVisible(false);
		includeNum.setVisible(false);

		
		selector1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				includeChar.setVisible(false);
				includeNum.setVisible(false);

				listenForSlider = new ChangeListener(){
					public void stateChanged(ChangeEvent e) {
						Generator my=new Generator();				
						slider = (JSlider) e.getSource();
						tf_len.setText("" + slider.getValue());
						my.self_password=0;
						my.type=1;
						my.length=slider.getValue();
						tf_pw.setText(my.getPassword());
					}
				};

				tmpslider.setVisible(false);
				slider = new JSlider(8,20,10);
				slider.setPaintTicks(true);
				slider.setMajorTickSpacing(5);
				slider.setMinorTickSpacing(1);     
				slider.addChangeListener(listenForSlider);
				slider.setBounds(75,210,230,50);
				add(slider);
			}
		});
		selector2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				includeChar.setVisible(true);
				includeNum.setVisible(true);

				listenForSlider = new ChangeListener(){
					public void stateChanged(ChangeEvent e) {
						
						
						Generator my=new Generator();
						slider = (JSlider) e.getSource();
						tf_len.setText("" + slider.getValue());
						my.self_password=1;
						my.setIncludeChar(includeChar.getText());
						my.setIncludeNum(Integer.parseInt(includeNum.getText()));
						my.type=1;
						my.length=slider.getValue();
						tf_pw.setText(my.getPassword());
					}
				};

				tmpslider.setVisible(false);
				slider = new JSlider(8,20,10);
				slider.setPaintTicks(true);
				slider.setMajorTickSpacing(5);
				slider.setMinorTickSpacing(1);     
				slider.addChangeListener(listenForSlider);
				slider.setBounds(75,210,230,50);
				add(slider);
			}
		});

		setTitle("Add new");
		setLayout(null);
	 
		la_name.setBounds(30, 30, 60, 30);
		la_id.setBounds(30, 80, 30, 30);
		la_pw.setBounds(30, 130, 30, 30);
		la_link.setBounds(30,300,30,30);
		la_len.setBounds(20,220,60,30);

		//UI setting
		tf_name.setBounds(90, 30, 120, 30);
		tf_id.setBounds(90, 80, 120, 30);
		tf_pw.setBounds(90, 130, 120, 30);
		tf_link.setBounds(90, 300, 200, 30);
		tf_len.setBounds(330,220,50,30);
	  
		bt_input.setBounds(30, 400, 60, 30);
		bt_cancel.setBounds(100, 400, 120, 30);
		
		
		//Component addition
		add(la_len);
		add(tf_len);
		add(la_name);
		add(la_id);
		add(la_pw);
		add(la_link);

		add(strength);
	  
		add(tf_name);
		add(tf_id);
		add(tf_pw);
		add(tf_link);
		add(bt_input);  
		add(bt_cancel);
	  
		setBounds(500, 500, 400, 600);
		setResizable(false);
	}
	
	//Constructor for function windows
	public void initTF(){
		setTitle("Add new");
		tf_name.setText("");
		tf_id.setText("");
		tf_pw.setText("");
		tf_link.setText("");
		tf_name.requestFocus();
	}
 
	public void initUp(){//Edit form
		setTitle("Edit");
	}
}
class ProgressBarUI extends BasicProgressBarUI {
	private final int[] pallet;
	public ProgressBarUI() {
		super();
		this.pallet = makeGradientPallet();
	}
	private static int[] makeGradientPallet() {
		BufferedImage image = new BufferedImage(100, 1, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2  = image.createGraphics();
		Point2D start  = new Point2D.Float(0f, 0f);
		Point2D end    = new Point2D.Float(99f, 0f);
		float[] dist   = {0.0f, 0.5f, 1.0f};
		Color[] colors = { Color.RED, Color.YELLOW, Color.GREEN};
		g2.setPaint(new LinearGradientPaint(start, end, dist, colors));
		g2.fillRect(0, 0, 100, 1);
		g2.dispose();

		int width  = image.getWidth(null);
		int[] pallet = new int[width];
		PixelGrabber pg = new PixelGrabber(image, 0, 0, width, 1, pallet, 0, width);
		try {
			pg.grabPixels();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return pallet;
	}
	
	private static Color getColorFromPallet(int[] pallet, float x) {
		if(x < 0.0 || x > 1.0) {
			throw new IllegalArgumentException("Parameter outside of expected range");
		}
		int i = (int)(pallet.length * x);
		int max = pallet.length-1;
		int index = i<0?0:i>max?max:i;
		int pix = pallet[index] & 0x00ffffff | (0x64 << 24);
		return new Color(pix, true);
	}
	public void paintDeterminate(Graphics g, JComponent c) {
		if (!(g instanceof Graphics2D)) {
			return;
		}
		Insets b = progressBar.getInsets(); // area for border
		int barRectWidth  = progressBar.getWidth()  - (b.right + b.left);
		int barRectHeight = progressBar.getHeight() - (b.top + b.bottom);
		if (barRectWidth <= 0 || barRectHeight <= 0) {
			return;
		}
	    // amount of progress to draw
		int amountFull = getAmountFull(b, barRectWidth, barRectHeight);

		if(progressBar.getOrientation() == JProgressBar.HORIZONTAL) {
	      // draw the cells
			float x = amountFull / (float)barRectWidth;
			g.setColor(getColorFromPallet(pallet, x));
			g.fillRect(b.left, b.top, amountFull, barRectHeight);
		}
	    // Deal with possible text painting
		if(progressBar.isStringPainted()) {
			paintString(g, b.left, b.top, barRectWidth, barRectHeight, amountFull, b);
		}
	}
}
