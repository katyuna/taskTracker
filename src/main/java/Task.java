import java.util.Objects;

public class Task {
    private final Integer id;
    private String name;
    private String description;
    private String status;
    private String type;

    //Конструктор
    public Task(Integer id) {
        this.id = id;
    }

    public Task(String type, Integer id, String name, String description, String status) {
        this.type = type;
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Type=" + type + ", status=" + status + " , id=" + id + ", name=" + name + ", description=" + description +".";
    }

    //РАЗОБРАТЬСЯ ПОЧЕМУ ЭТИ МЕТОДЫ ТОЖЕ НАДО ПЕРЕОПРЕДЕЛЯТЬ
//    @Override
//    public boolean equals(Object object) {
//        if (this == object) return true;
//        if (object == null || getClass() != object.getClass()) return false;
//        Task task = (Task) object;
//        return Objects.equals(id, task.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }








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
