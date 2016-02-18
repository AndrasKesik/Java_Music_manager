package MusicManager;

import java.io.*;

public class MP3Splitter {

    private File mp3File;
    private int numberOfPieces;
    private String newFolder;

    public  MP3Splitter(){}

    public MP3Splitter(File mp3File, int numberOfPieces) {
        this.mp3File = mp3File;
        this.numberOfPieces = numberOfPieces;
    }

    public void setMp3File(File file){
        mp3File = file;
    }

    public void setNumberOfPieces(int numberOfPieces){
        this.numberOfPieces = numberOfPieces;
    }

    public String getFileNameWithoutExtension(){
        String name = mp3File.getName();
        return name.substring(0,name.length()-4);}

    public void dispatch() {
        File newFolderDir = new File(newFolder);
        File[] files = newFolderDir.listFiles();
        System.out.println("...Done");
        for (File file : files) {
            System.out.println(file.getAbsolutePath());}
    }

    public void makeDir(){
        String dirName = getFileNameWithoutExtension() + "_" + numberOfPieces;
        newFolder = mp3File.getParent()+"\\"+dirName;
        File dir = new File(newFolder);
        dir.mkdir();
    }

    public void makePieces(){
        int pieceSize= (int)mp3File.length()/numberOfPieces;
        int[] sliceEdges = new int[numberOfPieces+1];
        for(int i = 0; i<=numberOfPieces; i++){
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

    private String nameOfPart(int number){
        return newFolder + File.separator + getFileNameWithoutExtension() + "_" + "part" + "_" + number + ".mp3";
    }
}
