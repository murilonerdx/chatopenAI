package com.murilonerdx.basico;

import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.MutableDataSet;

public class HTMLtoMarkdown {
    public static void main(String[] args) {
        String html = "<h1>Título</h1><p>Este é um parágrafo.</p>";

        MutableDataSet options = new MutableDataSet();

        // Cria um parser e um renderizador de HTML
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();

        // Transforma o HTML em um objeto do tipo Node
        Node document = parser.parse(html);

        // Transforma o objeto Node em Markdown
        String markdown = renderer.render(document);

        System.out.println(markdown);
        // Saída:
        // # Título
        // Este é um parágrafo.
    }
}
