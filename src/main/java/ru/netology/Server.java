package ru.netology;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int PORT = 8080;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started"); //output 1
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    System.out.println("New connection accepted"); //output 2
                    out.println(String.format("Hi %s, your port is %d", in.readLine(), clientSocket.getPort())); //output 3b
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}