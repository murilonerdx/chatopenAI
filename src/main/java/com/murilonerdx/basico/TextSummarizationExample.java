//package com.murilonerdx.basico;
//
//import edu.stanford.nlp.pipeline.StanfordCoreNLP;
//import edu.stanford.nlp.naturalli.NaturalLogicAnnotations;
//import edu.stanford.nlp.ling.CoreAnnotations;
//import edu.stanford.nlp.util.CoreMap;
//
//import java.util.List;
//import java.util.Properties;
//
//public class TextSummarizationExample {
//    public static void main(String[] args) {
//        // Cria um pipeline do Stanford CoreNLP
//        Properties props = new Properties();
//        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,depparse,natlog,openie");
//        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
//
//        // Analisa o texto
//        String text = "João é um professor da Universidade Federal de Minas Gerais";
//        // Imprime as informações de OpenIE das sentenças
//        List<CoreMap> sentences = doc.sentences();
//        for (CoreMap sentence : sentences) {
//            // Obtém as informações de OpenIE da sentença
//            List<CoreMap> openieInfo = sentence.get(NaturalLogicAnnotations.RelationTriplesAnnotation.class);
//            for (CoreMap info : openieInfo) {
//                // Imprime o sujeito, o predicado e o objeto da relação
//                System.out.println(
//                        info.get(NaturalLogicAnnotations.SubjectAnnotation.class) + " " +
//                                info.get(NaturalLogicAnnotations.PredicateAnnotation.class) + " " +
//                                info.get(NaturalLogicAnnotations.ObjectAnnotation.class));
//            }
//        }
//        // Saída:
//        // João é um professor
//        // um professor da Universidade Federal de Minas Gerais
//    }
//}
