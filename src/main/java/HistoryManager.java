import java.util.List;

public interface HistoryManager {
    //Помечать задачи, как просмотренные
    void addTask(Task task);
    //Возвращать список просмотренных задач
    List<Task> getHistory();
}
