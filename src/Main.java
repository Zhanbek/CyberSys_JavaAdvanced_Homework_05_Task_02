import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Scanner;

class Helper {
     private static void outputParametersInfo(Parameter[] params) {
        System.out.print("(");
        int len = params.length;
        if (len > 0) {
            for (int i = 0; i < len - 1; i++) {
                System.out.print(params[i].getType().getTypeName().concat(" ").concat(params[i].getName()).concat(", "));
            }
            System.out.print(params[len - 1].getType().getTypeName().concat(" ").concat(params[len - 1].getName()));
        }
        System.out.print(")");
    }

    static void outputcClassInfo(String className) throws ClassNotFoundException {
        Class<?> cl = Class.forName(className);

        System.out.println();
        System.out.println("Конструктори класу: ");
        System.out.println();
        Constructor<?>[] constructors = cl.getDeclaredConstructors();
        if (constructors.length > 0) {
            for (Constructor<?> constructor : constructors) {
                System.out.print(constructor.getName());
                Parameter[] parameters = constructor.getParameters();
                outputParametersInfo(parameters);
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
                Parameter[] parameters = method.getParameters();
                outputParametersInfo(parameters);
                System.out.println();
            }
        } else {
            System.out.println("У класу відсутні Методи");
        }
    }
}

public class Main {
    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("Вкажіть назву класу, інформацію про який хочете отримати: ");
        String className = scanner.nextLine();
        try {
            Helper.outputcClassInfo(className);
        } catch (ClassNotFoundException e) {
            System.out.println();
            System.out.println("Помилка: інформації про клас \"" + className + "\" не знайдено!");
        }
    }
}