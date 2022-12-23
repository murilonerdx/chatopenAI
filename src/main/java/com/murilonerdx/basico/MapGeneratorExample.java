//package com.murilonerdx.basico;
//
//import com.apple.eawt.Application;
//import javafx.application.Application;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Circle;
//import javafx.scene.shape.Line;
//import javafx.stage.Stage;
//
//public class MapGeneratorExample extends Application {
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage stage) {
//        // Cria o grupo de elementos do mapa
//        Group group = new Group();
//
//        // Adiciona as cidades ao mapa
//        Circle city1 = new Circle(50, 50, 10, Color.BLUE);
//        Circle city2 = new Circle(100, 100, 10, Color.BLUE);
//        Circle city3 = new Circle(150, 50, 10, Color.BLUE);
//        group.getChildren().addAll(city1, city2, city3);
//
//        // Adiciona as estradas ao mapa
//        Line road1 = new Line(50, 50, 100, 100);
//        Line road2 = new Line(100, 100, 150, 50);
//        group.getChildren().addAll(road1, road2);
//
//        // Cria a cena com o mapa
//        Scene scene = new Scene(group, 200, 150);
//
//        // Exibe a cena
//        stage.setScene(scene);
//        stage.show();
//    }
//}
