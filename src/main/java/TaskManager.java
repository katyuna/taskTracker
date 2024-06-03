import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
            Epic epicValue = entry.getValue();
            if (epicId == epicKey){
                Subtask subtaskValue = subtaskHashMap.get(subtaskId);
                epicValue.listSubtasksInEpic.add(subtaskValue);
                subtaskValue.setMyEpic(epicValue);
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
            System.out.println("ID " + key + ": " + epic.getName() + " | Subtasks in this Epic: " + epic.listSubtasksInEpic);
        }
    }
    public void getSubtasks() {
        for (Map.Entry<Integer, Subtask> entry : subtaskHashMap.entrySet()) {
            Integer key = entry.getKey();
            Subtask subtask = entry.getValue();
            System.out.println("ID " + key + ": " + subtask.getName() + "  | This Subtask in Epic: " + subtask.myEpic);
        }
    }

    //Получение всех подзадач эпика
    public void getSubtasksByEpicId (Integer epicId){
        for (Map.Entry<Integer, Epic> entry : epicHashMap.entrySet()) {
            Integer epicKey = entry.getKey();
            Epic epicValue = entry.getValue();
            if (epicId == epicKey){
                System.out.println("Список подзадач для эпика (ID" + epicId + ")");
                for (int i =0; i<epicValue.listSubtasksInEpic.size(); i++){
                    System.out.println(epicValue.listSubtasksInEpic.get(i).getId() + ": " + epicValue.listSubtasksInEpic.get(i).getName());
                }
            }

        }
    }
    //Получение задачи по id
    public void getById (Integer id){
        for (Map.Entry<Integer, Task> entry : taskHashMap.entrySet()) {
            Integer taskKey = entry.getKey();
            Task taskValue = entry.getValue();
            if (id == taskKey) {
                System.out.println("Type: " + taskValue.getType() + ". Status: " + taskValue.getStatus() + ". ID " + taskKey + ". Name: " + taskValue.getName());
                System.out.println("Description: " + taskValue.getDescription());
            }
        }
            for (Map.Entry<Integer, Epic> entry : epicHashMap.entrySet()) {
                Integer epicKey = entry.getKey();
                Epic epicValue = entry.getValue();
                if (id == epicKey) {
                    System.out.println("Type: " + epicValue.getType() + ". Status: " + epicValue.getStatus() + ". ID " + epicKey + ". Name: " + epicValue.getName());
                    System.out.println("Description: " + epicValue.getDescription());
                    getSubtasksByEpicId(epicKey);
                }
            }
        for (Map.Entry<Integer, Subtask> entry : subtaskHashMap.entrySet()) {
            Integer subtaskKey = entry.getKey();
            Subtask subtaskValue = entry.getValue();
            if (id == subtaskKey) {
                System.out.println("Type: " + subtaskValue.getType() + ". Status: " + subtaskValue.getStatus() + ". ID " + subtaskKey + ". Name: " + subtaskValue.getName());
                System.out.println("Description: " + subtaskValue.getDescription());
                System.out.println("Located in Epic: " + subtaskValue.myEpic);
            }
        }
    }

    //Удаление всех задач
    public void deleteAll (){
        //удаление ссылок на объекты, если нет ссылок - сборщик мусора удалит объекты
        taskHashMap.clear();
        epicHashMap.clear();
        subtaskHashMap.clear();
    }
    //Удаление задачи по id
    public void deleteById (Integer id) {
        //использую итератор т.к. удаление элемента во время for вызывает ConcurrentModificationException
        Iterator<Map.Entry<Integer, Task>> taskIterator = taskHashMap.entrySet().iterator();
        while (taskIterator.hasNext()) {
            Map.Entry<Integer, Task> entry = taskIterator.next();
            if (entry.getKey().equals(id)) {
                taskIterator.remove();
            }
        }
        Iterator<Map.Entry<Integer, Epic>> epicIterator = epicHashMap.entrySet().iterator();
        while (epicIterator.hasNext()) {
            Map.Entry<Integer, Epic> entry = epicIterator.next();
            if (entry.getKey().equals(id)) {
                epicIterator.remove();
            }
        }
        Iterator<Map.Entry<Integer, Subtask>> subtaskIterator = subtaskHashMap.entrySet().iterator();
        while (subtaskIterator.hasNext()) {
            Map.Entry<Integer, Subtask> entry = subtaskIterator.next();
            if (entry.getKey().equals(id)) {
                subtaskIterator.remove();
            }
        }
     }
    //Обновление задач. Новая версия объекта и id передаются в виде параметра
    public void updateTask (Task task){

    }
    public void updateEpic (Epic epic){

    }
    public void updateSubtask (Subtask subtask){

    }

    //Управление статусами
}
