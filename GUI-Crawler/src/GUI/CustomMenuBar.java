package GUI;

import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;

public class CustomMenuBar extends MenuBar
{
    public CustomMenuBar()
    {
        Menu program = new Menu("Program");
            MenuItem close = new MenuItem("Close");
            close.setOnAction(actionEvent -> Platform.exit());
            close.setAccelerator(KeyCombination.keyCombination("Ctrl+C"));
        program.getItems().addAll(close);

        Label ab = new Label("About");
        ab.setOnMouseClicked(event ->
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacje o programie");
            alert.setHeaderText("Ten program to GUI dla crawlera.");
            alert.setContentText("Autor: Tomasz Tomala");
            alert.showAndWait();
        });
        Menu about = new Menu();
        about.setGraphic(ab);

        this.getMenus().addAll(program, about);
    }
}
