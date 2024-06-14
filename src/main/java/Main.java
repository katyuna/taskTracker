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
                manager.getTasks();
                manager.getEpics();
                manager.getSubtasks();
            } else if (userInput == 2) {
                scanner.nextLine();
                System.out.println("Введите название Задачи");
                String name = scanner.nextLine();
                System.out.println("Введите описание Задачи");
                String description = scanner.nextLine();
                Task task = manager.createTask(name, description);
                manager.addTaskToStorage(task);
                manager.printTask(task);
            } else if (userInput == 3) {
                //Создать задачу Epic.
                scanner.nextLine();
                System.out.println("Введите название Эпика");
                String name = scanner.nextLine();
                System.out.println("Введите описание Эпика");
                String description = scanner.nextLine();
                Epic epic = manager.createEpic(name, description);
                manager.addEpicToStorage(epic);
                manager.printEpic(epic);
            } else if (userInput == 4) {
                //Создать Подзадачу.
                System.out.println("Введите ID эпика, в котором нужно создать подзадачу.");
                int epicId = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Введите название Сабтаски");
                String name = scanner.nextLine();
                System.out.println("Введите описание Сабтаски");
                String description = scanner.nextLine();
                Subtask subtask = manager.createSubtask(name, description);
                manager.addSubtaskToEpic(epicId, subtask);
                manager.addSubtaskToStorage(subtask);
                manager.printSubtask(subtask);
//            } else if (userInput == 5) {
//                //Добавить подзадачу в Эпик.
//                System.out.println("Введите ID эпика, в который нужно добавить подзадачу.");
//                int epicId = scanner.nextInt();
//                System.out.println("Введите ID подзадачи, которую нужно добавить в эпик.");
//                int subtaskId = scanner.nextInt();
//                manager.addSubtaskToEpicByIds(epicId, subtaskId);
//                //manager.updateEpicStatus(epicId);
            } else if (userInput == 6) {
                //Показать список задач Task.
                manager.getTasks();
            } else if (userInput == 7) {
                //Показать список задач Epic.
                manager.getEpics();
            } else if (userInput == 8) {
                //Показать список задач Subtasks.
                manager.getSubtasks();
            } else if (userInput == 9) {
                System.out.println("Введите ID эпика, для которого нужно получить список подзадач.");
                int epicId = scanner.nextInt();
                manager.printSubtasksInEpic(epicId);
            } else if (userInput == 10) {
                System.out.println("Введите ID.");
                int id = scanner.nextInt();
                manager.printIssueById(id);
            } else if (userInput == 11) {
                System.out.println("Введите ID тикета для удаления.");
                int id = scanner.nextInt();
                manager.deleteById(id);
            } else if (userInput == 12) {
                System.out.println("Все удалено!.");
                manager.deleteAll();
            } else if (userInput == 13) {
                System.out.println("Введите ID тикета для обновления.");
                int id = scanner.nextInt();
                manager.addTaskToStorage(manager.updateTask(id));
            } else {
                System.out.println("Выберите вариант из меню.");
            }

        }
    }

    private static void printMenu() {
        System.out.println("1 - Посмотреть полный список.");
        System.out.println("2 - Создать Task.");
        System.out.println("3 - Создать Epic.");
        System.out.println("4 - Создать Subtask.");
//      System.out.println("5 - Перенести подзадачу в другой Эпик");
        System.out.println("6 - Показать список Task.");
        System.out.println("7 - Показать список Epic.");
        System.out.println("8 - Показать список Subtask.");
        System.out.println("9 - Показать список подзадач Эпика.");
        System.out.println("10 - Вывести по id.");
        System.out.println("11 - Удалить по id.");
        System.out.println("12 - Удалить все.");
        System.out.println("13 - Обновить Task.");
        System.out.println("0 - Выход.");
    }
}