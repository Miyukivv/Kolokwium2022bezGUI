package com.example.demo;

import java.io.*;
import java.net.Socket;

public class ConnectionThread extends Thread{
    Socket socket;
    WordContainer wordContainer;

    public ConnectionThread(String address, int port, WordContainer wordContainer) throws IOException {
        socket = new Socket(address, port);
        this.wordContainer = wordContainer;
    }

    @Override
    public void run() {
        //Strumien danych  pochodzacych z socketa; zczytywane jest to co server przekaze, to do tego socketa
        try (InputStream inputStream = socket.getInputStream();
//             OutputStream outputStream = socket.getOutputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)))
        {
            String rawMessage;

            while ((rawMessage = reader.readLine()) != null){
//                System.out.println(rawMessage);
                wordContainer.addWord(rawMessage);

                System.out.println(wordContainer.toString());
                System.out.println("Words not added");
                System.out.println(wordContainer.getWordsNotAddedString());
                System.out.println();
                System.out.println();
            }
        } catch (IOException e) {
            System.err.println("Connection error: " + e.getMessage());
        }
    }
}
