package com.murilonerdx.basico.semantica;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.util.CoreMap;

import java.util.List;
import java.util.Properties;

public class NLPExample {
    public static void main(String[] args) {
        // Cria um pipeline do Stanford CoreNLP
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // Analisa o texto
        String text = "O gato está no telhado.";
        CoreDocument doc = new CoreDocument(text);
        pipeline.annotate(doc);

        // Imprime as informações de POS das palavras
        List<CoreLabel> labels = doc.tokens();
        for (CoreLabel label : labels) {
            String word = label.get(CoreAnnotations.TextAnnotation.class);
            String pos = label.get(CoreAnnotations.PartOfSpeechAnnotation.class);
            System.out.println(word + ": " + pos);
        }
        // Saída:
        // O: PRON
        // gato: NOUN
        // está: AUX
        // no: ADP
        // telhado: NOUN
        // .: PUNCT
    }
}
