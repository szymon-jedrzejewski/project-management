package pl.ttpsc.javaupdate.project.view;

import java.util.Scanner;

public interface View {
    default void info(String msg) {
        System.out.println(msg);
    }

    default void error(String msg) {
        System.out.println(msg);
    }

    default String getString(String message) {
        System.out.print(message);
        return new Scanner(System.in).nextLine();
    }
    default int getInt(String message) {
        System.out.print(message);
        return new Scanner(System.in).nextInt();
    }
}