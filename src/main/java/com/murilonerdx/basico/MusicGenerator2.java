package com.murilonerdx.basico;

import java.util.Random;

import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.Updater;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Adam;
import org.nd4j.linalg.lossfunctions.LossFunctions;

public class MusicGenerator2 {
    // Define o tamanho do conjunto de dados de treinamento
    private static final int TAMANHO_DATASET = 10000;
    // Define o tamanho da sequência de entrada
    private static final int TAMANHO_SEQUENCIA = 100;

    public static void main(String[] args) {
        // Gera um conjunto de dados de treinamento aleatório com temas diferentes
        String[] temas = gerarTemas();
        INDArray sequencias = gerarSequencias(temas);
        INDArray saidas = gerarSaidas(temas);

        // Cria a rede neural de aprendizado profundo
        MultiLayerNetwork rede = criarRede();

        // Treina a rede com os dados de treinamento
        rede.fit(sequencias, saidas);

        // Gera uma música para o tema "amor"
        String tema = "amor";
        INDArray sequencia = gerarSequencia(tema);
        INDArray saida = rede.output(sequencia);
        String musica = gerarMusica(saida);
        System.out.println("Música gerada para o tema '" + tema + "': " + musica);
    }

    // Gera saídas a partir dos temas especificados
    private static INDArray gerarSaidas(String[] temas) {
        INDArray saidas = Nd4j.create(TAMANHO_DATASET, 1);
        for (int i = 0; i < TAMANHO_DATASET; i++) {
            saidas.putScalar(new int[] {i, 0}, temas[i].equals("amor") ? 0 : 1);
        }
        return saidas;
    }

    // Gera um conjunto de temas aleatórios
    private static String[] gerarTemas() {
        String[] temas = new String[TAMANHO_DATASET];
        Random random = new Random();
        for (int i = 0; i < TAMANHO_DATASET; i++) {
            temas[i] = random.nextInt(2) == 0 ? "amor" : "guerra";
        }
        return temas;
    }

    // Cria a rede neural de aprendizado profundo
    private static MultiLayerNetwork criarRede() {
        // Define a arquitetura da rede neural
        MultiLayerConfiguration config = new NeuralNetConfiguration.Builder()
                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
                .updater(Updater.ADAM)
                .l2(1e-4)
                .list()
                .layer(0, new DenseLayer.Builder().nIn(TAMANHO_SEQUENCIA).nOut(128).activation(Activation.RELU).weightInit(WeightInit.XAVIER).build())
                .layer(1, new DenseLayer.Builder().nIn(128).nOut(64).activation(Activation.RELU).weightInit(WeightInit.XAVIER).build())
                .layer(2, new OutputLayer.Builder().nIn(64).nOut(1).activation(Activation.SIGMOID).lossFunction(LossFunctions.LossFunction.XENT).build())
                .build();

        // Cria a rede neural com a arquitetura definida
        MultiLayerNetwork rede = new MultiLayerNetwork(config);
        rede.init();

        return rede;
    }

    // Gera uma sequência de entrada para o tema especificado
    private static INDArray gerarSequencia(String tema) {
        INDArray sequencia = Nd4j.create(1, TAMANHO_SEQUENCIA);
        for (int i = 0; i < TAMANHO_SEQUENCIA; i++) {
            sequencia.putScalar(new int[] {0, i}, tema.charAt(i % tema.length()));
        }
        return sequencia;
    }

    private static INDArray gerarSequencias(String[] temas) {
        INDArray sequencias = Nd4j.create(TAMANHO_DATASET, TAMANHO_SEQUENCIA);
        for (int i = 0; i < TAMANHO_DATASET; i++) {
            String tema = temas[i];
            for (int j = 0; j < TAMANHO_SEQUENCIA; j++) {
                sequencias.putScalar(new int[]{i, j}, tema.charAt(j % tema.length()));
            }
        }
        return sequencias;
    }

    // Gera uma música a partir da saída da rede neural
    private static String gerarMusica(INDArray saida) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < saida.length(); i++) {
            sb.append((char) saida.getInt(i));
        }
        return sb.toString();
    }
}

