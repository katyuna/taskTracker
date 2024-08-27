public interface HistoryManager {
    //Помечать задачи, как просмотренные
    void addTask(Task task);
    //Возвращать список просмотренных задач
    void getHistory();
}
