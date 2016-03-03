package MusicManager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class M3UReader {
    private static final String FILE_NOT_EXISTS = "\tFile not exists.";
    public File m3uFile;
    public List<String> m3uContent = new ArrayList<>();
    public List<File> mp3FileList = new ArrayList<>();

    public M3UReader(File m3uFile) {
        this.m3uFile = m3uFile;
    }

    public void setM3uFile(File m3uFile) {
        this.m3uFile = m3uFile;
    }

    public List<File> getMp3FileList() {
        return mp3FileList;
    }

    private void readAllLinesFromM3u() throws IOException {
        String m3uLines;
        BufferedReader br = new BufferedReader(new FileReader(m3uFile));
        while ((m3uLines = br.readLine()) != null) {
            m3uContent.add(m3uLines);
        }
        br.close();
    }


    public List<String> returnFileList() {
        try {
            this.readAllLinesFromM3u();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> formattedM3u = new ArrayList<>();
        String concatenated;
        for (String m3uLine : m3uContent) {
            if (!m3uLine.startsWith("#")) {
                if (m3uLine.matches(".[^:]")) {
                    concatenated = m3uFile.getParent() + System.lineSeparator() + m3uLine;
                    if (!checkIfFileExists(concatenated))
                        concatenated += FILE_NOT_EXISTS;
                    else {
                        mp3FileList.add(new File(concatenated));
                    }
                } else {
                    concatenated = m3uLine;
                    if (!checkIfFileExists(concatenated))
                        concatenated += FILE_NOT_EXISTS;
                    else {
                        mp3FileList.add(new File(concatenated));
                    }
                }
                formattedM3u.add(concatenated);
            }
        }
        return formattedM3u;
    }

    private boolean checkIfFileExists(String path) {
        File file = new File(path);
        return file.exists();
    }


}
