package UserInterface;

import java.awt.Button;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import MusicManager.M3UReader;

public class GUIReader extends Frame implements ActionListener,WindowListener{
	private Button btnLoad;
	private TextArea mp3s;
	 JFileChooser choosenM3U;
	 
	public GUIReader() {
		JFrame frame = new JFrame("M3U Creator");
		frame.addWindowListener(this);
		 frame.setVisible(true);
		 frame.setSize(300, 300);
		 frame.setLayout(null);
		 frame.setLocationRelativeTo(null);
		 btnLoad=new Button("Load M3U file");
		 btnLoad.setBounds(80, 10, 100, 20);
		 frame.add(btnLoad);
		 btnLoad.addActionListener(this);
		 mp3s=new TextArea();
		 mp3s.setBounds(10,50,260,200);
		 frame.add(mp3s);
		 choosenM3U=new JFileChooser();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mp3s.setText(" ");
		int returnVal = choosenM3U.showOpenDialog(GUIReader.this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = choosenM3U.getSelectedFile();
            if( !file.getName().endsWith(".m3u")){
            	JOptionPane.showMessageDialog(null, "Choose an m3u file!");
            }
            else{
            	M3UReader reader=new M3UReader(file);
            	for (String path:reader.returnFileList()){
            		mp3s.append(path+"\n");
            	}
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
