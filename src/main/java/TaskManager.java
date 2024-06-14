import java.util.*;

public class TaskManager {
    private static Integer currentId = 0;

    //Хранение тикетов
    HashMap<Integer, Task> taskHashMap = new HashMap<>();
    HashMap<Integer, Epic> epicHashMap = new HashMap<>();
    HashMap<Integer, Subtask> subtaskHashMap = new HashMap<>();

    //Добавление тикетов в хранилище
    public void addTaskToStorage(Task task) {
        taskHashMap.put(task.getId(), task);
    }
    public void addEpicToStorage(Epic epic) {
        epicHashMap.put(epic.getId(), epic);
    }
    public void addSubtaskToStorage(Subtask subtask) {
        subtaskHashMap.put(subtask.getId(), subtask);
    }
    //Создание тикетов
    public Task createTask(String name, String description) {
        Task task = new Task(currentId++);
        task.setName(name);
        task.setDescription(description);
        task.setStatus("NEW");
        task.setType("Task");
        return task;
    }
    public Epic createEpic(String name, String description) {
        Epic epic = new Epic(currentId++);
        epic.setName(name);
        epic.setDescription(description);
        epic.setStatus("NEW");
        epic.setType("Epic");
        return epic;
    }
    public Subtask createSubtask(String name, String description) {
        Subtask subtask = new Subtask(currentId++);
        subtask.setName(name);
        subtask.setDescription(description);
        subtask.setStatus("NEW");
        subtask.setType("Subtask");
        return subtask;
    }


    public boolean isEpicIdEqualToKey(Integer epicId) {
        for (Map.Entry<Integer, Epic> entry : epicHashMap.entrySet()) {
            Integer epicKey = entry.getKey();
            if (epicId.equals(epicKey)) {
                return true;
            }
        }
        return false;
    }

    //Добавление подзадачи в эпик
    public void addSubtaskToEpic(Integer epicId, Subtask subtask) {
        if (isEpicIdEqualToKey(epicId)) {
            Epic epicValue = epicHashMap.get(epicId);
            epicValue.getListSubtasksInEpic().add(subtask);
            subtask.setEpic(epicValue);
        }
    }
     //Печать тикетов
    public void printTask(Task task){
        System.out.println(task.getType() + " " + task.getStatus() + " ID " + task.getId() + ": " + task.getName() + ". Description: " + task.getDescription());
    }
    public  void printEpic (Epic epic) {
        System.out.println(epic.getType() + " " + epic.getStatus() + " ID " + epic.getId() + ": " + epic.getName() + ". Description: " + epic.getDescription() + " -> Subtasks in this Epic: " + epic.getListSubtasksInEpic());
    }
    public void printSubtask (Subtask subtask){
        System.out.println(subtask.getType() + " " + subtask.getStatus() + " ID " + subtask.getId() + ": " + subtask.getName() + ". Description: " + subtask.getDescription() + "  -> This Subtask in Epic: " + subtask.epic);
    }
    //Получение списков тикетов
    public void getTasks() {
        for (Map.Entry<Integer, Task> entry : taskHashMap.entrySet()) {
//            Integer key = entry.getKey();
            Task task = entry.getValue();
            printTask(task);
        }
    }
    public void getEpics() {
        for (Map.Entry<Integer, Epic> entry : epicHashMap.entrySet()) {
//            Integer key = entry.getKey();
            Epic epic = entry.getValue();
            printEpic(epic);
        }
    }
    public void getSubtasks() {
        for (Map.Entry<Integer, Subtask> entry : subtaskHashMap.entrySet()) {
//            Integer key = entry.getKey();
            Subtask subtask = entry.getValue();
            printSubtask(subtask);
        }
    }
    public ArrayList<Subtask> getSubtasksInEpic(Integer epicId) {
        for (Map.Entry<Integer, Epic> entry : epicHashMap.entrySet()) {
            Integer epicKey = entry.getKey();
            Epic epicValue = entry.getValue();
            if (epicId.equals(epicKey)) {
                return epicValue.getListSubtasksInEpic();
            }
        }
        return null;
    }
    //Печать всех подзадач эпика
    public void printSubtasksInEpic(Integer epicId) {
        ArrayList<Subtask> subtasksInEpic = getSubtasksInEpic(epicId);
        for (int i = 0; i < subtasksInEpic.size(); i++) {
            System.out.println(subtasksInEpic.get(i).getType() + " " + subtasksInEpic.get(i).getStatus() + " " + subtasksInEpic.get(i).getId() + ": " + subtasksInEpic.get(i).getName() + ". Description: " + subtasksInEpic.get(i).getDescription());
        }
    }
    public Task getTaskById(Integer id) {
        for (Map.Entry<Integer, Task> entry : taskHashMap.entrySet()) {
            Integer key = entry.getKey();
            Task value = entry.getValue();
            if (id.equals(key)) {
                return value;
            }
        }

        return null;
    }
    public Epic getEpicById(Integer id) {
        for (Map.Entry<Integer, Epic> entry : epicHashMap.entrySet()) {
            Integer key = entry.getKey();
            Epic value = entry.getValue();
            if (id.equals(key)) {
                return value;
            }
        }

        return null;
    }
    public Subtask getSubtaskById(Integer id) {
        for (Map.Entry<Integer, Subtask> entry : subtaskHashMap.entrySet()) {
            Integer key = entry.getKey();
            Subtask value = entry.getValue();
            if (id.equals(key)) {
                return value;
            }
        }

        return null;
    }
    //Получение тикета по id
    public void printIssueById(Integer id) {
        for (Map.Entry<Integer, Task> entry : taskHashMap.entrySet()) {
            Integer taskKey = entry.getKey();
            Task taskValue = entry.getValue();
            if (id.equals(taskKey)) {
                printTask(taskValue);
            }
        }
        for (Map.Entry<Integer, Epic> entry : epicHashMap.entrySet()) {
            Integer epicKey = entry.getKey();
            Epic epicValue = entry.getValue();
            if (id.equals(epicKey)) {
                printEpic(epicValue);
            }
        }
        for (Map.Entry<Integer, Subtask> entry : subtaskHashMap.entrySet()) {
            Integer subtaskKey = entry.getKey();
            Subtask subtaskValue = entry.getValue();
            if (id.equals(subtaskKey)) {
                printSubtask(subtaskValue);
            }
        }
    }

