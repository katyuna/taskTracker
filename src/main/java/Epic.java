import java.util.ArrayList;

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
}
