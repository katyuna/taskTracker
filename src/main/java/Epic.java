import java.util.ArrayList;

public class Epic extends Task {

    private ArrayList<Subtask> listSubtasksInEpic = new ArrayList<>();

    public Epic(Integer id) {
        super(id);
    }

    public ArrayList<Subtask> getListSubtasksInEpic() {
        return listSubtasksInEpic;
    }
}
