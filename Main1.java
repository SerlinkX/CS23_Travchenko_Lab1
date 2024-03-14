import java.lang.reflect.*;

public class Main1 {

    public static void main(String[] args) {
        // Приклад використання: аналізуємо клас String
        String className = "java.lang.String";
        analyzeClass(className);
    }

    public static void analyzeClass(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            printClassInfo(clazz);
        } catch (ClassNotFoundException e) {
            System.out.println("Клас не знайдено: " + className);
        }
    }

    public static void analyzeClass(Class<?> clazz) {
        printClassInfo(clazz);
    }

    private static void printClassInfo(Class<?> clazz) {
        // Ім'я класу
        System.out.println("Ім'я класу: " + clazz.getName());

        // Пакет
        Package pkg = clazz.getPackage();
        System.out.println("Пакет: " + (pkg != null ? pkg.getName() : "Немає інформації"));

        // Модифікатори
        int modifiers = clazz.getModifiers();
        System.out.println("Модифікатори: " + Modifier.toString(modifiers));

        // Базовий клас
        Class<?> superClass = clazz.getSuperclass();
        System.out.println("Базовий клас: " + (superClass != null ? superClass.getName() : "Немає"));

        // Інтерфейси
        Class<?>[] interfaces = clazz.getInterfaces();
        System.out.println("Інтерфейси: " + (interfaces.length > 0 ? String.join(", ", getNames(interfaces)) : "Немає"));

        // Поля
        Field[] fields = clazz.getDeclaredFields();
        System.out.println("Поля:");
        for (Field field : fields) {
            System.out.println("  " + getFieldInfo(field));
        }

        // Конструктори
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        System.out.println("Конструктори:");
        for (Constructor<?> constructor : constructors) {
            System.out.println("  " + getConstructorInfo(constructor));
        }

        // Методи
        Method[] methods = clazz.getDeclaredMethods();
        System.out.println("Методи:");
        for (Method method : methods) {
            System.out.println("  " + getMethodInfo(method));
        }
    }

    private static String getFieldInfo(Field field) {
        return Modifier.toString(field.getModifiers()) + " " + field.getType().getName() + " " + field.getName();
    }

    private static String getConstructorInfo(Constructor<?> constructor) {
        return Modifier.toString(constructor.getModifiers()) + " " + constructor.getName() + getParametersInfo(constructor.getParameterTypes());
    }

    private static String getMethodInfo(Method method) {
        return Modifier.toString(method.getModifiers()) + " " + method.getReturnType().getName() + " " + method.getName() + getParametersInfo(method.getParameterTypes());
    }

    private static String getParametersInfo(Class<?>[] parameterTypes) {
        if (parameterTypes.length == 0) {
            return "()";
        }
        String[] parameterNames = getNames(parameterTypes);
        return "(" + String.join(", ", parameterNames) + ")";
    }

    private static String[] getNames(Class<?>[] classes) {
        String[] names = new String[classes.length];
        for (int i = 0; i < classes.length; i++) {
            names[i] = classes[i].getName();
        }
        return names;
    }
}