package sample.entity;

import java.sql.Date;
import java.util.Objects;

public class Task {
    private Long id;
    private Date date;
    private String name;
    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id.equals(task.id) &&
                Objects.equals(date, task.date) &&
                Objects.equals(name, task.name) &&
                Objects.equals(text, task.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, name, text);
    }

    @Override
    public String toString() {
        return "task{" +
                "id=" + id +
                ", date=" + date +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
