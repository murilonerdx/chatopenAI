//package com.murilonerdx.basico;
//
//import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
//import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
//import org.deeplearning4j.nn.conf.inputs.InputType;
//import org.deeplearning4j.nn.conf.layers.ConvolutionLayer;
//import org.deeplearning4j.nn.conf.layers.DenseLayer;
//import org.deeplearning4j.nn.conf.layers.OutputLayer;
//import org.deeplearning4j.nn.conf.layers.SubsamplingLayer;
//import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
//import org.deeplearning4j.nn.weights.WeightInit;
//import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
//import org.nd4j.linalg.activations.Activation;
//import org.nd4j.linalg.api.ndarray.INDArray;
//import org.nd4j.linalg.dataset.DataSet;
//import org.nd4j.linalg.factory.Nd4j;
//import org.nd4j.linalg.learning.config.Nesterovs;
//import org.nd4j.linalg.lossfunctions.LossFunctions;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//public class ImageGenerator {
//    private static final int SEED = 123;
//    private static final int IMAGE_WIDTH = 28;
//    private static final int IMAGE_HEIGHT = 28;
//    private static final int CHANNELS = 1;
//    private static final int NUM_CLASSES = 10;
//    private static final int NUM_EPOCHS = 20;
//    private static final int BATCH_SIZE = 128;
//
//    public static void main(String[] args) throws IOException {
//        // Cria um modelo de rede neural com uma camada de entrada, duas camadas de convolução, uma camada de
//        // subsampling e uma camada de saída.
//        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
//                .seed(SEED)
//                .weightInit(WeightInit.XAVIER)
//                .updater(new Nesterovs(0.01, 0.9))
//                .layer(new ConvolutionLayer.Builder()
//                        .nIn(CHANNELS)
//                        .nOut(20)
//                        .kernelSize(5, 5)
//                        .stride(1, 1)
//                        .padding(0, 0)
//                        .build())
//                .layer(new SubsamplingLayer.Builder()
//                        .poolingType(SubsamplingLayer.PoolingType.MAX)
//                        .kernelSize(2, 2)
//                        .stride(2, 2)
//                        .build())
//                .layer(new ConvolutionLayer.Builder()
//                        .nIn(20)
//                        .nOut(50)
//                        .kernelSize(5, 5)
//                        .stride(1, 1)
//                        .padding(0, 0)
//                        .build())
//                .layer(new SubsamplingLayer.Builder()
//                        .poolingType(SubsamplingLayer.PoolingType.MAX)
//                        .kernelSize(2, 2)
//                        .stride(2, 2)
//                        .build())
//                .layer(new DenseLayer.Builder()
//                        .nOut(500)
//                        .build())
//                .layer(new OutputLayer.Builder()
//                        .nOut(NUM_CLASSES)
//                        .lossFunction(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
//                        .activation(Activation.SOFTMAX)
//                        .build())
//                .setInputType(InputType.convolutionalFlat(IMAGE_HEIGHT, IMAGE_WIDTH, CHANNELS))
//                .build();
//
//        // Cria a rede neural com base na configuração criada anteriormente
//        MultiLayerNetwork model = new MultiLayerNetwork(conf);
//        model.init();
//        model.setListeners(new ScoreIterationListener(1));
//
//        // Treina a rede neural usando um conjunto de dados de treinamento
//        for (int i = 0; i < NUM_EPOCHS; i++) {
//            model.fit(trainData);
//        }
//
//        // Gera uma imagem usando a rede neural treinada
//        INDArray randomNoise = Nd4j.randn(1, 100);
//        INDArray output = model.generateAtOutputLayer(randomNoise, 10);
//        BufferedImage image = createImageFromArray(output.getRow(0).toIntVector());
//        ImageIO.write(image, "png", new File("generated_image.png"));
//    }
//
//    private static BufferedImage createImageFromArray(int[] pixels) {
//        BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
//        for (int y = 0; y < IMAGE_HEIGHT; y++) {
//            for (int x = 0; x < IMAGE_WIDTH; x++) {
//                int pixel = pixels[y * IMAGE_WIDTH + x];
//                Color color = new Color(pixel, pixel, pixel);
//                image.setRGB(x, y, color.getRGB());
//            }
//        }
//        return image;
//    }
//}
