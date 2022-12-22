Aqui está um exemplo simples de código em Java que implementa um classificador de flores usando o Weka, uma biblioteca de aprendizado de máquina popular para Java:

```java
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class FlowerClassifier {
  public static void main(String[] args) throws Exception {
    // Carrega o conjunto de dados de treinamento
    DataSource source = new DataSource("flowers.arff");
    Instances data = source.getDataSet();

    // Define o índice da classe (última coluna do conjunto de dados)
    data.setClassIndex(data.numAttributes() - 1);

    // Cria o classificador J48 (árvore de decisão)
    J48 tree = new J48();

    // Treina o classificador com o conjunto de dados
    tree.buildClassifier(data);

    // Cria uma avaliação cruzada com 10 folds
    Evaluation eval = new Evaluation(data);
    eval.crossValidateModel(tree, data, 10, new Random(1));

    // Exibe os resultados da avaliação
    System.out.println(eval.toSummaryString());
    System.out.println(eval.toClassDetailsString());
    System.out.println(eval.toMatrixString());
  }
}
```

Este código carrega um conjunto de dados de treinamento de flores a partir de um arquivo ARFF, cria um classificador J48 (árvore de decisão) e o treina com o conjunto de dados. Em seguida, ele realiza uma avaliação cruzada com 10 folds e exibe os resultados da avaliação.

Lembre-se de que você precisará adicionar a biblioteca do Weka à sua build path para poder usar este código. Além disso, você também pode querer alterar o caminho do arquivo de treinamento e os parâmetros do classificador para atender às suas necessidades específicas.
