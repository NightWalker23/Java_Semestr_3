package GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(550, 500);

        Scene scene = new Scene(gridPane);
        primaryStage.setTitle("GUI");
        primaryStage.setScene(scene);
        primaryStage.show();

        CustomMenuBar menuBar = new CustomMenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        ((GridPane) scene.getRoot()).getChildren().addAll(menuBar);

        CustomTabPane tab = new CustomTabPane(primaryStage);
        gridPane.add(tab, 0, 1);
    }

    public static void main( String[] args )
    {
        launch(args);
    }
}
