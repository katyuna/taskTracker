public class Subtask extends Task {
    Epic epic;
    public Subtask(Integer id) {
        super(id);
    }

    public Epic getEpic() {
        return epic;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }
}
