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

    public void dispatch(File pathOfFolder) {
        File[] files = pathOfFolder.listFiles();
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

//    public static void makePieces(){
//        int pieceSize= (int)mp3File.length()/numberOfPieces;
//        List<Integer> sliceEdges = new ArrayList<>();
//        for(int i = 0; i<=numberOfPieces; i++){
//            sliceEdges.add(i*pieceSize);
//        }
//        System.out.println(sliceEdges.toString());
//        try {
//
//            FileInputStream fis = new FileInputStream(mp3File);
//            FileOutputStream fos = new FileOutputStream(newFolder + File.separator + getFileNameWithoutExtension()
//                    + "_" + "part" + "_" + 1 + ".mp3");
//            byte[] b = new byte[pieceSize];
//            fis.read(b,sliceEdges[0],sliceEdges[1]);
//
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();}
//
//    }

}
