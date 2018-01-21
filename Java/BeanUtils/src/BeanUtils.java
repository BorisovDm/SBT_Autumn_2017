import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class BeanUtils {
    /*
    * Определим геттер:
    * Геттер - это метод, имя которого начинается с "get",
    * он не принимает аргументы
    * и обязательно возвращает какое-то значение.
    */
    private static boolean isGetter(Method method) {
        if (!method.getName().startsWith("get")) {
            return false;
        }
        if (method.getParameterTypes().length != 0) {
            return false;
        }
        if (void.class.equals(method.getReturnType())) {
            return false;
        }
        return true;
    }

    /*
    * Определим сеттер:
    * Сеттер - это метод, имя которого начинается с "set",
    * и принимает 1 аргумент.
    */
    private static boolean isSetter(Method method) {
        if (!method.getName().startsWith("set")) {
            return false;
        }
        if (method.getParameterTypes().length != 1) {
            return false;
        }
        return true;
    }

    private static boolean checkCompatibility(Class to, Class from) {
        while (from != null) {
            if (to.equals(from)) return true;
            from = from.getSuperclass();
        }
        return false;
    }

    /*
    * Scans object "from" for all getters. If object "to"
    * contains correspondent setter, it will invoke it
    * to set property value for "to" which equals to the property
    * of "from".
    *
    * The type in setter should be compatible to the value returned
    * by getter (if not, no invocation performed).
    * Compatible means that parameter type in setter should
    * be the same or be superclass of the return type of the getter.
    *
    * The method takes care only about public methods.
    *
    * @param to Object which properties will be set.
    * @param from Object which properties will be used to get values.
    */
    public static void assign(Object to, Object from) {
        List<Method> getMethods = new ArrayList<>();
        for (Method method: from.getClass().getMethods()) {
            if(isGetter(method)) {
                getMethods.add(method);
            }
        }

        List<Method> setMethods = new ArrayList<>();
        for (Method method: to.getClass().getMethods()) {
            if(isSetter(method)) {
                setMethods.add(method);
            }
        }

        for (Method setMethod: setMethods) {
            String setMethodName = setMethod.getName().substring(3);
            for (Method getMethod: getMethods) {
                if (setMethodName.equals(getMethod.getName().substring(3)) &&
                        checkCompatibility(setMethod.getParameterTypes()[0], getMethod.getReturnType())) {
                    try {
                        setMethod.invoke(to, getMethod.invoke(from));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                    break;
                }
            }
        }
    }
}