    //Удаление всех задач
    public void deleteAll() {
        //удаление ссылок на объекты, если нет ссылок - сборщик мусора удалит объекты
        taskHashMap.clear();
        epicHashMap.clear();
        subtaskHashMap.clear();
        //Обнуляем id, чтобы при новом создании снова id начинались в 0
        TaskManager.currentId = 0;
    }

    //Удаление задачи по id
    public void deleteById(Integer id) {
        if (taskHashMap.containsKey(id)) {
            // Удаляем элемент с заданным ключом
            taskHashMap.remove(id);
        } else if (epicHashMap.containsKey(id)) {
            epicHashMap.remove(id);
            //подазадачи тоже грохнуть?

        } else if (subtaskHashMap.containsKey(id)) {
            subtaskHashMap.remove(id);
            //удалится ли связь с эпиком автомагически?

        } else {
            System.out.println("Элемент с ключом " + id + " не найден.");
        }
    }



//
//        //Использую итератор т.к. удаление элемента во время for вызывает ConcurrentModificationException
//        Iterator<Map.Entry<Integer, Task>> taskIterator = taskHashMap.entrySet().iterator();
//        while (taskIterator.hasNext()) {
//            Map.Entry<Integer, Task> entry = taskIterator.next();
//            if (entry.getKey().equals(id)) {
//                taskIterator.remove();
//            }
//        }
//        Iterator<Map.Entry<Integer, Epic>> epicIterator = epicHashMap.entrySet().iterator();
//        while (epicIterator.hasNext()) {
//            Map.Entry<Integer, Epic> entry = epicIterator.next();
//            if (entry.getKey().equals(id)) {
//                epicIterator.remove();
//            }
//        }
//        Iterator<Map.Entry<Integer, Subtask>> subtaskIterator = subtaskHashMap.entrySet().iterator();
//        while (subtaskIterator.hasNext()) {
//            Map.Entry<Integer, Subtask> entry = subtaskIterator.next();
//            if (entry.getKey().equals(id)) {
//                subtaskIterator.remove();
//            }
//        }
//    }


    //Обновление задач. Новая версия объекта и id передаются в виде параметра
    //Для эпика вместе с обновлением реализовать логику статусов
    //подумать над методом выбора, и если не 1 и не 2


    public Task updateTask(Integer id) {
        Task task = getTaskById(id);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Изменить имя? 1 - да, 2 - нет");
        int choiceOne = scanner.nextInt();
        if (choiceOne == 1) {
            System.out.println("Введите название");
            String name = scanner.nextLine();
            task.setName(name);
        } else {
            System.out.println("Имя не изменено");
        }
        System.out.println("Изменить описание? 1 - да, 2 - нет");
        int choiceTwo = scanner.nextInt();
        if (choiceTwo == 1) {
            System.out.println("Введите описание");
            String description = scanner.nextLine();
            task.setDescription(description);
        } else {
            System.out.println("Описание не изменено");
        }
        System.out.println("Изменить статус? 1 - да, 2 - нет");
        int choiceThree = scanner.nextInt();
        if (choiceThree == 1) {
            System.out.println("Текущий статус: " + task.getStatus() + ". Введите новый статус. 1 - NEW. 2 - IN PROGRESS. 3 - DONE.");
            int status = scanner.nextInt();
            if (status == 1) {
                task.setStatus("NEW");
            } else if (status == 2) {
                task.setStatus("IN PROGRESS");
            } else if (status == 3) {
                task.setStatus("DONE");
            } else {
                System.out.println("Статус не изменен");
                task.setStatus(task.getStatus());
            }
        } else {
            System.out.println("Статус не изменен");
        }
        return task;
    }

    public static boolean areAllSubtasksStatusesEqual(ArrayList<String> list, String status) {
        for (String element : list) {
            if (!element.equals(status)) {
                return false;
            }
        }
        return true;
    }

//    public void updateEpicStatus(Integer epicId) {
//        ArrayList<Subtask> currentSubtasksList = getSubtasksInEpic(epicId);
//        ArrayList<String> allStatuses = new ArrayList<>();
//        for (int i = 0; i <= currentSubtasksList.size(); i++) {
//            allStatuses.add(currentSubtasksList.get(i).getStatus());
//            if (areAllSubtasksStatusesEqual(allStatuses, "NEW") || currentSubtasksList.size() == 0) {
//                taskHashMap.get(epicId).setStatus("NEW");
//            } else if (areAllSubtasksStatusesEqual(allStatuses, "DONE")) {
//                taskHashMap.get(epicId).setStatus("DONE");
//            } else {
//                taskHashMap.get(epicId).setStatus("IN PROGRESS");
//            }
//        }
//    }
}
