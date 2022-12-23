package com.murilonerdx.basico;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TextImageGenerator {
    private static final int IMAGE_WIDTH = 800;
    private static final int IMAGE_HEIGHT = 600;
    private static final String FONT_NAME = "Arial";
    private static final int FONT_STYLE = Font.PLAIN;
    private static final int FONT_SIZE = 32;

    public void generateImage(String text, String outputFile) throws IOException {
        BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font(FONT_NAME, FONT_STYLE, FONT_SIZE));
        graphics.drawString(text, 10, 50);
        ImageIO.write(image, "jpg", new File(outputFile));
    }
}
