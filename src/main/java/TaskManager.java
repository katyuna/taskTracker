public interface TaskManager {
    //Создание
    Task createTask(String name, String description);
    Epic createEpic(String name, String description);
    Subtask createSubtask(String name, String description, Epic epic);

    //Сохранение
    void addTaskToStorage(Task task);
    void addEpicToStorage(Epic epic);
    void addSubtaskToStorage(Subtask subtask);

    //Получение
    Task getTaskById(Integer id);
    Epic getEpicById(Integer id);
    Subtask getSubtaskById(Integer id);

    //Удаление
    void deleteById(Integer id);
    void deleteAll();

    //Обновление
    void replaceTaskInStorage(Task task);
    void replaceEpicInStorage(Epic epic);
    void replaceSubtaskInStorage(Subtask subtask);
}
