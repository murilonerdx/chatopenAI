//package com.murilonerdx.basico;
//
//import edu.stanford.nlp.pipeline.CoreDocument;
//import edu.stanford.nlp.pipeline.StanfordCoreNLP;
//import edu.stanford.nlp.ling.CoreAnnotations;
//import edu.stanford.nlp.ling.CoreLabel;
//import edu.stanford.nlp.util.CoreMap;
//import edu.stanford.nlp.semgraph.SemanticGraph;
//import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
//
//import java.util.List;
//import java.util.Properties;
//
//public class NLPExample2 {
//    public static void main(String[] args) {
//        // Cria um pipeline do Stanford CoreNLP
//        Properties props = new Properties();
//        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse");
//        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
//
//        // Analisa o texto
//        String text = "João é um professor da Universidade Federal de Minas Gerais";
//        CoreDocument doc = new CoreDocument(text);
//        pipeline.annotate(doc);
//        // Imprime as informações de NER das palavras
//        List<CoreLabel> labels = doc.tokens();
//        for (CoreLabel label : labels) {
//            String word = label.get(CoreAnnotations.TextAnnotation.class);
//            String ner = label.get(CoreAnnotations.NamedEntityTagAnnotation.class);
//            System.out.println(word + ": " + ner);
//        }
//        // Saída:
//        // João: PERSON
//        // é: O
//        // um: O
//        // professor: O
//        // da: O
//        // Universidade: ORGANIZATION
//        // Federal: ORGANIZATION
//        // de: O
//        // Minas: ORGANIZATION
//        // Gerais: ORGANIZATION
//
//        // Obtém o grafo semântico da primeira sentença
//        List<CoreMap> sentences = doc.sentences();
//        if (!sentences.isEmpty()) {
//            CoreMap sentence = sentences.get(0);
//            SemanticGraph graph = sentence.get(SemanticGraphCoreAnnotations.EnhancedDependenciesAnnotation.class);
//            System.out.println(graph.toString(SemanticGraph.OutputFormat.LIST));
//            // Saída:
//            // professor(O)(4)
//            // é(O)(2)
//            // um(O)(3)
//            // João(PERSON)(1)
//            // da(O)(7)
//            // de(O)(8)
//            // Gerais(ORGANIZATION)(10)
//            // Federal(ORGANIZATION)(6)
//            // Universidade(ORGANIZATION)(5)
//            // Minas(ORGANIZATION)(9)
//        }
//    }
//}
