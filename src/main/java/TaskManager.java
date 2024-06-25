import java.util.*;

public class TaskManager {
    private static Integer currentId = 0;

    //Хранилища тикетов
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

    //Получение списка всех Задач
    public ArrayList<Task> getTasksList() {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Map.Entry<Integer, Task> entry : taskHashMap.entrySet()) {
            tasks.add(entry.getValue());
        }
        return tasks;
    }

    //Получение списка всех Эпиков
    public ArrayList<Epic> getEpicsList() {
        ArrayList<Epic> epics = new ArrayList<>();
        for (Map.Entry<Integer, Epic> entry : epicHashMap.entrySet()) {
            epics.add(entry.getValue());
        }
        return epics;
    }
    //Получение списка всех Сабтасок
    public ArrayList<Subtask> getSubtasksList() {
        ArrayList<Subtask> subtasks = new ArrayList<>();
        for (Map.Entry<Integer, Subtask> entry : subtaskHashMap.entrySet()) {
            subtasks.add(entry.getValue());
        }
        return subtasks;
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

    //Удаление всех задач
    public void deleteAll() {
        subtaskHashMap.clear();
        taskHashMap.clear();
        epicHashMap.clear();
        TaskManager.currentId = 0;
    }

    //Удаление тикетов по id
    public void deleteById(Integer id) {
        if (isTaskId(id)) {
            taskHashMap.remove(id);
        } else if (isEpicId(id)) {
            //Если удаляю эпик, то удаляю и его подзадачи
            ArrayList<Subtask> subtasks = getSubtasksInEpic(id);
            for (int i = 0; i < subtasks.size(); i++) {
                subtaskHashMap.remove(subtasks.get(i).getId());
            }
            epicHashMap.remove(id);
        } else if (isSubtaskId(id)) {
            Subtask subtask = subtaskHashMap.get(id);
            Epic epic = subtask.getEpic();
            deleteSubtaskFromEpic(epic.getId(), id);
            subtaskHashMap.remove(id);
        } else {
            System.out.println("Тикет с id " + id + " не найден.");
        }
    }

    //ОБНОВЛЕНИЕ
    //Обновление подзадачи в эпике
    public void replaceSubtaskInEpic(Integer id, Subtask subtask) {
        if (isEpicId(id)) {
            Epic epicValue = epicHashMap.get(id);
            ArrayList<Subtask> subtasks = epicValue.getListSubtasksInEpic();
            boolean found = false;
            for (int i = 0; i < subtasks.size(); i++) {
                if (subtasks.get(i).equals(subtask)) {
                    subtasks.set(i, subtask);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Объект не найден в списке.");
            }
        }
    }

    //Замена тикетов на новые в хранилище
    public void replaceTaskInStorage(Task task) {
        taskHashMap.replace(task.getId(), task);
    }

    public void replaceEpicInStorage(Epic epic) {
        epicHashMap.replace(epic.getId(), epic);
    }

    public void replaceSubtaskInStorage(Subtask subtask) {
        subtaskHashMap.replace(subtask.getId(), subtask);

    }


    //УПРАВЛЕНИЕ СТАТУСАМИ

//    public static boolean areAllSubtasksStatusesEqual(ArrayList<String> list, String status) {
//        for (String element : list) {
//            if (!element.equals(status)) {
//                return false;
//            }
//        }
//        return true;
//    }


    public String checkEpicStatus(Integer epicId) {
        ArrayList<Subtask> currentSubtasksList = getSubtasksInEpic(epicId);
        boolean allNew = true;
        boolean allDone = true;
        if (currentSubtasksList.isEmpty()) {
            return "NEW";
        }
        for (Subtask subtask : currentSubtasksList) {
            if (!subtask.getStatus().equals("NEW")) {
                allNew = false;
            }
            if (!subtask.getStatus().equals("DONE")) {
                allDone = false;
            }
        }
        if (allNew) {
            return "NEW";
        } else if (allDone) {
            return "DONE";
        } else {
            return "IN PROGRESS";
        }
    }

//    public String checkEpicStatus1(Integer epicId){
//        ArrayList<Subtask> currentSubtasksList = getSubtasksInEpic(epicId);
//        ArrayList<String> allStatuses = new ArrayList<>();
//        for (int i = 0; i < currentSubtasksList.size(); i++) {
//            allStatuses.add(currentSubtasksList.get(i).getStatus());
//            if (areAllSubtasksStatusesEqual(allStatuses, "NEW") || currentSubtasksList.size() == 0) {
//               return "NEW";
//            } else if (areAllSubtasksStatusesEqual(allStatuses, "DONE")) {
//                return "DONE";
//            } else {
//                return "IN PROGRESS";
//            }
//        }
//        return null;
//    }


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


    //Метод выбора варианта 1 или 2 для меню
    public boolean choice(int choice) {
        if (choice == 1) {
            return true;
        }
        return false;
    }

    //МЕТОДЫ ДЛЯ ПЕЧАТИ

    //Печать списков тикетов
    public void printTasks() {
        for (Task task : getTasksList()) {
            System.out.println(task);
        }
    }

    public void printEpics() {
        for (Epic epic : getEpicsList()) {
            System.out.println(epic);
        }
    }

    public void printSubtasks() {
        for (Subtask subtask : getSubtasksList()) {
            System.out.println(subtask);
        }
    }

    public  void printSubtasksWithEpic(){
        for (Subtask subtask : getSubtasksList()) {
            System.out.print(subtask);
            System.out.println(" EpicID=" + subtask.getEpic().getId());
        }
    }

    //Печать всех подзадач эпика
    public void printSubtasksInEpic(Integer epicId) {
        for (Subtask subtask : getSubtasksInEpic(epicId)) {
            System.out.println(subtask);
        }
    }

    //Пeчать тикета по id
    public void printIssueById(Integer id) {
        if (isTaskId(id)) {
            System.out.println(taskHashMap.get(id));
        } else if (isEpicId(id)) {
            System.out.println(epicHashMap.get(id));
        } else if (isSubtaskId(id)) {
            System.out.println(subtaskHashMap.get(id));
        } else {
            System.out.println("Тикет с id " + id + " не найден.");
        }
    }
}
