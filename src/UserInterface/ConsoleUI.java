package UserInterface;

import MusicManager.*;

import java.io.File;
import java.util.Scanner;


public class ConsoleUI {


    public static void main(String[] args){

        //askForM3UName();

       // MP3Splitter m = new MP3Splitter();
        // askForNumberOFPieces(m);
        //askForMp3File(m);
        //m.makeDir();
        //m.makePieces();
        //m.dispatch();
        

    }

    private static void printMenuOptions(){
        System.out.println("Choose your action:");
        System.out.println("(1) M3U Creator");
        System.out.println("(2) M3U Reader");
        System.out.println("(3) MP3 Splitter");
        System.out.printf("> ");
    }

    private static boolean askForMp3File(MP3Splitter mp3Splitter){
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Path of the mp3 file that you want to split\n> ");
        String input = scanner.nextLine();
        File file = new File(input);

        if(file.isFile() && file.getName().toLowerCase().endsWith(".mp3")) {
            mp3Splitter.setMp3File(file);
            return true;
        }else{
            System.out.println("File not exist or file is not an mp3 file.");
            return false;
        }
    }
    private static boolean askForNumberOFPieces(MP3Splitter mp3Splitter){
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Number of pieces: ");
        if(scanner.hasNextInt()){
            mp3Splitter.setNumberOfPieces(scanner.nextInt());
            return true;
        }else{
            System.out.println("That is not a number.");
            return false;
        }

    }

//	private static void askForM3UName(){
//        M3UReader reader = new M3UReader();
//        M3UReader.getPathOfM3UFromConsole(reader);
//	}


}
