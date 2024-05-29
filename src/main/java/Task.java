import java.util.Scanner;

public class Task {
    Integer id;
    String name;
    String description;
    String status;

    public Task() {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName() {
        System.out.println("Введите название задачи");
        Scanner scanner = new Scanner(System.in);
        name = scanner.nextLine();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription() {
        System.out.println("Введите описание задачи");
        Scanner scanner = new Scanner(System.in);
        description = scanner.nextLine();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
