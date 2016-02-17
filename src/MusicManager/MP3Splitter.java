package MusicManager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MP3Splitter {

    public static File mp3File;
    public static int numberOfPieces;
    public static String newFolder;

    private MP3Splitter(){}

    public static void setMp3File(File file){
        mp3File = file;
    }

    public static void setNumberOfPieces(int numberOfPieces){
        MP3Splitter.numberOfPieces = numberOfPieces;
    }

    public static String getFileNameWithoutExtension(){
        String name = mp3File.getName();
        return name.substring(0,name.length()-4);}

    public static void dispatch() {
        File newFolderDir = new File(newFolder);
        File[] files = newFolderDir.listFiles();
        for (File file : files) {
            System.out.println(file.getAbsolutePath());
        }
    }

    public static void makeDir(){
        String dirName = getFileNameWithoutExtension() + "_" + numberOfPieces;
        newFolder = mp3File.getParent()+"\\"+dirName;
        File dir = new File(newFolder);
        dir.mkdir();
    }

    public static void makePieces(){
        int pieceSize= (int)mp3File.length()/numberOfPieces;
        int[] sliceEdges = new int[numberOfPieces+1];
        for(int i = 0; i<=numberOfPieces; i++){
            sliceEdges[i]= (i*pieceSize);
            System.out.println(sliceEdges[i]);
        }

        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(mp3File,"r");
            //FileInputStream fis = new FileInputStream(randomAccessFile);
            byte[] b = new byte[(int)mp3File.length()];
            //fis.read(b);
            randomAccessFile.read(b);
            for(int i =1; i<=numberOfPieces;i++) {
                FileOutputStream fos = new FileOutputStream(newFolder + File.separator + getFileNameWithoutExtension()
                        + "_" + "part" + "_" + i + ".mp3");
                fos.write(b,sliceEdges[i-1], sliceEdges[i]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();}

    }

}
