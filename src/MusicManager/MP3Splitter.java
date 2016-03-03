package MusicManager;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class MP3Splitter {

    private File mp3File;
    private int numberOfPieces;
    private String newFolder;

    public MP3Splitter(File mp3File, int numberOfPieces) {
        this.mp3File = mp3File;
        this.numberOfPieces = numberOfPieces;
    }

    public List<File> getPartFilesList() {
        File newFolderDir = new File(newFolder);
        File[] files = newFolderDir.listFiles();
        List<File> fileList= Arrays.asList(files);
        return fileList;
    }

    public boolean makeDirectory(File file, int parts ){
        String dirName = getFileNameWithoutExtension() + "_" + parts;
        newFolder = file.getParent()+"\\"+dirName;
        File dir = new File(newFolder);
        return dir.mkdir();
    }

    public void makePieces() {
        int pieceSize= (int)mp3File.length()/numberOfPieces;
        int[] sliceEdges = new int[numberOfPieces];
        for(int i = 0; i<numberOfPieces; i++){
            sliceEdges[i]= (i*pieceSize);
        }
        try {
            FileInputStream fis = new FileInputStream(mp3File);
            byte[] b = new byte[(int)mp3File.length()];
            fis.read(b);
            for(int i =1; i<=numberOfPieces;i++) {
                FileOutputStream fos = new FileOutputStream(nameOfPart(i));
                fos.write(b,sliceEdges[i-1], pieceSize);
                fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();}
    }

    public String getFileNameWithoutExtension(){
        String name = mp3File.getName();
        return name.substring(0,name.length()-4);}

    public String nameOfPart(int number){
        return newFolder + File.separator + getFileNameWithoutExtension() + "_" + "part" + "_" + number + ".mp3";
    }
}
