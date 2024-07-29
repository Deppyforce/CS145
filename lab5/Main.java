/**
 * Tanagorn Suksamran
 * CS145 Lab 5
 * Phone Book Management Program
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        PhonebookManager[] phoneBooks = {new PhonebookManager(), new PhonebookManager()};
        String[] phoneBooksTitle = {"Bellingham", "Seattle"};
        int phoneBookIndex;
        PhonebookManager phoneBook;

        phoneBookIndex = phoneBookSelection(scanner, phoneBooks.length) - 1;
        phoneBook = phoneBooks[phoneBookIndex];

        do {
            System.out.println();
            System.out.printf("Active phone book: %s\n", phoneBooksTitle[phoneBookIndex]);
            mainMenu();
            int choice = menuInput(scanner);
            switch (choice) {
                case 1:
                    phoneBook.display();
                    break;
                case 2:
                    addEntry(scanner, phoneBook);
                    break;
                case 3:
                    removeEntry(scanner, phoneBook);
                    break;
                case 4:
                    modifyEntry(scanner, phoneBook);
                    break;
                case 5:
                    moveEntry(scanner, phoneBook, phoneBooks);
                    break;
                case 6:
                    phoneBookIndex = phoneBookSelection(scanner, phoneBooks.length) - 1;
                    phoneBook = phoneBooks[phoneBookIndex];
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    System.out.println("Unknown command.");
            }
        } while (running);
        System.out.print("Exiting program...");
    }

    /** display phonebook selection menu */
    private static void phoneBookSelectionMenu() {
        System.out.println("Select a phonebook:");
        System.out.println("[1] Bellingham");
        System.out.println("[2] Seattle");
    }

    /** manage user phonebook selection */
    private static int phoneBookSelection(Scanner scanner, int phoneBooksCount) {
        phoneBookSelectionMenu();
        int choice = menuInput(scanner);

        // default to first option if invalid user choice
        if (choice == -1 || choice > phoneBooksCount) {
            return 1;
        }

        return choice;
    }

    /** return user choice */
    private static int menuInput(Scanner scanner) {
        while (true) {
            System.out.print(">> ");
            int choice;
            
            // set to -1 for invalid inputs
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                choice = -1;
            }

            scanner.nextLine();
            return choice;
        }
    }

    /** display main menu */
    private static void mainMenu() {
        System.out.println("Select a command:");
        System.out.println("\t[1] Display phone book");
        System.out.println("\t[2] Add an entry");
        System.out.println("\t[3] Remove an entry");
        System.out.println("\t[4] Modify entry");
        System.out.println("\t[5] Move entry");
        System.out.println("\t[6] Switch active phonebook");
        System.out.println("\t[7] Exit program");
    }

    /** ask user for info then add new node */
    private static void addEntry(Scanner scanner, PhonebookManager phoneBook) {
        String[] fields = {"first name", "last name", "address", "city", "phone number"};
        String[] d = new String[fields.length];

        // collect data for each field
        for (int i = 0; i < fields.length; i++) {
            d[i] = input(scanner, fields[i]);
        }

        phoneBook.add(d[0], d[1], d[2], d[3], d[4]);
    }

    /** remove node with given fName and lName */
    private static void removeEntry(Scanner scanner, PhonebookManager phoneBook) {
        // ask user for fName and lName
        String fName = input(scanner, "first name");
        String lName = input(scanner, "last name");

        // delete node if it exists
        int index = phoneBook.seek(fName, lName);
        if (index == -1) {
            System.out.println("Entry not found.");
        } else {
            phoneBook.remove(index);
            System.out.println("Entry removed.");
        }
    }

    /** ask for input with prompt, return scanner input */
    private static String input(Scanner scanner, String prompt) {
        System.out.printf("Enter %s: ", prompt);
        return scanner.nextLine();
    }

    /** modify entries */
    private static void modifyEntry(Scanner scanner, PhonebookManager phoneBook) {
        // ask user for fName and lName
        String fName = input(scanner, "first name");
        String lName = input(scanner, "last name");

        // abort if node doesnt exist
        int index = phoneBook.seek(fName, lName);
        if (index == -1) {
            System.out.println("Entry not found.");
            return;
        }

        System.out.println("Entry found. Displaying current information...");

        // modify phase (keep running until user wants to stop for rapid editing)
        boolean running = true;
        do {
            phoneBook.display(index);
            System.out.print("\n\n");
            modifyMenu();
            int choice = menuInput(scanner);
            switch (choice) {
                case 1:
                    phoneBook.modifyFName(index, input(scanner, "new first name"));
                    break;
                case 2:
                    phoneBook.modifyLName(index, input(scanner, "new last name"));
                    break;
                case 3:
                    phoneBook.modifyAddress(index, input(scanner, "new address"));
                    break;
                case 4:
                    phoneBook.modifyCity(index, input(scanner, "new city"));
                    break;
                case 5:
                    phoneBook.modifyPhoneNumber(index, input(scanner, "new phone number"));
                    break;
                case 6:
                    System.out.println("Aborting...");
                    running = false;
                    break;
                default:
                    System.out.println("Unknown command. Returning to main menu...");
                    return;
            }

            System.out.println("Entry successfully modified.");
        } while (running);
    }

    /** helper function to display modifying field selection menu */
    private static void modifyMenu() {
        System.out.println("Select field to modify:");
        System.out.println("\t[1] First name");
        System.out.println("\t[2] Last name");
        System.out.println("\t[3] Address");
        System.out.println("\t[4] City");
        System.out.println("\t[5] Phone Number");
        System.out.println("\t[6] Cancel");
    }

    /** move entry to another phonebook */
    private static void moveEntry(Scanner scanner, PhonebookManager phoneBook, PhonebookManager[] phoneBooks) {

        // ask user for fName and lName
        String fName = input(scanner, "first name");
        String lName = input(scanner, "last name");

        // abort if node doesnt exist
        int entryIndex = phoneBook.seek(fName, lName);
        if (entryIndex == -1) {
            System.out.println("Entry not found.");
            return;
        }

        System.out.println("Entry found. Displaying current information...");
        phoneBook.display(entryIndex);
        System.out.print("\n\n");

        // ask which phone book to move to, remove node from old phonebook, add node to target phonebook
        int newPhoneBookIndex = phoneBookSelection(scanner, phoneBooks.length) - 1;
        PhonebookNode rNode = phoneBook.remove(entryIndex);
        phoneBooks[newPhoneBookIndex].add(rNode.fName, rNode.lName, rNode.address, rNode.city, rNode.phoneNumber);

        System.out.println("Entry successfully moved.");
    }
}
