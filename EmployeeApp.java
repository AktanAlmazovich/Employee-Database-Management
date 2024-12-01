import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class EmployeeApp extends Application {
    private TableView<Employee> employeeTable = new TableView<>();
    private ObservableList<Employee> employees = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Employee Management System");

        // Table columns
        TableColumn<Employee, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getName()));

        TableColumn<Employee, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getClass().getSimpleName()));

        TableColumn<Employee, String> salaryColumn = new TableColumn<>("Salary");
        salaryColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(String.format("%.2f", data.getValue().calculateSalary())));

        employeeTable.getColumns().addAll(nameColumn, typeColumn, salaryColumn);
        employeeTable.setItems(employees);

        // Input fields
        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        ComboBox<String> typeField = new ComboBox<>();
        typeField.setItems(FXCollections.observableArrayList("FullTimeEmployee", "PartTimeEmployee", "Contractor"));
        typeField.setPromptText("Type");

        TextField salaryField = new TextField();
        salaryField.setPromptText("Salary / Hourly Rate");

        TextField hoursField = new TextField();
        hoursField.setPromptText("Hours Worked / Max Hours");

        Button addButton = new Button("Add Employee");
        addButton.setOnAction(e -> {
            String name = nameField.getText();
            String type = typeField.getValue();
            try {
                if (name.isEmpty() || type == null) throw new Exception("Invalid input");

                if (type.equals("FullTimeEmployee")) {
                    double annualSalary = Double.parseDouble(salaryField.getText());
                    employees.add(new FullTimeEmployee(name, annualSalary));
                } else if (type.equals("PartTimeEmployee")) {
                    double hourlyWage = Double.parseDouble(salaryField.getText());
                    int hoursWorked = Integer.parseInt(hoursField.getText());
                    employees.add(new PartTimeEmployee(name, hourlyWage, hoursWorked));
                } else if (type.equals("Contractor")) {
                    double hourlyRate = Double.parseDouble(salaryField.getText());
                    int maxHours = Integer.parseInt(hoursField.getText());
                    employees.add(new Contractor(name, hourlyRate, maxHours));
                }
                nameField.clear();
                salaryField.clear();
                hoursField.clear();
                typeField.setValue(null);
            } catch (Exception ex) {
                showAlert("Invalid Input", "Please check your input and try again.");
            }
        });

        Button removeButton = new Button("Remove Selected");
        removeButton.setOnAction(e -> {
            Employee selected = employeeTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                employees.remove(selected);
            }
        });

        // Layout
        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.setPadding(new Insets(10));
        inputGrid.add(new Label("Name:"), 0, 0);
        inputGrid.add(nameField, 1, 0);
        inputGrid.add(new Label("Type:"), 0, 1);
        inputGrid.add(typeField, 1, 1);
        inputGrid.add(new Label("Salary/Rate:"), 0, 2);
        inputGrid.add(salaryField, 1, 2);
        inputGrid.add(new Label("Hours (if applicable):"), 0, 3);
        inputGrid.add(hoursField, 1, 3);
        inputGrid.add(addButton, 0, 4);
        inputGrid.add(removeButton, 1, 4);

        VBox layout = new VBox(10, employeeTable, inputGrid);
        layout.setPadding(new Insets(10));

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
