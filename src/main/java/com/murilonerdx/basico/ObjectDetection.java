package com.murilonerdx.basico;

import java.awt.image.BufferedImage;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class ObjectDetection {
    public static void main(String[] args) {
        // Carregar a biblioteca do OpenCV
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Carregar a imagem
        Mat image = Imgcodecs.imread("image.jpg");

        // Carregar o classificador de cascata Haar
        CascadeClassifier classifier = new CascadeClassifier("haarcascade_frontalface_default.xml");

        // Detectar objetos na imagem
        MatOfRect objects = new MatOfRect();
        classifier.detectMultiScale(image, objects);

        // Desenhar caixas em volta dos objetos detectados
        for (Rect rect : objects.toArray()) {
            Imgproc.rectangle(image, rect.tl(), rect.br(), new Scalar(0, 255, 0), 3);
        }

        // Salvar a imagem com as caixas desenhadas
        Imgcodecs.imwrite("image_with_boxes.jpg", image);
    }
}
