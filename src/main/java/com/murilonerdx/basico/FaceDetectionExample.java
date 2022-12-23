package com.murilonerdx.basico;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

public class FaceDetectionExample {
    public static void main(String[] args) {
        // Carrega a biblioteca do OpenCV
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Cria o classificador de cascata para detectar rostos
        CascadeClassifier faceDetector = new CascadeClassifier("haarcascade_frontalface_default.xml");

        // Abre o vídeo
        VideoCapture capture = new VideoCapture(0);
        if (!capture.isOpened()) {
            System.out.println("Erro ao abrir o vídeo");
            return;
        }

        // Lê as frames do vídeo e detecta os rostos
        Mat frame = new Mat();
        MatOfRect faces = new MatOfRect();
        while (capture.read(frame)) {
            // Detecta os rostos na imagem
            faceDetector.detectMultiScale(frame, faces);

            // Desenha um retângulo em volta dos rostos detectados
            for (Rect face : faces.toArray()) {
                Imgproc.rectangle(frame, new Point(face.x, face.y), new Point(face.x + face.width, face.y + face.height), new Scalar(0, 255, 0));
            }

            // Exibe a imagem com os rostos detectados
            Imgcodecs.imwrite("frame.jpg", frame);
        }

        // Fecha o vídeo
        capture.release();
    }
}
