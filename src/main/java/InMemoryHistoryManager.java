import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    List<Task> historyList;

    public InMemoryHistoryManager(List<Task> historyList) {
        this.historyList = historyList;
    }

    // Check List Size
    public boolean checkHistoryListSize(List<Task> list) {
        if (list.size() < 10) {
            return true;
        } else {
            return false;
        }
    }

    //Добавление просмотренной задачи в список
    @Override
    public void addTask(Task task) {
        if (checkHistoryListSize(historyList)) {
            historyList.add(task);
        } else {
            historyList.remove(0);
            historyList.add(task);
        }
    }

    //Возвращать список просмотренных задач
    @Override
    public List<Task> getHistory() {
        return historyList;
    }
}
