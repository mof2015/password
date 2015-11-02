import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class InputForm extends JFrame{
 
 JLabel la_name, la_id, la_pw, la_link, la_stren;
 JTextField tf_name, tf_id, tf_pw, tf_link;
 JButton bt_input, bt_cancel;
 JTextField textField;
 JSlider slider;
 ChangeListener listener;
 
 public InputForm() {
          
	 listener = new ChangeListener()
     {
		public void stateChanged(ChangeEvent e) {
	           JSlider source = (JSlider) e.getSource();
	           textField.setText("" + source.getValue());
		}
     };

     slider = new JSlider();
     slider.setPaintTicks(true);
     slider.setMajorTickSpacing(20);
     slider.setMinorTickSpacing(5);     
     slider.addChangeListener(listener);
     
	  setTitle("Insert");
	  la_name = new JLabel("Name");
	  la_id = new JLabel("id");
	  la_pw = new JLabel("pw");
	  la_link = new JLabel ("link");
	  la_stren = new JLabel ("Strength");
	  
	  tf_name = new JTextField();
	  tf_id = new JTextField();
	  tf_pw = new JTextField();
	  tf_link = new JTextField();
	  
	  bt_input = new JButton("OK");
	  bt_cancel = new JButton("Cancel");
	 
	  
	  setLayout(null);
	  la_name.setBounds(30, 30, 60, 30);
	  la_id.setBounds(30, 80, 30, 30);
	  la_pw.setBounds(30, 130, 30, 30);
	  la_link.setBounds(30,230,30,30);
	  la_stren.setBounds(15,175,60,30);
	  
	  tf_name.setBounds(80, 30, 120, 30);
	  tf_id.setBounds(80, 80, 120, 30);
	  tf_pw.setBounds(80, 130, 120, 30);
	  tf_link.setBounds(80, 230, 200, 30);
	  
	  bt_input.setBounds(30, 300, 60, 30);
	  bt_cancel.setBounds(100, 300, 120, 30);
	  
	  slider.setBounds(75,170,230,50);
	  slider.addChangeListener(listener);
	  textField = new JTextField();
	  textField.setBounds(310,170,50,30);
	  add(textField);
	
	  add(la_name);
	  add(la_id);
	  add(la_pw);
	  add(la_link);
	  add(la_stren);
	  add(slider);
	
	  add(tf_name);
	  add(tf_id);
	  add(tf_pw);
	  add(tf_link);
	  add(bt_input);  
	  add(bt_cancel);
	        
	  setBounds(500, 500, 400, 400);
      setResizable(false);
        
 }//Constructor for function windows
 public void initTF(){
  setTitle("Insert");
  tf_name.setText("");
  tf_id.setText("");
  tf_pw.setText("");
  tf_link.setText("");
  tf_name.requestFocus();
  
 }
 
 public void initUp(){//Edit form
  setTitle("Edit");
 }
 
 public void initSearch(){//Search form
  setTitle("Search");
  tf_name.setText("");
  tf_name.requestFocus();
 }
 
 } 
