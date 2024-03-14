import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class Main3 {

    public static void main(String[] args) {
        // Приклад виклику методу
        SampleObject sampleObject = new SampleObject();
        String methodName = "sum";
        List<Object> parameters = Arrays.asList(2, 3);

        try {
            invokeMethod(sampleObject, methodName, parameters);
        } catch (FunctionNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void invokeMethod(Object object, String methodName, List<Object> parameters) throws FunctionNotFoundException {
        try {
            // Отримуємо клас об'єкта
            Class<?> objectClass = object.getClass();

            // Отримуємо метод за іменем і параметрами
            Method method = findMethod(objectClass, methodName, parameters);

            // Викликаємо метод на об'єкті з переданими параметрами
            Object result = method.invoke(object, parameters.toArray());

            System.out.println("Результат виклику методу '" + methodName + "': " + result);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new FunctionNotFoundException("Функція не знайдена: " + e.getMessage());
        }
    }

    private static Method findMethod(Class<?> clazz, String methodName, List<Object> parameters) throws NoSuchMethodException {
        // Отримуємо всі методи класу
        Method[] methods = clazz.getMethods();

        // Шукаємо метод за іменем і кількістю параметрів
        for (Method method : methods) {
            if (method.getName().equals(methodName) && method.getParameterCount() == parameters.size()) {
                return method;
            }
        }

        throw new NoSuchMethodException("Метод не знайдено");
    }

    // Приклад класу для тестування
    static class SampleObject {
        public int sum(int a, int b) {
            return a + b;
        }
    }

    // Виключення, яке викидається, коли метод не знайдено
    static class FunctionNotFoundException extends Exception {
        public FunctionNotFoundException(String message) {
            super(message);
        }
    }
}