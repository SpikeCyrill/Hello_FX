package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.DAO.TaskDao;
import sample.entity.Task;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class InputFormController implements Initializable {

    private Task task;

    @FXML private TextField date;
    @FXML private TextField name;
    @FXML private TextArea text;

    @FXML private Button save;
    @FXML private Button cancel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        save.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            if (this.task == null) this.task = new Task();
            task.setName(name.getText());
            task.setText(text.getText());
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
            task.setDate(LocalDateTime.parse(date.getText(), dateFormat));

            TaskDao taskDao = new TaskDao();
            if(task.getId() == null) {
                taskDao.insert(this.task);
            } else {
                taskDao.update(this.task);
            }

            Stage stage = (Stage) cancel.getScene().getWindow();
            stage.close();
        });

        cancel.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Stage stage = (Stage) cancel.getScene().getWindow();
            stage.close();
        });

    }

    public void setTask(Task task){
        this.task = task;

        if(task != null) {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
            date.setText(dateFormat.format(task.getDate()));
            name.setText(task.getText());
            text.setText(task.getText());
        }
    }

}
