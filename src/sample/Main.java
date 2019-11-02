package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.DAO.TaskDao;
import sample.entity.Task;

import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        Button refreshBtn = (Button) root.getScene().lookup("#load");
        TableView<Task> tableView = (TableView) root.getScene().lookup("#table");

        TableColumn idColumn = new TableColumn("id");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(
                new PropertyValueFactory<Task, Long>("id"));

        TableColumn dateColumn = new TableColumn("date");
        dateColumn.setMinWidth(100);
        dateColumn.setCellValueFactory(
                new PropertyValueFactory<Task, String>("date"));

        TableColumn nameColumn = new TableColumn("name");
        nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<Task, String>("name"));

        TableColumn textColumn = new TableColumn("text");
        textColumn.setMinWidth(150);
        textColumn.setCellValueFactory(
                new PropertyValueFactory<Task, String>("text"));


        tableView.getColumns().add(idColumn);
        tableView.getColumns().add(dateColumn);
        tableView.getColumns().add(nameColumn);
        tableView.getColumns().add(textColumn);


        if(refreshBtn != null) {
            refreshBtn.setOnMouseClicked(mouseEvent -> {
                TaskDao taskDao = new TaskDao();
                List<Task> myTask =  taskDao.getAll();
                tableView.getItems().setAll(myTask);
            });
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
