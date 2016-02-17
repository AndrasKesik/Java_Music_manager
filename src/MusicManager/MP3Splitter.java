package MusicManager;

import java.io.File;

public class MP3Splitter {

    private static File mp3File;
    private static String fileName;
    private static int numberOfPieces;

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

    public void dispatch(String pathOfFolder){
    }

    public void makeDir(){}

    public void makePieces(){}
}
