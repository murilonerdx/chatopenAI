//package com.murilonerdx.basico;
//
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.jackson2.JacksonFactory;
//import com.google.api.services.customsearch.Customsearch;
//import com.google.api.services.customsearch.model.Result;
//import com.google.api.services.customsearch.model.Search;
//
//import javax.xml.transform.Result;
//import java.io.IOException;
//import java.util.List;
//
//public class InternetResearchAI{
//    private static final String API_KEY = "SUA_CHAVE_DA_API_DO_GOOGLE_CUSTOM_SEARCH";
//    private static final String SEARCH_ENGINE_ID = "SUA_ID_DO_MOTOR_DE_BUSCA";
//
//    private Customsearch customsearch;
//
//    public InternetResearchAI() {
//        // Cria um objeto Customsearch usando a chave da API e a ID do motor de busca
//        customsearch = new Customsearch.Builder(new NetHttpTransport(), new JacksonFactory(), null)
//                .setApplicationName("InternetResearchAI")
//                .setGoogleClientRequestInitializer(request -> {
//                    request.setKey(API_KEY);
//                    request.setCx(SEARCH_ENGINE_ID);
//                })
//                .build();
//    }
//
//    public List<Result> search(String query) throws IOException {
//        // Executa a pesquisa e retorna os resultados
//        Customsearch.Cse.List list = customsearch.cse().list(query);
//        Search search = list.execute();
//        return search.getItems();
//    }
//
//    public static void main(String[] args) throws IOException {
//        InternetResearchAI ai = new InternetResearchAI();
//
//        // Faz uma pesquisa por "gatos"
//        List<Result> results = ai.search("gatos");
//        for (Result result : results) {
//            System.out.println(result.getTitle());
//            System.out.println(result.getLink());
//            System.out.println();
//        }
//    }
//}