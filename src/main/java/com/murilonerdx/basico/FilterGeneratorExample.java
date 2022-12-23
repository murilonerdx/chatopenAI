package com.murilonerdx.basico;

import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;

import static org.opencv.core.Core.inRange;
import static org.opencv.imgproc.Imgproc.*;

public class FilterGeneratorExample {
    public static void main(String[] args) {
        // Carrega a imagem
        Mat image = Imgcodecs.imread("image.jpg");

        // Aplica o filtro de saturação
        cvtColor(image, image, COLOR_BGR2HSV);
        Scalar lower = new Scalar(0, 50, 50, 0);
        Scalar upper = new Scalar(10, 255, 255, 0);
        Mat mask = new Mat();
        inRange(image, lower, upper, mask);
        image.setTo(new Scalar(0, 0, 0), mask);
        cvtColor(image, image, COLOR_HSV2BGR);

        // Salva a imagem com o filtro aplicado
        Imgcodecs.imwrite("image_filtered.jpg", image);
    }

}
