import java.lang.reflect.Constructor;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("Вкажіть назву класу, інформацію про який хочете отримати: ");
        String className = scanner.nextLine();

        Class<?> cl = Class.forName(className);

        System.out.println();

        Constructor<?>[] constructors = cl.getDeclaredConstructors();
        if (constructors.length > 0) {
            for (Constructor<?> constructor : constructors) {
                System.out.print(constructor.getName());
                Class<?>[] paramTypes = constructor.getParameterTypes();
                System.out.print("(");
                if (paramTypes.length > 0) {
                    for (int i = 0; i < paramTypes.length - 1; i++) {
                        System.out.print(paramTypes[i].getName().concat(", "));
                    }
                    System.out.print(paramTypes[paramTypes.length - 1].getName().concat(")"));
                }

                System.out.println();
            }
        } else {
            System.out.println("У класу відсутні конструктори");
        }



    }
}