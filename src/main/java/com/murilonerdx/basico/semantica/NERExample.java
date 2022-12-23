package com.murilonerdx.basico.semantica;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;

import java.io.IOException;
import java.io.InputStream;

public class NERExample {
    public static void main(String[] args) throws IOException {
        // Carrega o modelo de NER treinado
        InputStream modelIn = NERExample.class.getResourceAsStream("/pt-ner-person.bin");
        TokenNameFinderModel model = new TokenNameFinderModel(modelIn);
        NameFinderME nameFinder = new NameFinderME(model);

        // Analisa o texto
        String[] tokens = {"João", "é", "um", "professor", "da", "Universidade", "Federal", "de", "Minas", "Gerais"};
        Span[] names = nameFinder.find(tokens);

        // Imprime os nomes encontrados
        for (Span name : names) {
            System.out.printf("Nome encontrado: %s (%d,%d)\n", name.toString(), name.getStart(), name.getEnd());
        }
        // Saída:
        // Nome encontrado: João (0,1)
    }
}
