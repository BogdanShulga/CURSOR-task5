package main.java;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        LibraryHandler libraryHandler = new LibraryHandler();

        int action;
        Scanner in = new Scanner(System.in);
        boolean stop = false;

        System.out.println("Hi! It is a library.");
        while (!stop) {
            System.out.println("Pleas, choose a type of operation:");
            System.out.println("1 - to choose the book");
            System.out.println("2 - to show a list of dates in which you took the books");
            System.out.println("3 - to show a list of books you have taken all the time");
            System.out.println("4 - to find the title of the book for a given date");
            System.out.println("5 - to quit from the library");

            try {
                action = in.nextInt();
                System.out.println("You entered " + action);
            } catch (java.util.InputMismatchException ex) {
                System.out.println("You entered incorrect data!");
                in.nextLine();
                continue;
            }

            if (action == 1) {
                libraryHandler.chooseBook();
            } else if (action == 2) {
                libraryHandler.showBooks();
            } else if (action == 3) {
                libraryHandler.showDates();
            } else if (action == 4) {
                libraryHandler.findBook();
            } else if (action == 5) {
                stop = true;
            } else {
                System.out.println("You entered incorrect data!");
            }
        }
        System.out.println("Goodbye!");
        System.exit(0);

    }
}