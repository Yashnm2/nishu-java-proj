package view;

import java.util.Scanner;

public abstract class BaseView {
    protected final Scanner scanner;

    protected BaseView(Scanner scanner) {
        this.scanner = scanner;
    }

    protected String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    protected int parseInt(String s, int fallback) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return fallback;
        }
    }
}
