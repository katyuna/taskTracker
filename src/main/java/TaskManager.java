import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TaskManager {
    private static Integer currentId = 0;

    //Хранение задач
    HashMap<Integer, Task> taskHashMap = new HashMap<>();
    HashMap<Integer, Epic> epicHashMap = new HashMap<>();
    HashMap<Integer, Subtask> subtaskHashMap = new HashMap<>();

    //Создание задач
    public void createTask (){
       Task task = new Task();
       task.setId(currentId++);
       task.setName();
       task.setDescription();
       task.setStatus("NEW");
       task.setType("Task");
        //Добавить задачу в хранилище
        taskHashMap.put(task.getId(), task);
    }
    public void createEpic (){
        Epic epic = new Epic();
        epic.setId(currentId++);
        epic.setName();
        epic.setDescription();
        epic.setStatus("NEW");
        epic.setType("Epic");
        epicHashMap.put(epic.getId(), epic);
   }
    public void createSubtask (){
        Subtask subtask = new Subtask();
        subtask.setId(currentId++);
        subtask.setName();
        subtask.setDescription();
        subtask.setStatus("NEW");
        subtask.setType("Subtask");
        subtaskHashMap.put(subtask.getId(), subtask);

    }

    //Добавить подзадачу в эпик
    public void addSubtaskToEpicByIds(Integer epicId, Integer subtaskId){
        for (Map.Entry<Integer, Epic> entry : epicHashMap.entrySet()) {
            Integer epicKey = entry.getKey();
            Epic epic = entry.getValue();
            if (epicKey == epicId){
                epic.listSubtasksInEpic.add(subtaskId);
                for (Map.Entry<Integer, Subtask> oneMoreEntry : subtaskHashMap.entrySet()){
                    Integer subtaskKey = oneMoreEntry.getKey();
                    Subtask subtask = oneMoreEntry.getValue();
                    if (subtaskKey == subtaskId){
                        subtask.setEpicId(subtaskId);
                    }
                }
            } else {
                System.out.println ("Эпика с таким Id не существует");
            }
        }
    }
    //Получение списка всех задач
    public void getTasks() {
        for (Map.Entry<Integer, Task> entry : taskHashMap.entrySet()) {
            Integer key = entry.getKey();
            Task task = entry.getValue();
            System.out.println("ID " + key + ": " + task.getName());
        }
    }
    public void getEpics() {
        for (Map.Entry<Integer, Epic> entry : epicHashMap.entrySet()) {
            Integer key = entry.getKey();
            Epic epic = entry.getValue();
            System.out.println("ID " + key + ": " + epic.getName() + " | Subtasks: " + epic.listSubtasksInEpic);
        }
    }
    public void getSubtasks() {
        for (Map.Entry<Integer, Subtask> entry : subtaskHashMap.entrySet()) {
            Integer key = entry.getKey();
            Subtask subtask = entry.getValue();
            System.out.println("ID " + key + ": " + subtask.getName() + "  | Epic: " + subtask.epicId);
        }
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
