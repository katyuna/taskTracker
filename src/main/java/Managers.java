public class Managers <T extends TaskManager>{
    /*
    Ограничение на верхнюю границу - можно использовать только те типы, которые
    реализуют интерфейс TaskManager т.е. только менеджеры.
    */
    T manager;

    //конструктор
    public Managers(T manager) {
        this.manager = manager;
    }
    //метод для возвращения объекта-менеджера
    public T getDefault() {
        return manager;
    }
}
