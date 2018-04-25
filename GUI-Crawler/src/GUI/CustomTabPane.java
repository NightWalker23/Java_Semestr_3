package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomTabPane extends AnchorPane
{
    public final ObservableList<Student> studentList = FXCollections.observableArrayList();
    public static int mark2_0 = 0, mark3_0 = 0, mark3_5 = 0, mark4_0 = 0, mark4_5 = 0, mark5_0 = 0;

    private CustomListView logger;
    private CustomTableView table;
    private CustomBarChart bar;
    private TextField markField, firstNameField, lastNameField, ageField;
    private HBox hb;
    private VBox vbox;
    private Button addButton, removeButton;

    public CustomTabPane(Stage stage)
    {
        logger = new CustomListView(stage);
        table = new CustomTableView(stage);
        bar = new CustomBarChart();

        table.addStudent(studentList);
        //____________________Pola tekstowe i buttony____________________
        markField = new TextField();
        markField.setPromptText("Mark");
        markField.setMaxWidth(105);

        firstNameField = new TextField();
        firstNameField.setPromptText("First Name");
        firstNameField.setMaxWidth(105);

        lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");
        lastNameField.setMaxWidth(105);

        ageField = new TextField();
        ageField.setPromptText("Age");
        ageField.setMaxWidth(105);

        addButton = new Button("Add");
        addButton.setOnAction(e ->
        {
            addStudent(new Student()
            {{
                this.setMark(Double.parseDouble(markField.getText()));
                this.setFirstName(firstNameField.getText());
                this.setLastName(lastNameField.getText());
                this.setAge(Integer.parseInt(ageField.getText()));
            }});
        });

        removeButton = new Button("Delete");
        removeButton.setOnAction(e ->
        {
            removeStudent(table.removeStudent());
        });

        hb = new HBox();
        hb.getChildren().addAll(markField, firstNameField, lastNameField, ageField, addButton, removeButton);
        hb.setSpacing(3);
        hb.setPadding(new Insets(5,0,0,15));

        vbox = new VBox();
        vbox.setSpacing(5);
        vbox.getChildren().addAll(table, hb);
        //____________________Pola tekstowe i buttony____________________

        //____________________Zakładki____________________
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab students = new Tab();
        students.setText("Students");
        students.setContent(vbox);

        Tab logs = new Tab();
        logs.setText("Log");
        logs.setContent(logger);

        Tab histogram = new Tab();
        histogram.setText("Histogram");
        histogram.setContent(bar);
        //____________________Zakładki____________________

        tabPane.getTabs().addAll(students, logs, histogram);
        tabPane.prefWidthProperty().bind(stage.widthProperty());
        tabPane.prefHeightProperty().bind(stage.heightProperty());
        this.getChildren().add(tabPane);
    }

    public void addStudent(Student student)
    {
        logger.addStudent(student);
        studentList.add(student);

        markField.clear();
        firstNameField.clear();
        lastNameField.clear();
        ageField.clear();

        updateBar();
    }

    public void removeStudent(Student student)
    {
        logger.removeStudent(student);
        updateBar();
    }

    public void updateBar()
    {
        mark2_0 = mark3_0 = mark3_5 = mark4_0 = mark4_5 = mark5_0 = 0;

        for (Student el: studentList)
        {
            if (el.getMark() == 2.0) mark2_0++;
            else if (el.getMark() == 3.0) mark3_0++;
            else if (el.getMark() == 3.5) mark3_5++;
            else if (el.getMark() == 4.0) mark4_0++;
            else if (el.getMark() == 4.5) mark4_5++;
            else if (el.getMark() == 5.0) mark5_0++;
        }
        bar.uptadeData(mark2_0, mark3_0, mark3_5, mark4_0, mark4_5, mark5_0);
    }
}
