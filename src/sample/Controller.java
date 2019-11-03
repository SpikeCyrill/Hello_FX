package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.DAO.TaskDao;
import sample.entity.Task;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private TableView<Task> table;
    @FXML private TableColumn<Task, String> id;
    @FXML private TableColumn<Task, String> date;
    @FXML private TableColumn<Task, String> name;
    @FXML private TableColumn<Task, String> text;

    @FXML private Button load;
    @FXML private Button add;
    @FXML private Button update;
    @FXML private Button delete;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setCellValueFactory(new PropertyValueFactory<Task, String>("id"));
        date.setCellValueFactory(new PropertyValueFactory<Task, String>("date"));
        name.setCellValueFactory(new PropertyValueFactory<Task, String>("name"));
        text.setCellValueFactory(new PropertyValueFactory<Task, String>("text"));

        load.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> table.getItems().setAll(parseUserList()));

        add.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            openInputForm(mouseEvent, null);
        });

        update.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Task task = table.getSelectionModel().getSelectedItem();
            openInputForm(mouseEvent, task);
        });

        delete.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Task task = table.getSelectionModel().getSelectedItem();

            if(task != null) {
                TaskDao taskDao = new TaskDao();
                taskDao.delete(task);
            }

        });
    }

    private List<Task> parseUserList(){
        TaskDao taskDao = new TaskDao();
        return taskDao.getAll();
    }

    private void openInputForm(MouseEvent mouseEvent, Task task) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "inputForm.fxml"
                    )
            );
            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            Scene scene = new Scene(loader.load(), 450, 450);
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(
                    ((Node)mouseEvent.getSource()).getScene().getWindow()
            );

            if(task != null) {
                InputFormController controller =
                        loader.getController();
                controller.setTask(task);
            }

            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }



}
