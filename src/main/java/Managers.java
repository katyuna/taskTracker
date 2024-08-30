import java.util.ArrayList;

public class Managers {
    public static TaskManager getDefault() {
        InMemoryTaskManager taskManager = new InMemoryTaskManager();
        return taskManager;
    }
    public static HistoryManager getDefaultHistory() {
        HistoryManager historyManager = new InMemoryHistoryManager(new ArrayList<>());
        return historyManager;
    }
}
