import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class TaskManager {
    private static Integer currentId = 0;

    //Хранение задач
    HashMap<Integer, Task> taskHashMap = new HashMap<>();
    HashMap<Integer, Epic> epicHashMap = new HashMap<>();
    HashMap<Integer, Subtask> subtaskHashMap = new HashMap<>();

    public void addTaskToStorage (Task task){
        taskHashMap.put(task.getId(), task);
    }
    public  void addEpicToStorage(Epic epic){
        epicHashMap.put(epic.getId(), epic);
    }
    public void addSubtaskToStorage(Subtask subtask){
        subtaskHashMap.put(subtask.getId(), subtask);
    }

    //Создание задач
    public Task createTask (){
       Task task = new Task(currentId++);
       //task.setId(currentId++);
       task.setName();
       task.setDescription();
       task.setStatus("NEW");
       task.setType("Task");
       return task;
    }
    public Epic createEpic (){
        Epic epic = new Epic(currentId++);
        //epic.setId(currentId++);
        epic.setName();
        epic.setDescription();
        epic.setStatus("NEW");
        epic.setType("Epic");
        return epic;
   }
    public Subtask createSubtask (){
        Subtask subtask = new Subtask(currentId++);
        //subtask.setId(currentId++);
        subtask.setName();
        subtask.setDescription();
        subtask.setStatus("NEW");
        subtask.setType("Subtask");
        return subtask;
    }

    //Добавить подзадачу в эпик
    public void addSubtaskToEpicByIds(Integer epicId, Integer subtaskId){
        for (Map.Entry<Integer, Epic> entry : epicHashMap.entrySet()) {
            Integer epicKey = entry.getKey();
            Epic epicValue = entry.getValue();
            if (epicId == epicKey){
                Subtask subtaskValue = subtaskHashMap.get(subtaskId);
                epicValue.listSubtasksInEpic.add(subtaskValue);
                subtaskValue.setE(epicValue);
            }
        }
    }
    //Получение списка всех задач
    public  void getAllIssues (){
        for (Map.Entry<Integer, Task> entry : taskHashMap.entrySet()) {
            Integer key = entry.getKey();
            Task task = entry.getValue();
            System.out.println(task.getType() + " " + task.getStatus() + " ID " + key + ": " + task.getName() + ". Description: " + task.getDescription());
        }
        for (Map.Entry<Integer, Epic> entry : epicHashMap.entrySet()) {
            Integer key = entry.getKey();
            Epic epic = entry.getValue();
            System.out.println(epic.getType() + " " + epic.getStatus() +  " ID " + key + ": " + epic.getName() + " -> Subtasks in this Epic: " + epic.listSubtasksInEpic + ". Description: " + epic.getDescription());
        }
        for (Map.Entry<Integer, Subtask> entry : subtaskHashMap.entrySet()) {
            Integer key = entry.getKey();
            Subtask subtask = entry.getValue();
            System.out.println(subtask.getType()+ " " + subtask.getStatus() + " ID " + key + ": " + subtask.getName() + "  -> This Subtask in Epic: " + subtask.e + ". Description: " + subtask.getDescription());
        }
    }

    //Получение отдельно каждого типа задач
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
            System.out.println("ID " + key + ": " + subtask.getName() + "  | This Subtask in Epic: " + subtask.e);
        }
    }

    //Получение всех подзадач эпика
    public void getSubtasksByEpicId (Integer epicId){
        for (Map.Entry<Integer, Epic> entry : epicHashMap.entrySet()) {
            Integer epicKey = entry.getKey();
            Epic epicValue = entry.getValue();
            if (epicId == epicKey){
                System.out.println("Список подзадач для эпика c ID " + epicId + ": ");
                for (int i =0; i<epicValue.listSubtasksInEpic.size(); i++){
                    System.out.println(epicValue.listSubtasksInEpic.get(i).getType() + " " + epicValue.listSubtasksInEpic.get(i).getStatus() +" " + epicValue.listSubtasksInEpic.get(i).getId() + ": " + epicValue.listSubtasksInEpic.get(i).getName() + ". Description: " + epicValue.listSubtasksInEpic.get(i).getDescription());
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
                return;
            }
        }
            for (Map.Entry<Integer, Epic> entry : epicHashMap.entrySet()) {
                Integer epicKey = entry.getKey();
                Epic epicValue = entry.getValue();
                if (id == epicKey) {
                    System.out.println("Type: " + epicValue.getType() + ". Status: " + epicValue.getStatus() + ". ID " + epicKey + ". Name: " + epicValue.getName());
                    System.out.println("Description: " + epicValue.getDescription());
                    getSubtasksByEpicId(epicKey);
                    return;
                }
            }
        for (Map.Entry<Integer, Subtask> entry : subtaskHashMap.entrySet()) {
            Integer subtaskKey = entry.getKey();
            Subtask subtaskValue = entry.getValue();
            if (id == subtaskKey) {
                System.out.println("Type: " + subtaskValue.getType() + ". Status: " + subtaskValue.getStatus() + ". ID " + subtaskKey + ". Name: " + subtaskValue.getName());
                System.out.println("Description: " + subtaskValue.getDescription());
                System.out.println("Located in Epic: " + subtaskValue.e);
                return;
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
    //Для эпика вместе с обновлением реализовать логику статусов
    //подумать над методом выбора, и если не 1 и не 2
    public Task createUpdatedTask(Integer id){
        Task task = taskHashMap.get(id);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Изменить имя? 1 - да, 2 - нет");
        Integer myChoice1 = scanner.nextInt();
        if (myChoice1 == 1) {
            task.setName();
        }else{
           System.out.println("Имя не изменено");
        }
        System.out.println("Изменить описание? 1 - да, 2 - нет");
        Integer myChoice2 = scanner.nextInt();
        if (myChoice2 == 1) {
            task.setDescription();
        }else{
            System.out.println("Описание не изменено");
        }
        System.out.println("Изменить статус? 1 - да, 2 - нет");
        Integer myChoice3 = scanner.nextInt();
        if (myChoice3 == 1) {
            System.out.println("Текущий статус: " + task.getStatus() +". Введите новый статус. 1 - NEW. 2 - IN PROGRESS. 3 - DONE.");
           Integer status = scanner.nextInt();
           if(status == 1) {
               task.setStatus("NEW");
           } else if (status == 2) {
               task.setStatus("IN PROGRESS");
           } else if (status == 3) {
               task.setStatus("DONE");
           }else {
               System.out.println("Статус не изменен");
               task.setStatus(task.getStatus());
           }
        }else{
            System.out.println("Статус не изменен");
        }
         return  task;
    }







 }
