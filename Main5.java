import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface MyInterface {
    int calculate(int a, int b);
}

class MyImplementation implements MyInterface {
    @Override
    public int calculate(int a, int b) {
        return a + b;
    }
}

class ProfilingHandler implements InvocationHandler {
    private final Object target;

    public ProfilingHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.nanoTime();
        Object result = method.invoke(target, args);
        long endTime = System.nanoTime();

        System.out.println("Method " + method.getName() + " took " + (endTime - startTime) + " nanoseconds to execute.");

        return result;
    }
}

class TracingHandler implements InvocationHandler {
    private final Object target;

    public TracingHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Method " + method.getName() + " called with parameters: " + arrayToString(args));

        Object result = method.invoke(target, args);

        System.out.println("Method " + method.getName() + " returned: " + result);

        return result;
    }

    private String arrayToString(Object[] array) {
        StringBuilder result = new StringBuilder("[");
        for (Object obj : array) {
            result.append(obj).append(", ");
        }
        if (array.length > 0) {
            result.setLength(result.length() - 2); // Remove the trailing comma and space
        }
        result.append("]");
        return result.toString();
    }
}

public class Main5 {
    public static void main(String[] args) {
        // Profiling
        MyInterface profilingProxy = (MyInterface) Proxy.newProxyInstance(
                MyInterface.class.getClassLoader(),
                new Class[]{MyInterface.class},
                new ProfilingHandler(new MyImplementation())
        );

        System.out.println("Profiling Proxy Result: " + profilingProxy.calculate(3, 7));

        // Tracing
        MyInterface tracingProxy = (MyInterface) Proxy.newProxyInstance(
                MyInterface.class.getClassLoader(),
                new Class[]{MyInterface.class},
                new TracingHandler(new MyImplementation())
        );

        System.out.println("Tracing Proxy Result: " + tracingProxy.calculate(5, 8));
    }
}