//package com.murilonerdx.basico;
//import org.datavec.api.records.reader.RecordReader;
//import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
//import org.datavec.api.split.FileSplit;
//import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
//import org.deeplearning4j.eval.Evaluation;
//import org.deeplearning4j.nn.api.OptimizationAlgorithm;
//import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
//import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
//import org.deeplearning4j.nn.conf.layers.DenseLayer;
//import org.deeplearning4j.nn.conf.layers.OutputLayer;
//import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
//import org.deeplearning4j.nn.weights.WeightInit;
//import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
//import org.deeplearning4j.util.ModelSerializer;
//import org.nd4j.linalg.activations.Activation;
//import org.nd4j.linalg.api.ndarray.INDArray;
//import org.nd4j.linalg.dataset.DataSet;
//import org.nd4j.linalg.dataset.SplitTestAndTrain;
//import org.nd4j.linalg.factory.Nd4j;
//import org.nd4j.linalg.learning.config.Nesterovs;
//import org.nd4j.linalg.lossfunctions.LossFunctions;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.Arrays;
//
//public class WikiPodia {
//
//    public void train() throws IOException {
//        // Define o tamanho do batch e o número de épocas de treinamento
//        int batchSize = 50;
//        int nEpochs = 10;
//
//// Carrega os dados do arquivo CSV e os divide em conjuntos de treinamento e teste
//        RecordReader rr = new CSVRecordReader();
//        rr.initialize(new FileSplit(new File("wikipedia-data.csv")));
//        RecordReaderDataSetIterator iterator = new RecordReaderDataSetIterator(rr, batchSize, 0, 2);
//        DataSet allData = iterator.next();
//        allData.shuffle();
//        SplitTestAndTrain testAndTrain = allData.splitTestAndTrain(0.65);
//        DataSet trainingData = testAndTrain.getTrain();
//        DataSet testData = testAndTrain.getTest();
//
//// Configura a rede neural
//        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
//                .seed(123)
//                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
//                .updater(new Nesterovs(0.006, 0.9))
//                .l2(1e-4)
//                .list()
//                .layer(new DenseLayer.Builder()
//                        .nIn(2)
//                        .nOut(4)
//                        .activation(Activation.TANH)
//                        .weightInit(WeightInit.XAVIER)
//                        .build())
//                .layer(new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
//                        .nIn(4)
//                        .nOut(2)
//                        .activation(Activation.SOFTMAX)
//                        .weightInit(WeightInit.XAVIER)
//                        .build())
//                .pretrain(false)
//                .backprop(true)
//                .build();
//
//// Cria a rede neural
//        MultiLayerNetwork model = new MultiLayerNetwork(conf);
//        model.init();
//        model.setListeners(new ScoreIterationListener(10));
//
//// Treina a rede neural com os dados de treinamento
//        for (int i = 0; i < nEpochs; i++) {
//            model.fit(trainingData);
//        }
//
//// Avalia a precisão da rede neural com os dados de teste
//        Evaluation eval = new Evaluation(2);
//        INDArray output = model.output(testData.getFeatureMatrix());
//        eval.eval(testData.getLabels(), output);
//        System.out.println(eval.stats());
//
//        // Cria um conjunto de dados de entrada para fazer a previsão
//        INDArray input = Nd4j.create(new double[][] { {0.1, 0.3}, {0.4, 0.6}, {0.7, 0.9} });
//
//// Faz a previsão com a rede neural
//        INDArray output2 = model.output(input);
//
//// Exibe os resultados da previsão
//        System.out.println(output);
//
//        // Faz a previsão com a rede neural
//        int[] predictions = model.predict(input);
//
//// Exibe os resultados da previsão
//        System.out.println(Arrays.toString(predictions));
//
//        // Salva a rede neural treinada em um arquivo
//        ModelSerializer.writeModel(model, new File("wikipedia-model.zip"), true);
//
//// Carrega a rede neural treinada a partir do arquivo
//        MultiLayerNetwork model2 = ModelSerializer.restoreMultiLayerNetwork(new File("wikipedia-model.zip"));
//    }
//
//}
