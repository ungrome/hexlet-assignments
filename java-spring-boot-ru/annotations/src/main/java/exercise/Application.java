package exercise;

import exercise.model.Address;
import exercise.annotation.Inspect;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.lang.reflect.Method;

public class Application {
    public static void main(String[] args) {
        var address = new Address("London", 12345678);

        // BEGIN
        Class<?> addressClass = address.getClass();
        for (Method method:  addressClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Inspect.class)) {
                String methodName = method.getName();
                String returnType = method.getReturnType().getSimpleName();
                System.out.println("Method " + methodName + " returns a value of type " + returnType);
            }
        }
        // END
    }
}
