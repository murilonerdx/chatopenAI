//package com.murilonerdx.basico;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Random;
//import java.util.stream.Collectors;
//
//import org.deeplearning4j.nn.conf.BackpropType;
//import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
//import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
//import org.deeplearning4j.nn.conf.layers.LSTM;
//import org.deeplearning4j.nn.conf.layers.RnnOutputLayer;
//import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
//import org.deeplearning4j.nn.weights.WeightInit;
//import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
//import org.deeplearning4j.util.ModelSerializer;
//import org.nd4j.linalg.activations.Activation;
//import org.nd4j.linalg.api.ndarray.INDArray;
//import org.nd4j.linalg.factory.Nd4j;
//import org.nd4j.linalg.learning.config.Adam;
//import org.nd4j.linalg.lossfunctions.LossFunctions;
//
//import static org.nd4j.linalg.lossfunctions.LossFunctions.LossFunction.MCXENT;
//
//// Classe para treinar a IA
//public class StoryGenerator {
//    private static final int HIDDEN_LAYER_WIDTH = 128;
//    private static final int HIDDEN_LAYER_COUNT = 2;
//    private static final int SEED_LENGTH = 20;
//    private static final int MINI_BATCH_SIZE = 64;
//    private static final int NUM_ITERATIONS = 1;
//    private static final int NUM_EPOCHS = 100;
//    private static final Random rng = new Random();
//
//    // Método para treinar a IA com os dados de treinamento
//    public static void train(List<String> trainingData) {
//        // Define as dimensões da entrada e saída da rede neural
//        int inputSize = SEED_LENGTH;
//        int outputSize = trainingData.size();
//
//        // Cria a configuração da rede neural
//        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
//                .seed(rng.nextLong())
//                .updater(new Adam())
//                .l2(1e-4)
//                .list()
//                .layer(0, new LSTM.Builder().nIn(inputSize).nOut(HIDDEN_LAYER_WIDTH)
//                        .weightInit(WeightInit.XAVIER).build())
//                .layer(1, new LSTM.Builder().nIn(HIDDEN_LAYER_WIDTH).nOut(HIDDEN_LAYER_WIDTH)
//                        .weightInit(WeightInit.XAVIER).build())
//                .layer(2, new RnnOutputLayer.Builder(MCXENT).activation(Activation.SOFTMAX)
//                        .nIn(HIDDEN_LAYER_WIDTH).nOut(outputSize).weightInit(WeightInit.XAVIER).build())
//                .backpropType(BackpropType.Standard)
//                .build();
//
//        // Cria a rede neural com a configuração definida
//        MultiLayerNetwork model = new MultiLayerNetwork(conf);
//        model.init();
//
//        // Treina a rede neural com os dados de treinamento
//        model.fit(getTrainingData(trainingData), NUM_EPOCHS, NUM_ITERATIONS, MINI_BATCH_SIZE);
//    }
//
//    // Método para gerar uma sugestão de frase com base nas palavras-chave fornecidas
//    public static String generateSuggestion(List<String> keywords, MultiLayerNetwork model) {
//        // Transforma a lista de palavras-chave em um vetor de caracteres
//        char[] keywordArray = keywords.stream().collect(Collectors.joining()).toCharArray();
//
//        // Cria o vetor de entrada para a rede neural com o tamanho da semente
//        INDArray input = Nd4j.zeros(1, SEED_LENGTH, trainingData.size());
//
//        // Popula o vetor de entrada com os caracteres das palavras-chave
//        for (int i = 0; i < keywordArray.length; i++) {
//            int idx = trainingData.indexOf(keywordArray[i]);
//            input.putScalar(new int[] {0, i, idx}, 1.0);
//        }
//
//        // Executa a rede neural para gerar a próxima previsão
//        INDArray output = model.rnnTimeStep(input);
//
//        // Escolhe a previsão com a maior probabilidade
//        int[] outputArray = output.argMax(2).getInt(0);
//
//        // Transforma a previsão em uma frase
//        StringBuilder sb = new StringBuilder();
//        for (int idx : outputArray) {
//            sb.append(trainingData.get(idx));
//        }
//
//        return sb.toString();
//    }
//
//    // Método para preparar os dados de treinamento para o treinamento da rede neural
//    public static INDArray getTrainingData(List<String> trainingData) {
//        // Calcula o número de exemplos de treinamento
//        int numExamples = trainingData.size() - SEED_LENGTH - 1;
//
//        // Cria os vetores de entrada e saída para o treinamento da rede neural
//        INDArray input = Nd4j.zeros(numExamples, SEED_LENGTH, trainingData.size());
//        INDArray output = Nd4j.zeros(numExamples, trainingData.size());
//
//        // Popula os vetores de entrada e saída com os dados de treinamento
//        for (int i = 0; i < numExamples; i++) {
//            for (int j = 0; j < SEED_LENGTH; j++) {
//                int idx = trainingData.indexOf(trainingData.get(i + j));
//                input.putScalar(new int[] {i, j, idx}, 1.0);
//            }
//            int idx = trainingData.indexOf(trainingData.get(i + SEED_LENGTH));
//            output.putScalar(new int[] {i, idx}, 1.0);
//        }
//
//        return input;
//    }
//
//    // Método para carregar os dados de treinamento a partir de um arquivo
//    public static List<String> loadTrainingData(String fileName) throws IOException {
//        // Carrega o conteúdo do arquivo em uma lista de linhas
//        List<String> lines = Files.readAllLines(Paths.get(fileName));
//
//        // Concatena as linhas em uma única string
//        String data = String.join("", lines);
//
//        // Transforma a string em uma lista de caracteres
//        return data.chars().mapToObj(c -> String.valueOf((char) c)).collect(Collectors.toList());
//    }
//
//    // Método para salvar a rede neural treinada em um arquivo
//    public static void saveModel(MultiLayerNetwork model, String fileName) throws IOException {
//        File locationToSave = new File(fileName);
//        ModelSerializer.writeModel(model, locationToSave, true);
//    }
//
//    // Método para carregar a rede neural treinada a partir de um arquivo
//    public static MultiLayerNetwork loadModel(String fileName) throws IOException {
//        return ModelSerializer.restoreMultiLayerNetwork(fileName);
//    }
//
//    // Método para testar a rede neural com exemplos de teste
//    public static void testModel(List<String> testData, MultiLayerNetwork model, List<String> trainingData) {
//        // Transforma os exemplos de teste em um vetor de caracteres
//        char[] testArray = testData.stream().collect(Collectors.joining()).toCharArray();
//
//        // Cria o vetor de entrada para a rede neural com o tamanho da semente
//        INDArray input = Nd4j.zeros(1, SEED_LENGTH, trainingData.size());
//
//        // Popula o vetor de entrada com os caracteres dos exemplos de teste
//        for (int i = 0; i < testArray.length; i++) {
//            int idx = trainingData.indexOf(testArray[i]);
//            input.putScalar(new int[] {0, i, idx}, 1.0);
//        }
//
//        // Executa a rede neural para gerar a próxima previsão
//        INDArray output = model.rnnTimeStep(input);
//
//        // Escolhe a previsão com a maior probabilidade
//        int[] outputArray = output.argMax(2).getInt(0);
//
//        // Transforma a previsão em uma frase
//        StringBuilder sb = new StringBuilder();
//        for (int idx : outputArray) {
//            sb.append(trainingData.get(idx));
//        }
//
//        // Imprime a frase gerada pela rede neural e a frase correta
//        System.out.println("Frase gerada pela rede neural: " + sb.toString());
//        System.out.println("Frase correta: " + String.join("", testData));
//    }
//
//    // Método para pré-processar os dados de treinamento
//    public static List<String> preprocessTrainingData(List<String> trainingData) {
//        // Remove caracteres indesejados dos dados de treinamento
//        List<String> processedData = trainingData.stream()
//                .map(s -> s.replaceAll("[^a-zA-Z0-9\\s]", ""))
//                .collect(Collectors.toList());
//
//        // Normaliza os dados de treinamento para caixa baixa
//        processedData = processedData.stream()
//                .map(String::toLowerCase)
//                .collect(Collectors.toList());
//
//        return processedData;
//    }
//
//    // Método para dividir os dados de treinamento em conjuntos de treinamento e validação
//    public static List<List<String>> splitTrainingData(List<String> trainingData, double splitRatio) {
//        // Calcula o tamanho dos conjuntos de treinamento e validação
//        int splitIdx = (int) (trainingData.size() * splitRatio);
//
//        // Divide os dados de treinamento em dois conjuntos
//        List<String> trainingSet = trainingData.subList(0, splitIdx);
//        List<String> validationSet = trainingData.subList(splitIdx, trainingData.size());
//
//        return Arrays.asList(trainingSet, validationSet);
//    }
//
//    // Método para treinar a rede neural com os dados de treinamento
//    public static void trainModel(List<String> trainingData, MultiLayerNetwork model) {
//        // Transforma os dados de treinamento em um vetor de caracteres
//        char[] trainingArray = String.join("", trainingData).toCharArray();
//
//        // Cria o vetor de entrada para a rede neural com o tamanho da semente
//        INDArray input = Nd4j.zeros(1, SEED_LENGTH, trainingData.size());
//
//        // Cria o vetor de saída para a rede neural com o tamanho da semente
//        INDArray labels = Nd4j.zeros(1, SEED_LENGTH, trainingData.size());
//
//        // Popula o vetor de entrada e o vetor de saída com os caracteres dos dados de treinamento
//        for (int i = 0; i < trainingArray.length - SEED_LENGTH; i++) {
//            for (int j = 0; j < SEED_LENGTH; j++) {
//                int idx = trainingData.indexOf(trainingArray[i + j]);
//                input.putScalar(new int[] {0, j, i}, idx);
//                labels.putScalar(new int[] {0, j, i + 1}, idx);
//            }
//        }
//
//        // Configura o otimizador e o erro
//        model.setListeners(new ScoreIterationListener(1));
//        model.setLossFunction(new MCXENT());
//
//        // Treina a rede neural com os dados de treinamento
//        for (int epoch = 0; epoch < NUM_EPOCHS; epoch++) {
//            model.fit(input, labels);
//        }
//    }
//}