package com.murilonerdx.basico;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.jhlabs.image.AbstractBufferedImageOp;
import com.jhlabs.image.GrayscaleFilter;
import com.jhlabs.image.ImageUtils;
import com.jhlabs.image.InvertFilter;
import com.jhlabs.image.ThresholdFilter;

public class TextImageGenerator2 {
    public void generateImage(String text, String outputFile) throws IOException {
        // Create a BufferedImage to draw the text on
        BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        // Draw the text on the image
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString(text, 50, 50);
        g.dispose();

        // Apply image filters to the image
        AbstractBufferedImageOp grayscaleFilter = new GrayscaleFilter();
        image = grayscaleFilter.filter(image, null);
        AbstractBufferedImageOp invertFilter = new InvertFilter();
        image = invertFilter.filter(image, null);
        ThresholdFilter thresholdFilter = new ThresholdFilter();
        thresholdFilter.setLowerThreshold(128);
        image = thresholdFilter.filter(image, null);

        // Save the image to a file
        ImageIO.write(image, "png", new File(outputFile));
    }
}
