package com.murilonerdx.basico;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class DalleeExample {
    public static void main(String[] args) throws IOException {
        // Prompt the user for a text description
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a text description: ");
        String text = scanner.nextLine();
        scanner.close();

        // Encode the text description as a URL
        String encodedText = java.net.URLEncoder.encode(text, "UTF-8");

        // Send a request to the DALL-E API and get the generated image
        URL url = new URL("https://api.openai.com/v1/images/generations?model=image-alpha-001&prompt=" + encodedText);
        InputStream in = url.openStream();
        ImageIcon image = new ImageIcon(ImageIO.read(in));

        // Display the generated image
        JLabel label = new JLabel(image);
        javax.swing.JOptionPane.showMessageDialog(null, label);
    }
}
