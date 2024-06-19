public class Subtask extends Task {
    Epic epic;
    public Subtask(Integer id) {
        super(id);
    }

    public Subtask(String type, Integer id, String name, String description, String status) {
        super(type, id, name, description, status);
    }

    public Epic getEpic() {
        return epic;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }

    @Override
    public String toString() {
        return super.toString() + ", epic=" + epic;
    }
}
