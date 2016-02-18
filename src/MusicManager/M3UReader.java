package MusicManager;

import java.io.*;
import java.util.Scanner;

public class M3UReader {
    public String pathOfM3U;

    public void setPathOfM3U(String pathOfM3U) {
        this.pathOfM3U = pathOfM3U;
    }

    public static void getPathOfM3UFromConsole(M3UReader reader) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the M3U files absolute path: ");
        String givenPath = scanner.nextLine();
        reader.setPathOfM3U(givenPath);
        printContent(givenPath);
    }

    static void printContent(String filePath) {
        File m3uFile = new File(filePath);
        try {
            checkIfItIsM3U(m3uFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static boolean checkIfItIsM3U(File m3uFile) throws IOException {
        if (m3uFile.exists() && m3uFile.isFile() && m3uFile.getName().toLowerCase().endsWith(".m3u")) {
            readFromM3U(m3uFile);
            return true;
        } else {
            System.out.println("File not exist or file is not an m3u file.");
            return false;
        }
    }

    static void readFromM3U(File m3uFile){
        try {
            BufferedReader br = new BufferedReader(new FileReader(m3uFile));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                System.out.println(strLine);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
