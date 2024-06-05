import java.util.Scanner;

public class Task {
    Integer id;
    String name;
    String description;
    String status;
    String type;


    public Task() {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.type = type;
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
        System.out.println("Введите название");
        Scanner scanner = new Scanner(System.in);
        name = scanner.nextLine();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription() {
        System.out.println("Введите описание");
        Scanner scanner = new Scanner(System.in);
        description = scanner.nextLine();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
