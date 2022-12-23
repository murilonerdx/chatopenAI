package com.murilonerdx.basico;

import java.io.IOException;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WikipediaLearner {
    private static final String WIKIPEDIA_URL = "https://en.wikipedia.org";

    public void learn(String topic) throws IOException {
        String url = WIKIPEDIA_URL + "/wiki/" + topic;
        Document doc = Jsoup.connect(url).get();
        Elements paragraphs = doc.select("p");
        for (Element paragraph : paragraphs) {
            String text = paragraph.text();
            // Process the text to extract important information and learn from it
        }
    }
}
