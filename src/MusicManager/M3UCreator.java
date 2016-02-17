package MusicManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

public class M3UCreator {
	public String resultName;
	public ArrayList<File> fileList;
	
	public M3UCreator(ArrayList<File> fileList) {
		this.fileList = fileList;
	}
	
	public void setResultName(String resultName){
		String absolutePath=fileList.get(0).getParent();
		absolutePath+="\\";
		absolutePath+=resultName;
		absolutePath+=".m3u";
		System.out.println(absolutePath);
		File m3uFile=new File(absolutePath);
		if (m3uFile.exists()){
			System.out.println("There is a list with this name");
		}
		else{
			writeM3U(m3uFile);
		}
	}
	
	public void writeM3U(File m3uFile){
		FileWriter w=null;
		try {
			w= new FileWriter(m3uFile);
			w.write("#EXTM3U"+System.getProperty("line.separator"));
			for (File mp3:fileList)
			w.write(mp3+System.getProperty("line.separator"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				w.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}



	public static void main(String[] args) {
		ArrayList<File> fileList=new ArrayList<>();
		fileList.add(new File("C:\\Users\\Szekely Peter\\Desktop\\zene\\04 Clown.mp3"));
		fileList.add(new File("C:\\Users\\Szekely Peter\\Desktop\\zene\\01 Bastards Back Home.mp3"));
		fileList.add(new File("C:\\Users\\Szekely Peter\\Desktop\\zene\\04 Clown.mp3"));
		fileList.add(new File("C:\\Users\\Szekely Peter\\Desktop\\zene\\04 Freedom.mp3"));
		fileList.add(new File("C:\\Users\\Szekely Peter\\Desktop\\zene\\AC-DC - Highway To Hell.mp3"));
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s="";
		try {
			s = br.readLine();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		 M3UCreator thisM3U=new M3UCreator(fileList);
		 thisM3U.setResultName(s);
		
		
	}
}
