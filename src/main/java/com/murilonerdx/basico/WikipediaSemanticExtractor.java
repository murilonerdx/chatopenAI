package com.murilonerdx.basico;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.lemmatizer.DictionaryLemmatizer;
import opennlp.tools.lemmatizer.LemmatizerME;
import opennlp.tools.lemmatizer.LemmatizerModel;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

public class WikipediaSemanticExtractor {
    private static final String WIKIPEDIA_URL = "https://en.wikipedia.org";
    private TokenizerME tokenizer;
    private SentenceDetectorME sentenceDetector;
    private POSTaggerME posTagger;
    private ChunkerME chunker;
    private LemmatizerME lemmatizer;
    private NameFinderME nameFinder;

    public WikipediaSemanticExtractor() throws IOException {
        this.tokenizer = new TokenizerME(new TokenizerModel(Objects.requireNonNull(getClass().getResourceAsStream("/en-token.bin"))));
        this.sentenceDetector = new SentenceDetectorME(new SentenceModel(Objects.requireNonNull(getClass().getResourceAsStream("/en-sent.bin"))));
        this.posTagger = new POSTaggerME(new POSModel(Objects.requireNonNull(getClass().getResourceAsStream("/en-pos-maxent.bin"))));
        this.chunker = new ChunkerME(new ChunkerModel(Objects.requireNonNull(getClass().getResourceAsStream("/en-chunker.bin"))));
        this.nameFinder = new NameFinderME(new TokenNameFinderModel(Objects.requireNonNull(getClass().getResourceAsStream("/en-ner-person.bin"))));
        this.lemmatizer = new LemmatizerME(new LemmatizerModel(Objects.
                requireNonNull(getClass().getResourceAsStream("/en-lemmatizer.bin"))));
    }

    public void extractSemantics(String topic) throws IOException {
        String[] lemmas;
        List<Span> names;

        String url = WIKIPEDIA_URL + "/wiki/" + topic;
        Document doc = Jsoup.connect(url).get();
        Elements paragraphs = doc.select("p");
        for (Element paragraph : paragraphs) {
            String text = paragraph.text();
            // Tokenize the text
            String[] tokens = tokenizer.tokenize(text);
            // Detect sentences
            String[] sentences = sentenceDetector.sentDetect(text);

            // Part-of-speech tagging
            String[] posTags = posTagger.tag(tokens);

            // Chunking
            String[] chunks = chunker.chunk(tokens, posTags);

            lemmas = lemmatizer.lemmatize(tokens, posTags);

            // Named entity recognition
            names = List.of(nameFinder.find(tokens));

            logPrin(List.of(sentences));
        }


    }

    public void logPrin(List<String> t){
        t.forEach(System.out::println);
    }

    public static void main(String[] args){
        WikipediaSemanticExtractor wse = null;
        try {
            wse = new WikipediaSemanticExtractor();
            wse.extractSemantics("Time");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
