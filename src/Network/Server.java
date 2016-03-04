package Network;

import MusicManager.M3UCreator;
import MusicManager.M3UReader;
import MusicManager.MP3Splitter;
import common.Command;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {

    public static String PATH_TO_SAVE = "c:\\new\\temp\\";

    Socket clientSocket = null;
    ServerSocket serverSocket = null;
    ObjectInputStream ois = null;

    int parts;

    public Server(int port) {

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started...");

            clientSocket = serverSocket.accept();
            System.out.println("---------------------------------------------------");
            System.out.println("+ Connection successful from: " + clientSocket.getInetAddress());
            ois = new ObjectInputStream(clientSocket.getInputStream());

            Object inputObject=null;
            while (true) {

                if(ois.read()>-1) {
                    inputObject = ois.readObject();

                    if (inputObject instanceof Command) {
                        Command command = (Command) inputObject;

                        if (command.equals(Command.SPLIT)) {
                            System.out.printf("SPLIT: ");
                            split();
                        } else if (command.equals(Command.READ)) {
                            System.out.println("READ");
                            read(ois);
                        } else if (command.equals(Command.CREATE)) {
                            System.out.println("CREATE");
                            create(ois);
                        } else if (command.equals(Command.EXIT)){
                            break;
                        }
                    }
                }
            }

            clientSocket.close();
            System.out.println("- Disconnected: " + clientSocket.getInetAddress());
            System.out.println("---------------------------------------------------");


        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Server(4444);
    }

    private void split() {
        File savedFile = saveFile();

        MP3Splitter mp3Splitter = new MP3Splitter(savedFile, parts);
        mp3Splitter.makeDirectory(savedFile, parts);
        mp3Splitter.makePieces();
        List<File> fileList = mp3Splitter.getPartFilesList();
        System.out.printf(" %d ",fileList.size());
        System.out.println(savedFile);
        for(File f:fileList){
            sendFile(f);
        }

    }
    private void read(ObjectInputStream objectInputStream){
        try {
            String tempFile = "C:\\new\\temp\\temp.m3u";
            String m3uContent = (String)objectInputStream.readObject();
            FileWriter fw = new FileWriter(tempFile);
            fw.write(m3uContent);
            fw.close();

            M3UReader m3UReader = new M3UReader(new File(tempFile));
            m3UReader.returnFileList();
            List<File> fileList = m3UReader.getMp3FileList();
            System.out.println(fileList);
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            oos.writeObject(fileList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void create(ObjectInputStream objectInputStream){
        try {
            Object o;
            if(!((o = objectInputStream.readObject()) instanceof List)){
                System.out.println("Not a list");
                return;
            }

            List<File> fileList = (List)o;
            System.out.println(fileList);
            M3UCreator m3UCreator = new M3UCreator(fileList);
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            oos.writeObject(m3UCreator.getFilesAsString());

        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    private File saveFile() {
        File file = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            parts = (int)ois.readObject();
            file = (File) ois.readObject();
            long fileSize = (long) ois.readObject();
            DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
            FileOutputStream fos = new FileOutputStream(PATH_TO_SAVE + file.getName());
            byte[] buffer = new byte[4096];
            int read;
            long remaining = fileSize;
            while ((read = dis.read(buffer, 0, Math.min(buffer.length, (int) remaining))) > 0) {
                remaining -= read;
                fos.write(buffer, 0, read);
            }
        } catch (Exception e) {
            System.out.println("File save error");
        }
        return new File(PATH_TO_SAVE + file.getName());
    }

    private void sendFile(File file) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            oos.writeObject(file);
            oos.writeObject(file.length());

            DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[4096];
            int read;
            long remaining = file.length();
            while ((read = fis.read(buffer, 0, Math.min(buffer.length, (int) remaining))) > 0) {
                remaining -= read;
                dos.write(buffer, 0, read);
            }

        } catch (IOException e) {
            System.err.println("sendFile");
        }
    }
}

