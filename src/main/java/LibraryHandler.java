package main.java;

import java.time.LocalDate;
import java.util.*;

class LibraryHandler {

    private Map<LocalDate, String> userBookMap = new HashMap<>();
    private List<String> bookList = initCatalogList();
    private long l = 1L;

    void chooseBook() {
        System.out.println("============================================================================");
        if (bookList.isEmpty()) {
            System.out.println("You have already taken all books from the library!!!");
            return;
        }
        int i = 1;
        System.out.println("Please, choose a number of a book you like:");
        for (String s : bookList) {
            System.out.println(i + ". " + s);
            i++;
        }
        int n = 0;
        boolean stop = false;
        Scanner scanner = new Scanner(System.in);
        while (!stop) {
            try {
                n = scanner.nextInt();
                System.out.println();
            } catch (NoSuchElementException | IllegalStateException ex) {
                System.out.println("You entered incorrect data!");
                scanner.nextLine();
                continue;
            }
            if (n <= 0 && n > i) {
                System.out.println("You entered incorrect data!");
                continue;
            }
            stop = true;
        }
        System.out.println("You have chosen the book number " + n + ". " + bookList.get(n - 1));

        LocalDate now = LocalDate.now();
        if (userBookMap.containsKey(now)) { //here I make different keys in order not to overwrite the old values
            String s = userBookMap.get(now);
            userBookMap.put(now.minusDays(l), s);
            l++;
            userBookMap.put(now, bookList.get(n - 1));
        } else {
            userBookMap.put(now, bookList.get(n - 1));
        }
        bookList.remove(n - 1);
    }

    void showBooks() {
        if (userBookMap.isEmpty()) {
            System.out.println("You have not taken any books yet!!!");
            return;
        }
        System.out.println("A list of books you have taken all the time:");
        Collection<String> userBooks = userBookMap.values();
        for (String s : userBooks) {
            System.out.println(s);
        }
    }

    void showDates() {
        if (userBookMap.isEmpty()) {
            System.out.println("You have not taken any books yet!!!");
            return;
        }
        System.out.println("A list of dates in which you took the books:");
        Set<LocalDate> userLocalDates = userBookMap.keySet();
        for (LocalDate ld : userLocalDates) {
            System.out.println(ld);
        }
    }

    void findBook() {
        System.out.println("============================================================================");
        if (userBookMap.isEmpty()) {
            System.out.println("You have not taken any books yet!!!");
            return;
        }
        LocalDate n;
        boolean stop = false;

        Scanner scanner = new Scanner(System.in);
        while (!stop) {
            System.out.println("Please enter a date (for example: " + LocalDate.now() + "):");
            try {
                n = LocalDate.parse(scanner.next());
                System.out.println();
            } catch (Exception ex) {
                System.out.println("You entered incorrect data!");
                scanner.nextLine();
                continue;
            }

            String userBookMapOrDefault = userBookMap.getOrDefault(n, "For this date there were no books taken");
            System.out.println("On " + n + "th, you took the book\n" + userBookMapOrDefault);
            stop = true;
        }
    }

    private List<String> initCatalogList() {
        List<String> bookList = new ArrayList<>();
        bookList.add("\"Java: The Complete Reference, Ninth Edition\" by Herbert Schildt");
        bookList.add("\"Head First Design Patterns: A Brain-Friendly Guide\" by Eric Freeman , Bert Bates , et al.");
        bookList.add("\"Clean Code: A Handbook of Agile Software Craftsmanship\" by Robert C. Martin");
        bookList.add("\"Thinking in Java\" by Bruce Eckel");
        bookList.add("\"Effective Java\" by Joshua Bloch");
        return bookList;
    }
}
