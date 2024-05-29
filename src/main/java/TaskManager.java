import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {

    private static Integer currentTaskId = 0;
    private static Integer currentEpicId = 0;
    private static Integer currentSubtaskId = 0;

    //Хранение задач
    HashMap<Integer, Task> Tasks = new HashMap<>();
    HashMap<Integer, Epic> Epics = new HashMap<>();
    HashMap<Integer, Subtask> Subtasks = new HashMap<>();

    //Создание задач
    public void createTask (Task task){
       task = new Task();
       task.setId(currentTaskId++);
       task.setName();
       task.setDescription();
       task.setStatus("NEW");
        //Добавить задачу в хранилище
        Tasks.put(task.getId(), task);
    }
    public void createEpic (Epic epic){

    }
    public void createSubtask (Subtask subtask){

    }

    //Получение списка задач
    public ArrayList<String> getTasks (Task task){
        ArrayList<String> taskNameList = new ArrayList<>();
        return taskNameList;
    }
    public ArrayList<String> getEpics (Task task){
        ArrayList<String> epicsNameList = new ArrayList<>();
        return epicsNameList;
    }
    public ArrayList<String> getSubtasks (Task task){
        ArrayList<String> subtasksNameList = new ArrayList<>();
        return subtasksNameList;
    }

    //Удаление всех задач

    //Удаление задачи по id

    //Получение задачи по id

    //Обновление задач. Новая версия объекта и id передаются в виде параметра
    public void updateTask (Task task){

    }
    public void updateEpic (Epic epic){

    }
    public void updateSubtask (Subtask subtask){

    }
    //Получение всех подзадач эпика

    //Управление статусами
}
