package ru.netology;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int PORT = 8989;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер стартовал");
            String town = null;
            String townNew;
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                ) {
                    if (town == null) {
                        out.println("???");
                        town = in.readLine();
                        out.println("ОК");
                    } else {
                        out.println(town);
                        townNew = in.readLine();
                        if (town.charAt(town.length() - 1) == townNew.charAt(0)) {
                            town = townNew;
                            out.println("ОК");
                        } else {
                            out.println("НЕ ОК");
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}