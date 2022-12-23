package com.murilonerdx.basico;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerExample {
    public static void main(String[] args) throws IOException {
        // Cria o servidor
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Servidor iniciado na porta 1234");

        // Escuta por conexões de clientes
        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Conexão de cliente aceita: " + clientSocket);

            // Cria os streams de entrada e saída
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Lê a mensagem do cliente
            String request = in.readLine();
            System.out.println("Mensagem recebida do cliente: " + request);

            // Envia uma mensagem de volta para o cliente
            out.println("Obrigado por se conectar!");

            // Fecha a conexão do cliente
            clientSocket.close();
        }
    }
}
