package MusicManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class M3UCreator {
	public String absolutePath;
	public List<File> fileList;
	
	public M3UCreator(List<File> fileList) {
		this.fileList = fileList;
		this.absolutePath=fileList.get(0).getParent();
	}

    public String getFilesAsString(){
        String paths = "#EXTM3U" + System.getProperty("line.separator");
        for(File f: fileList){
            paths += f + System.getProperty("line.separator");
        }
        return paths;
    }




	public String getResultName(String resultName,String absolutePath){
		if (absolutePath!=null){
			this.absolutePath=absolutePath;
		}
		absolutePath+="\\";
		absolutePath+=resultName;
		absolutePath+=".m3u";
		File m3uFile=new File(absolutePath);
		if (m3uFile.exists()){
			return "There is a list with this name";
		}
		else{
			writeM3U(m3uFile);
			return "Created here:" + absolutePath;
		}
		
	}

	public void writeM3U(File m3uFile){
		FileWriter w=null;
		try {
			w= new FileWriter(m3uFile);
			w.write("#EXTM3U"+System.getProperty("line.separator"));
			for (File mp3:fileList)
			w.write(mp3 + System.getProperty("line.separator"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				w.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}

}