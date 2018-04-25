package GUI;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CustomTableView extends AnchorPane
{
    TableView<Student> table = new TableView<>();
    public CustomTableView(Stage stage)
    {
        table.prefWidthProperty().bind(stage.widthProperty());

        TableColumn mark = new TableColumn("Mark");
        mark.setCellValueFactory( new PropertyValueFactory<Student, Double>("mark") );

        TableColumn firstName = new TableColumn("First Name");
        firstName.setCellValueFactory( new PropertyValueFactory<Student, String>("firstName") );

        TableColumn lastName = new TableColumn("Last Name");
        lastName.setCellValueFactory( new PropertyValueFactory<Student, String>("lastName") );

        TableColumn age = new TableColumn("Age");
        age.setCellValueFactory( new PropertyValueFactory<Student, Integer>("age") );

        table.getColumns().addAll(mark, firstName, lastName, age);

        this.getChildren().add(table);
    }

    void addStudent(ObservableList<Student> st)
    {
        table.setItems(st);
    }

    public Student removeStudent()
    {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        Student st = table.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0)
            table.getItems().remove(selectedIndex);

        return st;
    }
}
