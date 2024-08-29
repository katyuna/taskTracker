import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    List<Task> historyList;

    //Помечать задачи, как просмотренные
    @Override
    public void addTask(Task task) {
        historyList.add(task);
    }

    //Возвращать список просмотренных задач
    @Override
    public void getHistory() {
        System.out.println(historyList);
    }
}
