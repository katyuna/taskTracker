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
            } else if (userInput == 4) {
                //Показать список задач Task.
                manager.getTasks();
            } else if (userInput == 5) {
                //Показать список задач Epic.
                manager.getEpics();
            } else if (userInput == 6) {
                //Показать список задач Subtasks.
                manager.getSubtasks();
            } else if (userInput == 1) {
                manager.addTaskToStorage(manager.createTask());
            } else if (userInput == 2) {
                //Создать задачу Epic.
                manager.addEpicToStorage(manager.createEpic());
            }else if (userInput == 3) {
                //Создать Подзадачу.
                manager.addSubtaskToStorage(manager.createSubtask());
            }else if (userInput == 31) {
                //Добавить подзадачу в Эпик.
                System.out.println("Введите ID эпика, в который нужно добавить подзадачу.");
                int epicId = scanner.nextInt();
                System.out.println("Введите ID подзадачи, которую нужно добавить в эпик.");
                int subtaskId = scanner.nextInt();
                manager.addSubtaskToEpicByIds(epicId, subtaskId);
            } else if (userInput == 51) {
                System.out.println("Введите ID эпика, для которого нужно получить список подзадач.");
                int epicId = scanner.nextInt();
                manager.getSubtasksByEpicId(epicId);
            } else if (userInput == 7) {
                System.out.println("Введите ID.");
                int id = scanner.nextInt();
                manager.getById(id);
            } else if (userInput == 8) {
                System.out.println("Введите ID для удаления.");
                int id = scanner.nextInt();
                manager.deleteById(id);
            } else if (userInput == 9) {
                System.out.println("Все удалено!.");
                manager.deleteAll();
            } else if (userInput == 11) {
                manager.getAllIssues();
            } else if (userInput == 10) {
                System.out.println("Введите ID для обновления.");
                int id = scanner.nextInt();



            } else {
                System.out.println("Выберите вариант из меню.");
            }

        }
    }

    private static void printMenu() {
        System.out.println("11 - Посмотреть полный список.");
        System.out.println("1 - Создать Task.");
        System.out.println("2 - Создать Epic.");
        System.out.println("3 - Создать Subtask.");
        System.out.println("    31 - Добавить подзадачу в эпик");
        System.out.println("4 - Показать список Task.");
        System.out.println("5 - Показать список Epic.");
        System.out.println("    51 - Показать список подзадач Эпика.");
        System.out.println("6 - Показать список Subtask.");
        System.out.println("7 - Вывести по id.");
        System.out.println("8 - Удалить по id.");
        System.out.println("9 - Удалить все.");
        System.out.println("10 - Обновить Task.");
        System.out.println("0 - Выход.");
    }
}