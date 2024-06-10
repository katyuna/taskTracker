public class Subtask extends Task {

    Epic e;

    public Subtask(Integer id) {
        super(id);
    }

    public Epic getE() {
        return e;
    }

    public void setE(Epic e) {
        this.e = e;
    }
}
