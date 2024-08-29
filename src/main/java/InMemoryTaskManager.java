import java.util.*;

public class InMemoryTaskManager implements TaskManager {
    private static Integer currentId = 0;

    //Хранилища тикетов
    Map<Integer, Task> taskHashMap = new HashMap<>();
    Map<Integer, Epic> epicHashMap = new HashMap<>();
    Map<Integer, Subtask> subtaskHashMap = new HashMap<>();

    //Создание новых тикетов
    @Override
    public Task createTask(String name, String description) {
        Task task = new Task("Task", currentId++, name,description, Status.NEW);
        return task;
    }
    @Override
    public Epic createEpic(String name, String description) {
        Epic epic = new Epic("Epic", currentId++, name, description, Status.NEW, new ArrayList<>());
        return epic;
    }
    @Override
    public Subtask createSubtask(String name, String description, Epic epic) {
        Subtask subtask = new Subtask("Subtask", currentId++, name, description, Status.NEW, epic);
        return subtask;
    }

    //Добавление тикетов в хранилище
    @Override
    public void addTaskToStorage(Task task) {
        taskHashMap.put(task.getId(), task);
    }
    @Override
    public void addEpicToStorage(Epic epic) {
        epicHashMap.put(epic.getId(), epic);
    }
    @Override
    public void addSubtaskToStorage(Subtask subtask) {
        subtaskHashMap.put(subtask.getId(), subtask);
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


    /*START ИСТОРИЯ------------------------------*/
    //список просмотренных задач
    List<Task> historyList = new ArrayList<>();

    // Check List Size
    public boolean checkHistoryListSize(List<Task> list) {
        if (list.size() < 10) {
            return true;
        } else {
            return false;
        }
    }

    //Добавление просмотренной задачи в список
    public void addTaskToTheHistoryList(List<Task> historyList, Task task) {
        if (checkHistoryListSize(historyList)) {
            historyList.add(task);
        } else {
            historyList.remove(0);
            historyList.add(task);
        }
    }

    public List<Task> history() {
        return historyList;
    }

    /*END ИСТОРИЯ------------------------------*/


    //Получение Задачи по id
    @Override
    public Task getTaskById(Integer id) {
        if (isTaskId(id)) {
            addTaskToTheHistoryList(historyList, taskHashMap.get(id));
            return taskHashMap.get(id);
        }
        return null;
    }
    //Получение Эпика по id
    @Override
    public Epic getEpicById(Integer id) {
        if (isEpicId(id)) {
            addTaskToTheHistoryList(historyList, epicHashMap.get(id));
            return epicHashMap.get(id);
        }
        return null;
    }
    //Получение Сабтаски по id
    @Override
    public Subtask getSubtaskById(Integer id) {
        if (isSubtaskId(id)) {
            addTaskToTheHistoryList(historyList, subtaskHashMap.get(id));
            return subtaskHashMap.get(id);
        }
        return null;
    }






    //Удаление всех задач
    @Override
    public void deleteAll() {
        subtaskHashMap.clear();
        taskHashMap.clear();
        epicHashMap.clear();
        InMemoryTaskManager.currentId = 0;
    }

    //Удаление тикетов по id
    @Override
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
            for (int i = 0; i < subtasks.size(); i++) {
                if (subtasks.get(i).getId().equals(subtask.getId())) {
                    subtasks.set(i, subtask);
                    //надо теперь список сабтасков отдать обратно в эпик
                    epicValue.setListSubtasksInEpic(subtasks); // Устанавливаем обновленный список в Epic
                    epicHashMap.put(id, epicValue); // Обновляем Epic в HashMap
                    break;
                } else {
                    System.out.println("Объект не найден в списке.");
                }
            }
        }
    }

    //Замена тикетов на новые в хранилище
    @Override
    public void replaceTaskInStorage(Task task) {
        taskHashMap.replace(task.getId(), task);
    }
    @Override
    public void replaceEpicInStorage(Epic epic) {
        epicHashMap.replace(epic.getId(), epic);
    }
    @Override
    public void replaceSubtaskInStorage(Subtask subtask) {
        subtaskHashMap.replace(subtask.getId(), subtask);
    }

    //УПРАВЛЕНИЕ СТАТУСАМИ
    public Status checkEpicStatus(Integer epicId) {
        ArrayList<Subtask> currentSubtasksList = getSubtasksInEpic(epicId);
        boolean allNew = true;
        boolean allDone = true;
        if (currentSubtasksList.isEmpty()) {
            return Status.NEW;
        }
        for (Subtask subtask : currentSubtasksList) {
            if (!subtask.getStatus().equals(Status.NEW)) {
                allNew = false;
            }
            if (!subtask.getStatus().equals(Status.DONE)) {
                allDone = false;
            }
        }
        if (allNew) {
            return Status.NEW;
        } else if (allDone) {
            return Status.DONE;
        } else {
            return Status.IN_PROGRESS;
        }
    }

    //Метод выбора статуса для меню
    public Status statusChoice (int choice){
        if(choice == 1){
            Status status = Status.NEW;
            return status;
        } else if (choice == 2) {
            Status status = Status.IN_PROGRESS;
            return status;
        } else if (choice == 3) {
            Status status = Status.DONE;
            return status;
        }else {
            System.out.println("Статус NEW");
            Status status = Status.NEW;
            return status;
        }
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
}
