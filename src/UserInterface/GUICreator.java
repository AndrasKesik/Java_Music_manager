package UserInterface;


import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import MusicManager.M3UCreator;

public class GUICreator extends Frame implements ActionListener,WindowListener { 
	 private Label nameOfM3U;    // Declare component Label
	 public TextField askingForName;
	 public TextArea mp3FileList;
	 private Button btnSave;
	 private Button btnAdd;
	 private Button btnClear;
	 public ArrayList<File> fileList;
	 private Label successedOrNot;
	 JFileChooser choosenMP3s;
	
	public GUICreator(){
		JFrame frame = new JFrame("M3U Creator");
		frame.addWindowListener(this);
		 frame.setVisible(true);
		 frame.setSize(300, 300);
		 frame.setLayout(null);
		 frame.setLocationRelativeTo(null);
		 nameOfM3U = new Label("Name of M3U: ");
		 nameOfM3U.setBounds(10, 10, 100, 20);// construct Button
		 frame.add(nameOfM3U);
		 askingForName=new TextField();
		 askingForName.setBounds(120, 10, 150, 20);
		 frame.add(askingForName);
		 btnSave=new Button("Save");
		 btnSave.setBounds(10,40,50,20);
		 frame.add(btnSave);
		 successedOrNot=new Label("");
		 successedOrNot.setBounds(70, 40, 200, 20);
		 frame.add(successedOrNot);
		 mp3FileList=new TextArea();
		 mp3FileList.setBounds(10,70,200,180);
		 frame.add(mp3FileList);
		 btnAdd=new Button("Add");
		 btnAdd.setBounds(220,90,60,60);
		 frame.add(btnAdd);
		 btnClear=new Button("Clear");
		 btnClear.setBounds(220,170,60,60);
		 frame.add(btnClear);
		 btnAdd.addActionListener(this);
		 btnClear.addActionListener(this);
		 btnSave.addActionListener(this);
		 choosenMP3s=new JFileChooser();
		 fileList=new ArrayList<>();
	}
		 
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			int returnVal = choosenMP3s.showOpenDialog(GUICreator.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = choosenMP3s.getSelectedFile();
                String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length());
                String mp3="mp3";
                if (!mp3.equals(extension)){
                	JOptionPane.showMessageDialog(null, "Choose an mp3 file!");
                }
                else{
                fileList.add(file);
                mp3FileList.append(file.getName()+System.lineSeparator());}
                //This is where a real application would open the file.                
            }
		}
		else if (e.getSource() == btnClear){
			mp3FileList.setText("");
			fileList=null;
		}
		else if (e.getSource()==btnSave){
			int returnVal = choosenMP3s.showSaveDialog(GUICreator.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = choosenMP3s.getSelectedFile();
                String path=file.getParent();
                M3UCreator creator=new M3UCreator(fileList);
                successedOrNot.setText(creator.setResultName(askingForName.getText(),path));
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
	}

}

