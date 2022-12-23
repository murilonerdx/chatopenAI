package com.murilonerdx.basico.semantica;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ArtGenerator {
    public static void main(String[] args) throws IOException {
        // Cria uma imagem em branco
        int width = 500;
        int height = 500;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Obtém o objeto Graphics2D da imagem
        Graphics2D g2d = img.createGraphics();

        // Desenha uma elipse vermelha no centro da imagem
        g2d.setColor(Color.RED);
        g2d.fillOval(width/2-100, height/2-100, 200, 200);

        // Desenha um retângulo azul no canto superior esquerdo da imagem
        g2d.setColor(Color.BLUE);
        g2d.fillRect(0, 0, 100, 100);

        // Salva a imagem em um arquivo
        ImageIO.write(img, "png", new File("art.png"));
    }
}
