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

    //Создание новых тикетов
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

    //Проверка есть ли id в хранилище
    public boolean isEpicId(Integer id) {
        if (epicHashMap.containsKey(id)) {
            return true;
        }
        return false;
    }

    public boolean isTaskId(Integer id) {
        if (taskHashMap.containsKey(id)) {
            return true;
        }
        return false;
    }

    public boolean isSubtaskId(Integer id) {
        if (subtaskHashMap.containsKey(id)) {
            return true;
        }
        return false;
    }

    //Добавление подзадачи в эпик
    public void addSubtaskToEpic(Integer epicId, Subtask subtask) {
        if (isEpicId(epicId)) {
            Epic epicValue = epicHashMap.get(epicId);
            epicValue.getListSubtasksInEpic().add(subtask);
            subtask.setEpic(epicValue);
        }
    }

    //Удаление подзадачи из Эпика
    public void deleteSubtaskFromEpic(Integer epicId, Integer subtaskId) {
        if (isEpicId(epicId)) {
            Epic epicValue = epicHashMap.get(epicId);
            ArrayList<Subtask> listSubtasksInEpic = epicValue.getListSubtasksInEpic();
            for (int i = 0; i < listSubtasksInEpic.size(); i++) {
                Subtask subtask = listSubtasksInEpic.get(i);
                if (subtask.getId().equals(subtaskId)) {
                    listSubtasksInEpic.remove(i);
                    break;
                }
            }
        }
    }

    //Получение подзадач Эпика
    public ArrayList<Subtask> getSubtasksInEpic(Integer epicId) {
        Epic epicValue = epicHashMap.get(epicId);
        return epicValue.getListSubtasksInEpic();
    }

   //Получение Задачи по id
    public Task getTaskById(Integer id) {
        if (isTaskId(id)) {
            return taskHashMap.get(id);
        }
        return null;
    }
    //Получение Эпика по id
    public Epic getEpicById(Integer id) {
        if (isEpicId(id)) {
            return epicHashMap.get(id);

        }
        return null;
    }
    //Получение Сабтаски по id
    public Subtask getSubtaskById(Integer id) {
        if (isSubtaskId(id)) {
            return subtaskHashMap.get(id);
        }
        return null;
    }

//    public Object getIssueById(Integer id){
//        if (isTaskId(id)) {
//            return taskHashMap.get(id);
//        }if (isEpicId(id)) {
//            return epicHashMap.get(id);
//        }if (isSubtaskId(id)) {
//            return subtaskHashMap.get(id);
//        }
//        return null;
//    }



    //Удаление всех задач
    public void deleteAll() {
        //удаление ссылок на объекты, если нет ссылок - сборщик мусора удалит объекты
        subtaskHashMap.clear();
        taskHashMap.clear();
        epicHashMap.clear();
        //Обнуляем id, чтобы при новом создании снова id начинались в 0
        TaskManager.currentId = 0;
    }

    //Удаление задачи по id
    public void deleteById(Integer id) {
        if (isTaskId(id)) {
            // Удаляем элемент с заданным ключом
            taskHashMap.remove(id);
        } else if (isEpicId(id)) {
            epicHashMap.remove(id);
            //Подзадачи тоже грохнуть?

        } else if (isSubtaskId(id)) {
            Subtask subtask = subtaskHashMap.get(id);
            Epic epic = subtask.getEpic();
            deleteSubtaskFromEpic(epic.getId(), id);
            subtaskHashMap.remove(id);
        } else {
            System.out.println("Сабтаска с id " + id + " не найдена.");
        }
    }

    //Обновление полей тикетов
    public void updateTaskName(Integer id, String name) {
        getTaskById(id).setName(name);
    }

    public void updateTaskDescription(Integer id, String description) {
        getTaskById(id).setDescription(description);
    }

    public void updateTaskStatus(Integer id, String status) {
        getTaskById(id).setStatus(status);
    }

    private void updateEpicName(Integer id, String name) {
        getEpicById(id).setName(name);
    }

    private void updateEpicDescription(Integer id, String description) {
        getEpicById(id).setDescription(description);
    }
    private void updateEpicStatus(Integer id, String status) {
        getEpicById(id).setStatus(status);
    }

    //Метод выбора варианта 1 или 2 для меню
      public boolean choice(int choice) {
        if (choice == 1) {
            return true;
        }
        return false;
    }

    //Общий метод для апдейта тикета по id
    //Нужен общий метод получения огбъекта для передачи в этот метод
    //Кажется, так не сработает.
    //Определить тип объекта, который передали в метод.
    //Привести объект к соответствующему типу.
    public void updateIssue (Object object){
        if (object instanceof  Task){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Новое имя");
            updateTaskName(((Task) object).getId(), scanner.nextLine());
            System.out.println("Новое описание");
            updateTaskDescription(((Task) object).getId(), scanner.nextLine());
            System.out.println("Новый статус");
            updateTaskStatus(((Task) object).getId(), scanner.nextLine());
        }//if Эпик if Сабтаска.
    }


