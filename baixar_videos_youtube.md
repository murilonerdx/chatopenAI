Existem várias maneiras de baixar vídeos do YouTube usando Java. Uma opção é usar a biblioteca de terceiros YouTube Extraction, que fornece uma API fácil de usar para baixar vídeos do YouTube. Aqui está um exemplo de código em Java que usa a YouTube Extraction para baixar um vídeo do YouTube:

```java
import java.io.IOException;

import com.github.axet.vget.VGet;
import com.github.axet.vget.info.VGetParser;
import com.github.axet.vget.info.VideoFileInfo;
import com.github.axet.vget.info.VideoInfo;
import com.github.axet.vget.vhs.YouTubeMPGParser;

public class YouTubeDownloader {
  public static void main(String[] args) {
    // Define a URL do vídeo que deseja baixar
    String url = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";

    // Cria um parser para a URL do vídeo
    VGetParser user = null;
    try {
      user = new YouTubeMPGParser(url);
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }

    // Obtém as informações sobre o vídeo
    VideoInfo videoinfo = user.info(user.getVideo());

    // Cria o objeto VGet
    VGet v = new VGet(videoinfo, new File("."));

    // Inicia o download do vídeo
    v.download();
  }
}
```

Este código cria um parser para a URL do vídeo, obtém as informações sobre o vídeo e cria um objeto VGet. Em seguida, chama o método download para iniciar o download do vídeo. O vídeo será salvo na pasta atual com o nome padrão.

Observe que, para usar a YouTube Extraction, você precisará adicionar a seguinte dependência ao seu projeto:

```yml
<dependency>
  <groupId>com.github.axet</groupId>
  <artifactId>vget</artifactId>
  <version>2.5</version>
</dependency>
```

Outras opções para baixar vídeos do YouTube usando Java incluem o uso de bibliotecas como o jYouTube ou o YouTube-DL. No entanto, essas bibliotecas podem ser mais complicadas de usar e podem requerer a configuração de dependências adicionais.
