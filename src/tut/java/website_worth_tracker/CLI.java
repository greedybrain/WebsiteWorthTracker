package tut.java.website_worth_tracker;

import java.util.Scanner;

public class CLI {
    public Scanner scanner = new Scanner(System.in);

    public String getUserQuery() {
        System.out.printf("What site do you want to search? ");
        return scanner.nextLine();
    }
}
