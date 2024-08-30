import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InMemoryTaskManager taskManager = new InMemoryTaskManager();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            int userInput = scanner.nextInt();
            if (userInput == 0) {
                System.out.println("Вы вышли из таск-трекера.");
                break;
            } else if (userInput == 1) {
                taskManager.printTasks();
                taskManager.printEpics();
                taskManager.printSubtasksWithEpic();
            } else if (userInput == 2) {
                //Создать Task
                scanner.nextLine();
                System.out.println("Введите название Задачи");
                String name = scanner.nextLine();
                System.out.println("Введите описание Задачи");
                String description = scanner.nextLine();
                Task task = taskManager.createTask(name, description);
                taskManager.addTaskToStorage(task);
                System.out.println(task);
            } else if (userInput == 3) {
                //Создать Epic.
                scanner.nextLine();
                System.out.println("Введите название Эпика");
                String name = scanner.nextLine();
                System.out.println("Введите описание Эпика");
                String description = scanner.nextLine();
                Epic epic = taskManager.createEpic(name, description);
                taskManager.addEpicToStorage(epic);
                System.out.println(epic);
            } else if (userInput == 4) {
                //Создать Subtask.
                System.out.println("Введите ID эпика, в котором нужно создать подзадачу.");
                if (scanner.hasNextInt()){
                    int epicId = scanner.nextInt();
                    scanner.nextLine();
                    if (taskManager.isEpicId(epicId)) {
                        System.out.println("Введите название Сабтаски");
                        String name = scanner.nextLine();
                        System.out.println("Введите описание Сабтаски");
                        String description = scanner.nextLine();
                        Subtask subtask = taskManager.createSubtask(name, description, taskManager.getEpicById(epicId));
                        taskManager.addSubtaskToEpic(epicId, subtask);
                        taskManager.addSubtaskToStorage(subtask);
                        System.out.println(subtask);
                        Status epicStatus = taskManager.checkEpicStatus(subtask.getEpic().getId());
                        taskManager.getEpicById(subtask.getEpic().getId()).setStatus(epicStatus);
                    } else {
                        System.out.println("Нет такого Эпика");
                    }
                }else {
                    System.out.println("Ошибка: введен не id.");
                }
            } else if (userInput == 5) {
                //Показать список Тасок
                taskManager.printTasks();
            } else if (userInput == 6) {
                //Показать список Эпиков
                taskManager.printEpics();
            } else if (userInput == 7) {
                //Показать список Сабтасок
                taskManager.printSubtasks();
            } else if (userInput == 8) {
                System.out.println("Введите ID эпика, для которого нужно получить список подзадач.");
                int epicId = scanner.nextInt();
                taskManager.printSubtasksInEpic(epicId);
            } else if (userInput == 9) {
                //Вывести тикет по id
                System.out.println("Введите ID.");
                int id = scanner.nextInt();
                System.out.println(taskManager.getById(id));
            } else if (userInput == 10) {
                //Удалить тикет по id
                System.out.println("Введите ID тикета для удаления.");
                int id = scanner.nextInt();
                taskManager.deleteById(id);
            } else if (userInput == 11) {
                //Удалить все
                System.out.println("Все удалено!.");
                taskManager.deleteAll();
            } else if (userInput == 12) {
                System.out.println("Введите id для обновления");
                Integer id = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Новое имя");
                String name = scanner.nextLine();
                System.out.println("Новое описание");
                String description = scanner.nextLine();
                if (taskManager.isTaskId(id)) {
                    System.out.println("Новый статус: 1 - NEW, 2 - IN_PROGRESS, 3 - DONE");
                    Integer choice = scanner.nextInt();
                    Status status = taskManager.statusChoice(choice);
                    Task task = new Task("Task", id, name, description, status);
                    taskManager.replaceTaskInStorage(task);
                } else if (taskManager.isEpicId(id)) {
                    Status status = taskManager.checkEpicStatus(id);
                    ArrayList<Subtask> subtasks = taskManager.getEpicById(id).getListSubtasksInEpic();
                    Epic epic = new Epic("Epic", id, name, description, status, subtasks);
                    taskManager.replaceEpicInStorage(epic);
                } else if (taskManager.isSubtaskId(id)) {
                    System.out.println("Новый статус: 1 - NEW, 2 - IN_PROGRESS, 3 - DONE");
                    Integer choice = scanner.nextInt();
                    Status status = taskManager.statusChoice(choice);
                    Epic epic = taskManager.getSubtaskById(id).getEpic();
                    Subtask subtask = new Subtask("Subtask", id, name, description, status, epic);
                    taskManager.replaceSubtaskInStorage(subtask);
                    taskManager.replaceSubtaskInEpic(subtask.getEpic().getId(), subtask);
                    Status epicStatus = taskManager.checkEpicStatus(subtask.getEpic().getId());
                    taskManager.getEpicById(subtask.getEpic().getId()).setStatus(epicStatus);
                }
            } else if (userInput == 13){
                for (int i = 0; i < taskManager.historyManager.getHistory().size(); i++){
                    System.out.println((i + 1) + ". " + taskManager.historyManager.getHistory().get(i));
                }
                //System.out.println(taskManager.historyManager.getHistory());
            }
            else {
                System.out.println("Выберите вариант из меню.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("_________");
        System.out.println("МЕНЮ");
        System.out.println("1 - Посмотреть полный список.");
        System.out.println("2 - Создать Task.");
        System.out.println("3 - Создать Epic.");
        System.out.println("4 - Создать Subtask.");
        System.out.println("5 - Показать список Task.");
        System.out.println("6 - Показать список Epic.");
        System.out.println("7 - Показать список Subtask.");
        System.out.println("8 - Показать список подзадач Эпика.");
        System.out.println("9 - Вывести по id.");
        System.out.println("10 - Удалить по id.");
        System.out.println("11 - Удалить все.");
        System.out.println("12 - Обновить по id.");
        System.out.println("13 - История просмотров.");
        System.out.println("0 - Выход.");
    }
}