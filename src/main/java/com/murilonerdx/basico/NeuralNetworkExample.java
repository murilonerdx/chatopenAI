//package com.murilonerdx.basico;
//
//import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
//import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
//import org.deeplearning4j.nn.conf.layers.DenseLayer;
//import org.deeplearning4j.nn.conf.layers.OutputLayer;
//import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
//import org.deeplearning4j.nn.weights.WeightInit;
//import org.nd4j.linalg.activations.Activation;
//import org.nd4j.linalg.learning.config.Adam;
//import org.nd4j.linalg.lossfunctions.LossFunctions;
//
//import java.util.List;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.File;
//
//public class NeuralNetworkExample {
//    public static void main(String[] args) {
//        // Define as configurações da RNA
//        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
//                .seed(123)
//                .updater(new Adam())
//                .l2(1e-4)
//                .list()
//                .layer(new DenseLayer.Builder()
//                        .nIn(784)
//                        .nOut(128)
//                        .weightInit(WeightInit.XAVIER)
//                        .activation(Activation.RELU)
//                        .build())
//                .layer(new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
//                        .nIn(128)
//                        .nOut(10)
//                        .weightInit(WeightInit.XAVIER)
//                        .activation(Activation.SOFTMAX)
//                        .build())
//                .build();
//
//        // Cria a RNA com as configurações definidas
//        MultiLayerNetwork model = new MultiLayerNetwork(conf);
//        model.init();
//
//        // Treina a RNA com os dados de treinamento
//        model.fit(trainingData);
//
//        // Faz previsões com a RNA treinada
//        INDArray output = model.output(testData);
//    }
//}
