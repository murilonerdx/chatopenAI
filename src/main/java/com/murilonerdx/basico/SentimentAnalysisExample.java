//package com.murilonerdx.basico;
//
//import edu.stanford.nlp.pipeline.CoreDocument;
//import edu.stanford.nlp.pipeline.StanfordCoreNLP;
//import edu.stanford.nlp.ling.CoreAnnotations;
//import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
//import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
//import edu.stanford.nlp.trees.Tree;
//import edu.stanford.nlp.util.CoreMap;
//
//import java.util.List;
//import java.util.Properties;
//
//public class SentimentAnalysisExample {
//    public static void main(String[] args) {
//        // Cria um pipeline do Stanford CoreNLP
//        Properties props = new Properties();
//        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,sentiment");
//        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
//
//        // Analisa o texto
//        String text = "O filme é ótimo! Eu adoro a atuação dos atores e a trilha sonora.";
//        CoreDocument doc = new CoreDocument(text);
//        pipeline.annotate(doc);
//
//        // Imprime o sentimento de cada sentença
//        List<CoreMap> sentences = doc.sentences();
//        for (CoreMap sentence : sentences) {
//            Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
//            int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
//            String sentimentName = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
//            System.out.println(sentimentName + " (" + sentiment + ")");
//        }
//        // Saída:
//        // POSITIVE (2)
//    }
//}
