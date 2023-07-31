import java.io.PrintWriter;
import java.lang.annotation.*;
import java.lang.reflect.Method;

@Target(value = {ElementType.TYPE, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@interface SaveTo {
    String path();
}
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@interface Saver {
}

@SaveTo(path = "c://Users//1//Desktop//test.rtf")
class TextContainer {
    private static String str = "My name is Alex";
    @Saver
    public static void save(String path) {
        try (PrintWriter pw = new PrintWriter(path)){
            pw.println(str);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
public class Main {
    public static void main(String[] args) {
        TextContainer textContainer = new TextContainer();
        Class<?> cls = textContainer.getClass();

            if (cls.isAnnotationPresent(SaveTo.class)) {
                for (Method method : cls.getMethods()) {
                    if (method.isAnnotationPresent(Saver.class)) {

                        try {
                            method.invoke(null, cls.getAnnotation(SaveTo.class).path());
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                    }
                }
            }
        }
    }
