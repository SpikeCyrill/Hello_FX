package sample.DAO;

import sample.Connector;
import sample.entity.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDao {

    public List<Task> getAll() {

        ArrayList<Task> myCollection = new ArrayList();

        try (Connection conn = Connector.getConnection()) {

            PreparedStatement st = conn.prepareStatement("select * from task;");

            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getLong("id"));
                task.setDate(resultSet.getTimestamp("date").toLocalDateTime());
                task.setName(resultSet.getString("name"));
                task.setText(resultSet.getString("text"));
                myCollection.add(task);
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return myCollection;

    }

    public void update(Task task) {
        String sql = "UPDATE task SET date=?, name=?, text=? WHERE id=?";

        try (Connection conn = Connector.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setDate(1, java.sql.Date.valueOf(task.getDate().toLocalDate()));
            statement.setString(2, task.getName());
            statement.setString(3, task.getText());
            statement.setLong(4, task.getId());

            int rowsInserted = statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insert(Task task) {
        String sql = "INSERT INTO task (date, name, text) VALUES (?, ?, ?)";

        try (Connection conn = Connector.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setDate(1, java.sql.Date.valueOf(task.getDate().toLocalDate()));
            statement.setString(2, task.getName());
            statement.setString(3, task.getText());

            int rowsInserted = statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void delete(Task task) {
        String sql = "DELETE FROM task WHERE id=?";

        try (Connection conn = Connector.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, task.getId());

            int rowsInserted = statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
