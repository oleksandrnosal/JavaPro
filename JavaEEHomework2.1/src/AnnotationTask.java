import java.lang.annotation.*;
import java.lang.reflect.*;
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)

@interface Test {
    int a();
    int b();
}
public class AnnotationTask {
    @Test(a = 2, b = 5)
    public void task (int a, int b){

        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Class<?> cls = AnnotationTask.class;
        Method m[]  = cls.getMethods();

        for (Method method: m){
            if (method.isAnnotationPresent(Test.class)) {
                Test annotation = method.getAnnotation(Test.class);
                int a = annotation.a();
                int b = annotation.b();

                try {
                    method.invoke(cls.newInstance(), a, b);
                } catch (IllegalAccessException e) {
                   throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
