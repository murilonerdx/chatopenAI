Para criar vídeos para o YouTube baseados em uma palavra-chave usando Java, você pode usar uma combinação de APIs do YouTube e bibliotecas de processamento de vídeo. Aqui está um exemplo de código que demonstra como fazer isso:

```java
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoSnippet;
import com.google.api.services.youtube.model.VideoStatus;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

public class YouTubeVideoCreator {
  private static final String APPLICATION_NAME = "YouTube Video Creator";
  private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
  private static final String TOKENS_DIRECTORY_PATH = "tokens";

  /**
   * Global instance of the
   /**
   * Creates an authorized Credential object.
   * @param HTTP_TRANSPORT The network HTTP Transport.
   * @return An authorized Credential object.
   * @throws IOException If the credentials.json file cannot be found.
   */
  private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
    // Load client secrets.
    InputStream in = YouTubeVideoCreator.class.getResourceAsStream("/credentials.json");
    GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

    // Build flow and trigger user authorization request.
    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
        .setDataStoreFactory(new FileDataStoreFactory(new File(TOKENS_DIRECTORY_PATH)))
        .setAccessType("offline")
        .build();
    LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
    return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
  }

  /**
   * Creates a video on YouTube using the YouTube API.
   * @param youtube The YouTube API client.
   * @param videoTitle The title of the video.
   * @param videoDescription The description of the video.
   * @param videoCategory The category of the video.
   * @param videoTags The tags for the video.
   * @param videoFilePath The path to the video file to upload.
   * @return The ID of the video that was created.
   * @throws IOException If there is a problem uploading the video.
   */
  private static String createVideo(YouTube youtube, String videoTitle, String videoDescription,
      String videoCategory, List<String> videoTags, String videoFilePath) throws IOException {
    // Create video metadata
    Video video = new Video();
    VideoSnippet snippet = new VideoSnippet();
    VideoStatus status = new VideoStatus();
    snippet.setTitle(videoTitle);
    snippet.setDescription(videoDescription);
    snippet.setCategoryId(videoCategory);
    snippet.setTags(videoTags);
    video.setSnippet(snippet);
    video.setStatus(status);

    // Set the video to be publicly visible
    status.setPrivacyStatus("public");

    // Create the video insert request
    YouTube.Videos.Insert videoInsert = youtube.videos().insert("snippet,status", video,
        getMediaContent(videoFilePath));

    // Execute the request and return the video ID
    Video returnedVideo = videoInsert.execute();
    return returnedVideo.getId();
  }

  /**
   * Gets the media content of the specified file.
   * @param filePath The path to the file.
   /**
   * Gets the media content of the specified file.
   * @param filePath The path to the file.
   * @return The media content of the file.
   * @throws IOException If the file cannot be read.
   */
  private static InputStreamContent getMediaContent(String filePath) throws IOException {
    File file = new File(filePath);
    InputStreamContent mediaContent = new InputStreamContent(
        "video/*", new BufferedInputStream(new FileInputStream(file)));
    mediaContent.setLength(file.length());
    return mediaContent;
  }

  public static void main(String[] args) throws GeneralSecurityException, IOException, InterruptedException {
    // Build a new authorized API client service
    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    YouTube youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
        .setApplicationName(APPLICATION_NAME)
        .build();

    // Create the video
    String videoTitle = "My Video";
    String videoDescription = "A video created by the YouTube Video Creator";
    String videoCategory = "22"; // category "People & Blogs"
    List<String> videoTags = Arrays.asList("youtube", "video", "creator");
    String videoFilePath = "path/to/video/file.mp4";
    String videoId = createVideo(youtube, videoTitle, videoDescription, videoCategory, videoTags, videoFilePath);
    System.out.println("Video ID: " + videoId);
  }
}
```

Este código usa a API do YouTube e a biblioteca de cliente Java para criar uma nova instância do cliente YouTube autorizado. Em seguida, ele cria um vídeo no YouTube com os parâmetros fornecidos, incluindo o título, a descrição, a categoria, as tags e o caminho do arquivo de vídeo. Quando o vídeo é criado, o ID do vídeo é exibido na saída.
