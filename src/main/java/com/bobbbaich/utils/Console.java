package com.bobbbaich.utils;

import com.bobbbaich.handler.BookHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * Created by Bohdan Pohotilyi on 13.09.2016.
 */
public class Console {
    public static void readToStart() {
        System.out.println("Hello, Reader!\n" + "In our library you can:" + "\t\n - 'remove' existing book" + "\t\n - 'add' new book" + "\t\n - 'update' existing book " + "\t\n - get 'all' books.");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter what you want to do: ");
            String methodName = scanner.nextLine();
            checkForExit(methodName);

            try {
                invokeMethod(methodName);
            } catch (NoSuchMethodException e) {
                System.out.println("Invalid command!\nUse one of this command: \n'add', 'all', 'remove', 'edit', 'exit'");
            } catch (InvocationTargetException e) {
                System.out.println("You entered invalid value! Try again.");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }

    public static String getBookParam(String param) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter book " + param + ": ");
        String inputValue = scanner.nextLine();
        return inputValue;
    }

    private static void checkForExit(String methodName) {
        if ("exit".equals(methodName)) {
            System.out.println("Exit");
            System.exit(0);
        }
    }

    private static void invokeMethod(String methodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        BookHandler controller = new BookHandler();
        Method method = controller.getClass().getMethod(methodName);
        method.invoke(controller);
    }
}
