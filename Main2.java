import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main2 {

    public static void inspectObject(Object obj) {
        // Отримати тип об'єкта
        Class<?> objClass = obj.getClass();
        System.out.println("Реальний тип об'єкта: " + objClass.getName());

        // Вивести список полів та їх значень
        System.out.println("\nПоля та їх значення:");
        Field[] fields = objClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                System.out.println(field.getName() + ": " + field.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        // Вивести список відкритих методів
        System.out.println("\nВідкриті методи:");
        Method[] methods = objClass.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
    }

    public static void main(String[] args) {
        // Створити об'єкт для інспекції
        // Приклад: можна створити власний клас і передати його об'єкт для інспекції
        // Object obj = new ВашКлас();

        // Наприклад, створимо об'єкт класу String для прикладу
        Object obj = new String("Hello, World!");

        // Викликати метод для інспекції об'єкта
        inspectObject(obj);

        // Обробка виклику методу користувачем
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nВиберіть метод для виклику (або 'exit' для завершення):");
        String methodName = scanner.nextLine();

        while (!methodName.equals("exit")) {
            try {
                Method selectedMethod = obj.getClass().getMethod(methodName);
                Object result = selectedMethod.invoke(obj);

                System.out.println("Результат виклику методу " + methodName + ": " + result);
            } catch (Exception e) {
                System.out.println("Помилка: " + e.getMessage());
            }

            System.out.println("\nВиберіть метод для виклику (або 'exit' для завершення):");
            methodName = scanner.nextLine();
        }

        System.out.println("Програма завершена.");
    }
}