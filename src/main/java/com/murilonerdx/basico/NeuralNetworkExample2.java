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
//public class NeuralNetworkExample2 {
//    public static void main(String[] args) {
//        // Cria a configuração da rede neural
//        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
//                .seed(123)
//                .weightInit(WeightInit.XAVIER)
//                .updater(new Adam())
//                .list()
//                .layer(new DenseLayer.Builder()
//                        .nIn(28 * 28)
//                        .nOut(100)
//                        .activation(Activation.RELU)
//                        .build())
//                .layer(new DenseLayer.Builder()
//                        .nIn(100)
//                        .nOut(10)
//                        .activation(Activation.SOFTMAX)
//                        .build())
//                .layer(new OutputLayer.Builder()
//                        .nIn(10)
//                        .nOut(10)
//                        .lossFunction(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
//                        .build())
//                .build();
//
//        // Cria a rede neural
//        MultiLayerNetwork model = new MultiLayerNetwork(conf);
//        model.init();
//    }
//}
