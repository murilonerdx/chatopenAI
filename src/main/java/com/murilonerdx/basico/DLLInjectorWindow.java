package com.murilonerdx.basico;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class DLLInjectorWindow extends JFrame {
    private JTextField dllPathField;
    private JButton injectButton;

    public DLLInjectorWindow() {
        // Crie um campo de texto para inserir o caminho da DLL
        dllPathField = new JTextField();
        dllPathField.setColumns(20);

        // Crie um botão para iniciar o processo de injeção
        injectButton = new JButton("Inject DLL");
        injectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtenha o caminho da DLL do campo de texto
                String dllPath = dllPathField.getText();

                try {
                    // Carregue a DLL
                    System.load(dllPath);

                    // Obtenha uma referência ao método da DLL que deseja invocar
                    Method method = DLLInjectorWindow.class.getMethod("exampleMethod");

                    // Invoque o método
                    method.invoke(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Adicione os componentes à janela
        add(dllPathField);
        add(injectButton);

        // Configure a janela
        setTitle("DLL Injector");
        setSize(300, 100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setVisible(true);
    }

    public static void main(String[] args) {
        new DLLInjectorWindow();
    }
}
