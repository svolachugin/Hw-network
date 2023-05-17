package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static final int PORT = 8989;
    private static final String HOST = "localhost";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try (Socket clientSocket = new Socket(HOST, PORT);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            String incoming = in.readLine();
            if (incoming.equals("???")) {
                System.out.println("Введите название любого города: ");
            } else {
                System.out.println("Последний введенный город: " + incoming);
                System.out.println("Введите название города: ");
            }
            String outgoing = scanner.nextLine();
            out.println(outgoing);
            System.out.println(in.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}