package ru.job4j.lambda;

public class ConstructorRefMain {

    /**
     * Почему идет обращение именно к конструктору с параметром?
     * Очень просто - функциональный интерфейс содержит метод, который принимает один параметр
     * если бы мы в функциональном интерфейсе принимали 0 аргументов - тогда бы вызывался конструтор без параметров
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        FuncInterface modelConstructor = Model::new;
        Model model = modelConstructor.function("Example");
        System.out.println("Значение равно: " + model.getName());
    }
}
