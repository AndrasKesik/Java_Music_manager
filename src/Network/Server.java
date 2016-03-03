package Network;

import MusicManager.MP3Splitter;

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

    public Server() {

        try {
            serverSocket = new ServerSocket(4444);
            System.out.println("Server started...");
        } catch (IOException e) {
            System.err.println("Port is already in use");
            System.exit(1);
        }

        while (true) {

            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed");
                System.exit(1);
            }
            System.out.println("---------------------------------------------------");
            System.out.println("+ Connection successful from: " + clientSocket.getInetAddress());


            try {
                ois = new ObjectInputStream(clientSocket.getInputStream());
            } catch (IOException e) {
                System.out.println("Stream initialization problem");
            }

            Object inputObject;
            while (true) {


                try {
                    inputObject = ois.readObject();
                } catch (Exception e) {
                    System.out.println("ObjectInputStream is empty. Disconnecting client...");
                    break;
                }

                if (inputObject instanceof Command) {
                    Command command = (Command) inputObject;

                    if (command.equals(Command.SPLIT)) {
                        System.out.println("SPLIT");
                        File savedFile = saveFile();

                        MP3Splitter mp3Splitter = new MP3Splitter(savedFile, parts);
                        mp3Splitter.makeDirectory(savedFile, parts);
                        mp3Splitter.makePieces();
                        List<File> fileList = mp3Splitter.getPartFilesList();
                        System.out.println(fileList.get(0));
                        System.out.println(fileList.size());

                        for(File f:fileList){
                            sendFile(f);
                        }


                    } else if (command.equals(Command.READ)) {
                        System.out.println("READ");
//                        String content = "";
//                        try {
//                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//                            content += bufferedReader.readLine();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        System.out.println(content);

                    } else if (command.equals(Command.CREATE)) {
                        System.out.println("CREATE");
                    }
                }
            }

            try {
                clientSocket.close();
                System.out.println("- Disconnected: " + clientSocket.getInetAddress());
                System.out.println("---------------------------------------------------");
            } catch (IOException e) {
                System.out.println("Clientsocket close problem");
            }

        }
    }


    public static void main(String[] args) {
        new Server();
    }


    private File saveFile() {
        File file = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            parts = ois.read();
            file = (File) ois.readObject();
            DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
            FileOutputStream fos = new FileOutputStream(PATH_TO_SAVE + file.getName());
            byte[] buffer = new byte[4096];
            int read;
            long remaining = file.length();
            while ((read = dis.read(buffer, 0, Math.min(buffer.length, (int) remaining))) > 0) {
                remaining -= read;
                fos.write(buffer, 0, read);
            }
//            fos.close();
//            dis.close();
//            ois.close();
        } catch (Exception e) {
            System.out.println("File save error");
        }
        return new File(PATH_TO_SAVE + file.getName());
    }


    private void sendFile(File file) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            oos.writeObject(file);

            DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[4096];
            int read;
            long remaining = file.length();
            while ((read = fis.read(buffer, 0, Math.min(buffer.length, (int) remaining))) > 0) {
                remaining -= read;
                dos.write(buffer, 0, read);
            }
//            oos.close();
//            socket.close();
        } catch (IOException e) {
            System.err.println("sendFile");
        }
    }
}

