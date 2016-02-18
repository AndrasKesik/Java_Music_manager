package UserInterface;

import java.awt.Button;
import java.awt.Desktop;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import MusicManager.MP3Splitter;

public class GUIMP3Splitter extends Frame implements ActionListener,WindowListener {
	private Label numberOfPieces;
	private TextField askingForPieces;
	private Button openFile;
	private TextField openedFile;
	private Button splitMP3;
	private TextField successedOrNot;
	JFileChooser choosenMP3;
	public File theChoosenOne;
	JFrame frame;
	public int pieces;
	
	public GUIMP3Splitter(){
		frame=new JFrame("MP3 Splitter");
		frame.addWindowListener(this);
		 frame.setVisible(true);
		 frame.setSize(300, 170);
		 frame.setLayout(null);
		 frame.setLocationRelativeTo(null);
		 numberOfPieces=new Label("Number of pieces: ");
		 numberOfPieces.setBounds(30, 10, 100, 20);
		 frame.add(numberOfPieces);
		 askingForPieces=new TextField();
		 askingForPieces.setBounds(150, 10, 120, 20);
		 frame.add(askingForPieces);
		 openFile=new Button("Open MP3 file");
		 openFile.setBounds(30, 50, 100, 20);
		 frame.add(openFile);
		 openedFile=new TextField("");
		 openedFile.setBounds(150, 50, 120, 20);
		 frame.add(openedFile);
		 splitMP3=new Button("Split");
		 splitMP3.setBounds(30,90,100,20);
		 openFile.addActionListener(this);
		 splitMP3.addActionListener(this);
		 splitMP3.disable();
		 frame.add(splitMP3);
		 choosenMP3=new JFileChooser();
		 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == openFile){
			int returnVal = choosenMP3.showOpenDialog(GUIMP3Splitter.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
            	File file = choosenMP3.getSelectedFile();
            	if( file.getName().endsWith(".mp3")){
                	openedFile.setText(file.getName());
                	splitMP3.enable();
                	theChoosenOne=file;
                }
                else{
                	JOptionPane.showMessageDialog(null, "Choose an mp3 file!");
                }
            }
        }
		else if(e.getSource()==splitMP3){
			try{
			pieces=Integer.parseInt(askingForPieces.getText());
			MP3Splitter splitter=new MP3Splitter(theChoosenOne,pieces);
			if (splitter.isDiractoryExists()){
				JOptionPane.showMessageDialog(null, "This diractory is exists");
			}
			else{
			splitter.makeDir();
			splitter.makePieces();
			String theFolder=splitter.getNewFolder();			
			JOptionPane.showMessageDialog(null,"Created at: "+ theFolder);
			Desktop.getDesktop().open(new File(theFolder));
			frame.dispose();
			GUI menuwindow=new GUI();
			}
			}
			catch(Exception ez){
				JOptionPane.showMessageDialog(null, "Number of pieces must be integer");
			}
		}
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent e) {
		GUI menuwindow=new GUI();
		
	}
	@Override
	public void windowClosed(WindowEvent e) {
	
		
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