//Обновление тикетов
    public void updateTask(Task task) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Новое имя");
        updateTaskName(task.getId(), scanner.nextLine());
        System.out.println("Новое описание");
        updateTaskDescription(task.getId(), scanner.nextLine());
        System.out.println("Новый статус");
        updateTaskStatus(task.getId(), scanner.nextLine());
    }

    public void updateEpic(Epic epic) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Новое имя");
        updateEpicName(epic.getId(), scanner.nextLine());
        System.out.println("Новое описание");
        updateEpicDescription(epic.getId(), scanner.nextLine());
        System.out.println("Новый статус");
        updateEpicStatus(epic.getId(), scanner.nextLine());
    }
    public void updateSubtask(Subtask subtask) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Новое имя");
        updateEpicName(subtask.getId(), scanner.nextLine());
        System.out.println("Новое описание");
        updateEpicDescription(subtask.getId(), scanner.nextLine());
        System.out.println("Новый статус");
        updateEpicStatus(subtask.getId(), scanner.nextLine());
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


    //МЕТОДЫ ДЛЯ ПЕЧАТИ

    //Печать тикетов
    public void printTask(Task task) {
        System.out.println(task.getType() + " " + task.getStatus() + " ID " + task.getId() + ": " + task.getName() + ". Description: " + task.getDescription());
    }

    public void printEpic(Epic epic) {
        System.out.println(epic.getType() + " " + epic.getStatus() + " ID " + epic.getId() + ": " + epic.getName() + ". Description: " + epic.getDescription() + " -> Subtasks in this Epic: " + epic.getListSubtasksInEpic());
    }

    public void printSubtask(Subtask subtask) {
        System.out.println(subtask.getType() + " " + subtask.getStatus() + " ID " + subtask.getId() + ": " + subtask.getName() + ". Description: " + subtask.getDescription() + "  -> This Subtask in Epic: " + subtask.epic);
    }

    //Печать списков тикетов
    public void getTasks() {
        for (Map.Entry<Integer, Task> entry : taskHashMap.entrySet()) {
            Task task = entry.getValue();
            printTask(task);
        }
    }

    public void getEpics() {
        for (Map.Entry<Integer, Epic> entry : epicHashMap.entrySet()) {
            Epic epic = entry.getValue();
            printEpic(epic);
        }
    }

    public void getSubtasks() {
        for (Map.Entry<Integer, Subtask> entry : subtaskHashMap.entrySet()) {
            Subtask subtask = entry.getValue();
            printSubtask(subtask);
        }
    }

    //Печать всех подзадач эпика
    public void printSubtasksInEpic(Integer epicId) {
        ArrayList<Subtask> subtasksInEpic = getSubtasksInEpic(epicId);
        for (int i = 0; i < subtasksInEpic.size(); i++) {
            System.out.println(subtasksInEpic.get(i).getType() + " " + subtasksInEpic.get(i).getStatus() + " " + subtasksInEpic.get(i).getId() + ": " + subtasksInEpic.get(i).getName() + ". Description: " + subtasksInEpic.get(i).getDescription());
        }
    }

    //Пeчать тикета по id
    public void printIssueById(Integer id) {
        if (isTaskId(id)) {
            printTask(taskHashMap.get(id));
        } else if (isEpicId(id)) {
            printEpic(epicHashMap.get(id));
        } else if (isSubtaskId(id)) {
            printSubtask(subtaskHashMap.get(id));
        } else {
            System.out.println("Тикет с id " + id + " не найден.");
        }
    }
}
