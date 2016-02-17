package UserInterface;

import MusicManager.*;

import java.io.File;
import java.util.Scanner;


public class ConsoleUI {


    public static void main(String[] args){
        askForMp3File();

    }

    private static void printMenuOptions(){
        System.out.println("Choose your action:");
        System.out.println("(1) M3U Creator");
        System.out.println("(2) M3U Reader");
        System.out.println("(3) MP3 Splitter");
        System.out.printf("> ");
    }

    private static boolean askForMp3File(){
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Path of the mp3 file that you want to split\n> ");
        String input = scanner.nextLine();
        File file = new File(input);

        if(file.isFile() && file.getName().endsWith(".mp3")) {
            MP3Splitter.setMp3File(file);
            return true;
        }else{
            System.out.println("File not exist or file is not an mp3 file.");
            return false;
        }
    }


}
