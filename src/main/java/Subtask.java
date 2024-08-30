import java.util.Objects;

public class Subtask extends Task {
    Epic epic;

    public Subtask(String type, Integer id, String name, String description, Status status, Epic epic) {
        super(type, id, name, description, status);
        this.epic=epic;
    }

    public Epic getEpic() {
        return epic;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }

    // Переопределение метода equals и hashCode для сравнения объектов по полям
    @Override
    public boolean equals(Object object) {
        if (this == object) return true; //сравниваем ссылки
        if (object == null) return false; //проверяем ссылку на null
        if (this.getClass() != object.getClass()) return false; //сравниваем классы объектов
        if (!super.equals(object)) return false; //сравниваем поля с помощью родительского метода
        Subtask subtask = (Subtask) object; //приведем object к типу Subtask, чтобы получить доступ к его полям
        return Objects.equals(epic, subtask.epic);
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), epic);
    }
}
