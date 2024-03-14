import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Field;

public class Main6 {

    public static void main(String[] args) {
        try {
            // Заданий клас (в даному випадку - String)
            Class<?> clazz = String.class;

            // Вивести список конструкторів класу
            Constructor<?>[] constructors = clazz.getConstructors();
            System.out.println("Список конструкторів:");
            for (Constructor<?> constructor : constructors) {
                System.out.println(constructor);
            }

            // Вибрати конструктор (приклад: конструктор з одним параметром типу char[])
            Constructor<?> selectedConstructor = clazz.getConstructor(char[].class);

            // Створити об'єкт за допомогою вибраного конструктора
            Object object = selectedConstructor.newInstance((Object) new char[]{'h', 'e', 'l', 'l', 'o'});
            printObjectState(object);

            // Вивести список методів класу
            Method[] methods = clazz.getMethods();
            System.out.println("\nСписок методів:");
            for (Method method : methods) {
                System.out.println(method);
            }

            // Вибрати метод (приклад: метод length())
            Method selectedMethod = clazz.getMethod("length");

            // Викликати вибраний метод та вивести результат
            Object result = selectedMethod.invoke(object);
            System.out.println("\nРезультат виклику методу length: " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Допоміжний метод для виведення стану об'єкта
    private static void printObjectState(Object object) {
        System.out.println("\nСтан об'єкта:");
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                System.out.println(field.getName() + ": " + field.get(object));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}