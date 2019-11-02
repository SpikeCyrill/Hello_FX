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
                task.setDate(resultSet.getDate("date"));
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

}
