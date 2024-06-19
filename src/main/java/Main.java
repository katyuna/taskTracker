import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            int userInput = scanner.nextInt();
            if (userInput == 0) {
                System.out.println("Вы вышли из таск-трекера.");
                break;
            } else if (userInput == 1) {
                manager.printTasks();
                manager.printEpics();
                manager.printSubtasks();
            } else if (userInput == 2) {
                //Создать Task
                scanner.nextLine();
                System.out.println("Введите название Задачи");
                String name = scanner.nextLine();
                System.out.println("Введите описание Задачи");
                String description = scanner.nextLine();
                Task task = manager.createTask(name, description);
                manager.addTaskToStorage(task);
                System.out.println(task);
            } else if (userInput == 3) {
                //Создать Epic.
                scanner.nextLine();
                System.out.println("Введите название Эпика");
                String name = scanner.nextLine();
                System.out.println("Введите описание Эпика");
                String description = scanner.nextLine();
                Epic epic = manager.createEpic(name, description);
                manager.addEpicToStorage(epic);
                System.out.println(epic);
            } else if (userInput == 4) {
                //Создать Subtask.
                System.out.println("Введите ID эпика, в котором нужно создать подзадачу.");
                int epicId = scanner.nextInt();
                scanner.nextLine();
                if (manager.isEpicId(epicId)) {
                    System.out.println("Введите название Сабтаски");
                    String name = scanner.nextLine();
                    System.out.println("Введите описание Сабтаски");
                    String description = scanner.nextLine();
                    Subtask subtask = manager.createSubtask(name, description);
                    manager.addSubtaskToEpic(epicId, subtask);
                    manager.addSubtaskToStorage(subtask);
                    System.out.println(subtask);
                } else {
                    System.out.println("Нет такого Эпика");
                }
            } else if (userInput == 5) {
                //Показать список Тасок
                manager.printTasks();
            } else if (userInput == 6) {
                //Показать список Эпиков
                manager.printEpics();
            } else if (userInput == 7) {
                //Показать список Сабтасок
                manager.printSubtasks();
            } else if (userInput == 8) {
                System.out.println("Введите ID эпика, для которого нужно получить список подзадач.");
                int epicId = scanner.nextInt();
                manager.printSubtasksInEpic(epicId);
            } else if (userInput == 9) {
                //Вывести тикет по id
                System.out.println("Введите ID.");
                int id = scanner.nextInt();
                manager.printIssueById(id);
            } else if (userInput == 10) {
                //Удалить тикет по id
                System.out.println("Введите ID тикета для удаления.");
                int id = scanner.nextInt();
                manager.deleteById(id);
            } else if (userInput == 11) {
                //Удалить все
                System.out.println("Все удалено!.");
                manager.deleteAll();
            } else if (userInput == 12) {
                System.out.println("Введите id для обновления");
                Integer id = scanner.nextInt();
                System.out.println("Новое имя");
                String name = scanner.nextLine();
                scanner.nextLine();
                System.out.println("Новое описание");
                String description = scanner.nextLine();
                scanner.nextLine();
                System.out.println("Новый статус");
                String status = scanner.nextLine();
                if (manager.isTaskId(id)) {
                    Task task = new Task("Task", id, name, description, status);
                    manager.replaceTaskInStorage(task);
                }
                if (manager.isEpicId(id)) {
                    Epic epic = new Epic("Epic", id, name, description,status);
                    manager.replaceEpicInStorage(epic);
                }
                if (manager.isSubtaskId(id)) {
                    Subtask subtask = new Subtask("Subtask", id, name, description,status);
                    manager.replaceSubtaskInStorage(subtask);
                }
            }else {
                System.out.println("Выберите вариант из меню.");
            }
        }
    }

    private static void printMenu() {
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
        System.out.println("0 - Выход.");
    }
}