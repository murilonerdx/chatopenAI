package com.murilonerdx.basico;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerExample2 extends Application {
    private TextArea messagesArea;
    private ExecutorService executor;

    @Override
    public void start(Stage primaryStage) {
        // Cria a área de mensagens
        messagesArea = new TextArea();
        messagesArea.setEditable(false);

        // Cria o painel de rolagem para a área de mensagens
        ScrollPane scrollPane = new ScrollPane(messagesArea);

        // Cria a cena com o painel de rolagem
        Scene scene = new Scene(scrollPane, 400, 200);

        // Adiciona a cena à janela principal
        primaryStage.setScene(scene);
        primaryStage.setTitle("Servidor");
        primaryStage.show();

        // Inicia o servidor em uma thread separada
        executor = Executors.newCachedThreadPool();
        executor.execute(() -> {
            try {
                // Cria o servidor
                ServerSocket serverSocket = new ServerSocket(1234);
                appendMessage("Servidor iniciado na porta 1234");

                // Escuta por conexões de clientes
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    appendMessage("Conexão de cliente aceita: " + clientSocket);

                    // Cria os streams de entrada e saída
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                    // Lê a mensagem do cliente
                    String request = in.readLine();
                    appendMessage("Mensagem recebida do cliente: " + request);

                    // Envia uma mensagem de volta para o cliente
                    out.println("Obrigado por se conectar!");

                    // Fecha a conexão do cliente
                    clientSocket.close();
                }
            } catch (IOException e) {
                System.out.println("");
            }
        });
    }

    private void appendMessage(String message) {
        Platform.runLater(() -> messagesArea.appendText(message + "\n"));
    }
}