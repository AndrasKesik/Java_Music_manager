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
        stringToFile(givenPath);
    }

    static void stringToFile(String filePath) {
        File m3uFile = new File(filePath);
        try {
            checkIfItIsM3U(m3uFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static boolean checkIfItIsM3U(File m3uFile) throws IOException {
        if (m3uFile.exists() && m3uFile.isFile() && m3uFile.getName().endsWith(".m3u")) {
            readFromM3U(m3uFile);
            return true;
        } else {
            System.out.println("File not exist or file is not an m3u file.");
            return false;
        }
    }

    static void readFromM3U(File m3uFile) throws UnsupportedEncodingException {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(m3uFile), "UTF-8"));
            String strLine;
            try {
                while ((strLine = br.readLine()) != null) {
                    System.out.println(strLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. ");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        M3UReader reader = new M3UReader();
        getPathOfM3UFromConsole(reader);
    }

}
