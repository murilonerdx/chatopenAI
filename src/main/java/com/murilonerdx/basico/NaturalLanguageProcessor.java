package com.murilonerdx.basico;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

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

public class NaturalLanguageProcessor {
    private TokenizerME tokenizer;
    private SentenceDetectorME sentenceDetector;
    private POSTaggerME posTagger;
    private ChunkerME chunker;
    private LemmatizerME lemmatizer;
    private NameFinderME nameFinder;

    public NaturalLanguageProcessor() throws IOException {
        this.tokenizer = new TokenizerME(new TokenizerModel(Objects.requireNonNull(getClass().getResourceAsStream("/en-token.bin"))));
        this.sentenceDetector = new SentenceDetectorME(new SentenceModel(Objects.requireNonNull(getClass().getResourceAsStream("/en-sent.bin"))));
        this.posTagger = new POSTaggerME(new POSModel(Objects.requireNonNull(getClass().getResourceAsStream("/en-pos-maxent.bin"))));
        this.chunker = new ChunkerME(new ChunkerModel(Objects.requireNonNull(getClass().getResourceAsStream("/en-chunker.bin"))));
        this.lemmatizer = new LemmatizerME(new LemmatizerModel(Objects.requireNonNull(getClass().getResourceAsStream("/en-lemmatizer.dict"))));
        this.nameFinder = new NameFinderME(new TokenNameFinderModel(Objects.requireNonNull(getClass().getResourceAsStream("/en-ner-person.bin"))));
    }

    public void processText(String text) throws IOException {
        // Tokenize the text
        String[] tokens = tokenizer.tokenize(text);

        // Detect sentences
        String[] sentences = sentenceDetector.sentDetect(text);

        // Part-of-speech tagging
        String[] posTags = posTagger.tag(tokens);

        // Chunking
        String[] chunks = chunker.chunk(tokens, posTags);

        // Lemmatization
        DictionaryLemmatizer lemmatizer = new DictionaryLemmatizer(Objects.requireNonNull(NaturalLanguageProcessor.class.getResourceAsStream(
                "/opennlp/tools/lemmatizer/smalldictionarymulti.dict")));
        String[] lemmas = lemmatizer.lemmatize(tokens, posTags);

        // Named entity recognition
        List<Span> names = List.of(nameFinder.find(tokens));
    }
}

