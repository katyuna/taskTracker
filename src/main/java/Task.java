import java.util.Objects;

public class Task {
    private final Integer id;
    private String name;
    private String description;
    private String status;
    private String type;

    //Конструктор

    public Task(String type, Integer id, String name, String description, String status) {
        this.type = type;
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }
    // Переопределение метода toString для строкового представления объекта
    @Override
    public String toString() {
        return "Type=" + type + ", status=" + status + " , id=" + id + ", name=" + name + ", description=" + description +".";
    }
    // Переопределение метода equals и hashCode для сравнения объектов по полям
    @Override
    public boolean equals(Object object) {
        if (this == object) return true; //сравниваем ссылки
        if (object == null) return false; //проверяем ссылку на null
        if (this.getClass() != object.getClass()) return false; //сравниваем классы объектов
        Task task = (Task) object;
        return Objects.equals(type, task.type) && //сравниваем все поля
                Objects.equals(id, task.id) &&
                Objects.equals(name, task.name) &&
                Objects.equals(description, task.description) &&
                Objects.equals(status, task.status);
    }
    @Override
    public int hashCode() {
        return Objects.hash(type, id, name, description, status);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
