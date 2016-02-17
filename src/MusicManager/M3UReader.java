package MusicManager;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class M3UReader {
    public String pathOfM3U;

    public void setPathOfM3U(String pathOfM3U) {
        this.pathOfM3U = pathOfM3U;
    }

    static void getPathOfM3UFromConsole(M3UReader reader) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the M3U files absolute path: ");
        String givenPath = scanner.nextLine();
        reader.setPathOfM3U(givenPath);
        stringToFile(givenPath);
    }

    static boolean checkIfFileExist(File m3uFile) {
        if (!m3uFile.exists()) {
            System.err.println("The file doesn't exists.");
            return false;
        } else return true;
    }

    static void stringToFile(String filePath) {
        File m3uFile = new File(filePath);
        checkIfFileExist(m3uFile);
    }

    public static void main(String[] args) {
        M3UReader reader = new M3UReader();
        getPathOfM3UFromConsole(reader);
    }

}
