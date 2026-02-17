import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("Вкажіть назву класу, інформацію про який хочете отримати: ");
        String className = scanner.nextLine();

        Class<?> cl = Class.forName(className);

        System.out.println();
        System.out.println("Конструктори класу: ");
        System.out.println();
        Constructor<?>[] constructors = cl.getDeclaredConstructors();
        if (constructors.length > 0) {
            for (Constructor<?> constructor : constructors) {
                System.out.print(constructor.getName());
                Class<?>[] paramTypes = constructor.getParameterTypes();
                System.out.print("(");

                if (paramTypes.length > 0) {
                    for (int i = 0; i < paramTypes.length - 1; i++) {
                        System.out.print(paramTypes[i].getTypeName().concat(", "));
                    }
                    System.out.print(paramTypes[paramTypes.length - 1].getTypeName());
                }
                System.out.print(")");
                System.out.println();
            }
        } else {
            System.out.println("У класу відсутні конструктори");
        }

        // Поля класу
        System.out.println();
        System.out.println();
        System.out.println("Поля класу: ");
        System.out.println();
        Field[] fields = cl.getDeclaredFields();
        Arrays.sort(fields, (f1, f2)-> f1.getType().getTypeName().compareTo(f2.getType().getTypeName()));

        if (fields.length > 0) {
            for  (Field field : fields) {
                System.out.println(field.getType().getTypeName() + " " + field.getName());
            }
        } else {
            System.out.println("У клас відсутні поля");
        }

        System.out.println();
        System.out.println();
        System.out.println("Методи класу: ");
        System.out.println();
        Method[] methods = cl.getDeclaredMethods();
        Arrays.sort(methods, (m1, m2)-> m1.getName().compareTo(m2.getName()));

        if (methods.length > 0) {
            for (Method method : methods) {
                System.out.print(method.getReturnType().getTypeName() + " " + method.getName());

                Class<?>[] paramTypes = method.getParameterTypes();
                System.out.print("(");

                if (paramTypes.length > 0) {
                    for (int i = 0; i < paramTypes.length - 1; i++) {
                        System.out.print(paramTypes[i].getTypeName().concat(", "));
                    }
                    System.out.print(paramTypes[paramTypes.length - 1].getTypeName());
                }
                System.out.print(")");
                System.out.println();
            }
        } else {
            System.out.println("У класу відсутні Методи");
        }

    }
}