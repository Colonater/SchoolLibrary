import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryCatalogSystem {
    private List<Book> books = new ArrayList<>();

    public static void main(String[] args) {
        LibraryCatalogSystem library = new LibraryCatalogSystem();
        library.initializeSampleData();
        library.displayWelcomeMessage();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            library.displayMenu();

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    library.searchForBook(scanner);
                    break;
                case 2:
                    library.checkoutBook(scanner);
                    break;
                case 3:
                    library.returnBook(scanner);
                    break;
                case 4:
                    System.out.println("Thank you for using the library catalog system. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    // Initialize the library with some sample data
    private void initializeSampleData() {
        books.add(new Book("Book 1", "Author 1", "1", true));
        books.add(new Book("Book 2", "Author 2", "2", false));
        books.add(new Book("The Hunger Games", "Suzanne Collins", "3", true));
        books.add(new Book("The Hobbit", "J.R.R. Tolkien", "4", true));
        books.add(new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", "5", true));
        books.add(new Book("Star Wars: A New Hope", "George Lucas", "6", false));
        books.add(new Book("Minecraft: The Official Beginner's Handbook", "Mojang Ab", "7", true));
    }

    // Display a welcome message when the program starts
    private void displayWelcomeMessage() {
        System.out.println("Welcome to the Library Catalog System!");
    }

    // Display the main menu with options
    private void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Search for a book");
        System.out.println("2. Checkout a book");
        System.out.println("3. Return a book");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    // Method to search for a book by title, author, or ISBN
    private void searchForBook(Scanner scanner) {
        System.out.println("Enter search term (title, author, or ISBN): ");
        String searchTerm = scanner.nextLine();

        boolean found = false; // To track if any books matching the search term were found

        for (Book book : books) {
            if (book.matches(searchTerm)) {
                System.out.println(book.toString());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books matching the search term were found.");
        }
    }

    // Method to handle book checkout
    private void checkoutBook(Scanner scanner) {
        System.out.println("Enter the ISBN of the book you want to check out: ");
        String isbnToCheckout = scanner.nextLine();

        boolean bookFound = false;

        for (Book book : books) {
            if (book.getIsbn().equalsIgnoreCase(isbnToCheckout)) {
                if (book.isAvailable()) {
                    // The book is available, so it can be checked out
                    book.setAvailability(false); // Mark the book as not available
                    System.out.println("Book checked out successfully.");
                } else {
                    System.out.println("This book is not available for checkout.");
                }
                bookFound = true;
                break; // No need to continue searching
            }
        }

        if (!bookFound) {
            System.out.println("Book with ISBN " + isbnToCheckout + " not found in the library.");
        }
    }

    // Method to handle book return
    private void returnBook(Scanner scanner) {
        System.out.println("Enter the ISBN of the book you want to return: ");
        String isbnToReturn = scanner.nextLine();

        boolean bookFound = false;

        for (Book book : books) {
            if (book.getIsbn().equalsIgnoreCase(isbnToReturn)) {
                if (!book.isAvailable()) {
                    // The book is not available, so it can be returned
                    book.setAvailability(true); // Mark the book as available again
                    System.out.println("Book returned successfully.");
                } else {
                    System.out.println("This book is already available in the library.");
                }
                bookFound = true;
                break; // No need to continue searching
            }
        }

        if (!bookFound) {
            System.out.println("Book with ISBN " + isbnToReturn + " not found in the library.");
        }
    }
}

class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean availability;

    public Book(String title, String author, String isbn, boolean availability) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.availability = availability;
    }

    public boolean matches(String searchTerm) {
        return title.equalsIgnoreCase(searchTerm) || author.equalsIgnoreCase(searchTerm) || isbn.equalsIgnoreCase(searchTerm);
    }

    // Getter for ISBN
    public String getIsbn() {
        return isbn;
    }

    // Getter for availability
    public boolean isAvailable() {
        return availability;
    }

    // Setter for availability
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Book Title: " + title + "\nAuthor: " + author + "\nISBN: " + isbn + "\nAvailability: " + (availability ? "Available" : "Not Available");
    }
}
