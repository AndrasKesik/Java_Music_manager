package UserInterface;

import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class GUI extends Frame implements ActionListener,WindowListener {
	 private Button btnM3UCreator; 
	 private Button btnM3UReader;
	 private Button btnMP3Splitter;
	 private JFrame frame;
	public GUI(){
		frame = new JFrame("Menu");
		 frame.setVisible(true);
		 frame.setSize(300, 200);;
		 frame.setLayout(null);
		 frame.setLocationRelativeTo(null);
		 btnM3UCreator = new Button("M3UCreator");
		 btnM3UCreator.setBounds(10, 40, 80, 80);// construct Button
		 frame.add(btnM3UCreator);
		 btnM3UReader = new Button("M3UReader");
		 btnM3UReader.setBounds(110, 40, 80, 80);// construct Button
		 frame.add(btnM3UReader);
		 btnMP3Splitter = new Button("MP3Splitter");
		 btnMP3Splitter.setBounds(210, 40, 80, 80);// construct Button
		 frame.add(btnMP3Splitter);
		 btnM3UCreator.addActionListener(this);
		 btnM3UReader.addActionListener(this);
		 btnMP3Splitter.addActionListener(this);
	    }
	public static void main(String[] args) {
		GUI menuwindow=new GUI();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnM3UCreator)
		{
			GUICreator creatorwindow=new GUICreator();
	    	frame.dispose();	
		}
		else if (e.getSource()==btnM3UReader){
			GUIReader readerwindow = new GUIReader();
			frame.dispose();
		}
		else if (e.getSource()==btnMP3Splitter){
			GUIMP3Splitter splitterwindow = new GUIMP3Splitter();
			frame.dispose();
		}
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
