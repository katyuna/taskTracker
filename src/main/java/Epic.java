import java.util.ArrayList;
import java.util.Objects;

public class Epic extends Task {

    private ArrayList<Subtask> listSubtasksInEpic = new ArrayList<>();

    public Epic(Integer id) {
        super(id);
    }

    public Epic(String type, Integer id, String name, String description, String status, ArrayList<Subtask> listSubtasksInEpic) {
        super(type, id, name, description, status);
        this.listSubtasksInEpic = listSubtasksInEpic;

    }

    public ArrayList<Subtask> getListSubtasksInEpic() {
        return listSubtasksInEpic;
    }

    @Override
    public String toString() {
        return super.toString() + " Subtasks=" + listSubtasksInEpic;
    }

    // Переопределение метода equals и hashCode для сравнения объектов по полям
    @Override
    public boolean equals(Object object) {
        if (this == object) return true; //сравниваем ссылки
        if (object == null) return false; //проверяем ссылку на null
        if (this.getClass() != object.getClass()) return false; //сравниваем классы объектов
        if (!super.equals(object)) return false; //сравниваем поля с помощью родительского метода
        Epic epic = (Epic) object; //приведем object к типу Subtask, чтобы получить доступ к его полям
        return Objects.equals(listSubtasksInEpic, epic.listSubtasksInEpic);
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), listSubtasksInEpic);
    }
}
