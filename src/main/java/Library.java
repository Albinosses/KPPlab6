import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Library implements Serializable {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Subscriber> subscribers = new ArrayList<>();
    private ArrayList<LibraryRecord> libraryRecords = new ArrayList<>();

    public void addBook(Book book){
        this.books.add(book);
    }

    public void addSubscriber(Subscriber subscriber){
        this.subscribers.add(subscriber);
    }

    public void addLibraryRecords(LibraryRecord libraryRecord){
        this.libraryRecords.add(libraryRecord);
    }

    public ArrayList<Book> getAllBooks(){
        return books;
    }

    public ArrayList<Subscriber> getAllSubscribers(){
        return subscribers;
    }

    public ArrayList<LibraryRecord> getAllLibraryRecords(){
        return libraryRecords;
    }

    public List<Book> SortBooksByYear(Order order){
        Comparator<Book> comparator = Comparator.comparingInt(Book::getPublicationYear);

        if (order == Order.Descending){
            comparator = comparator.reversed();
        }

        return books.stream().sorted(comparator).collect(Collectors.toList());
    }

    public List<String> getEmailsForReadersWithMoreThanTwoBooks() {
        return subscribers.stream()
                .filter(reader -> reader.getBorrowedBooks().size() > 2)
                .map(Subscriber::getEmail)
                .collect(Collectors.toList());
    }

    public long countReadersByAuthor(String author) {
        return subscribers.stream()
                .filter(reader -> reader.getBorrowedBooks().stream()
                        .anyMatch(book -> book.getAuthor().equals(author)))
                .count();
    }

    public int findMaxBooksBorrowed() {
        return subscribers.stream()
                .mapToInt(reader -> reader.getBorrowedBooks().size())
                .max()
                .orElse(0);
    }

    public List<String> newBooksNotificationEmails() {
        return subscribers.stream()
                .filter(subscriber -> subscriber.getBorrowedBooks().size() < 2)
                .map(Subscriber::getEmail)
                .collect(Collectors.toList());

    }

    public List<String> OnTimeReturnNotificationEmails() {
        return subscribers.stream()
                .filter(subscriber -> subscriber.getBorrowedBooks().size() >= 2)
                .map(Subscriber::getEmail)
                .collect(Collectors.toList());
    }

    public void sendNotification(List<String> emails, String message) {
        System.out.println("Sending notification to the following emails:");
        for (String email : emails) {
            System.out.println(email);
        }
        System.out.println(message);
        System.out.println("\nNotification sent successfully.");
    }

    public List<Debtor> findDebtors(LocalDate currentDate) {
        return subscribers.stream()
                .filter(subscriber ->
                        libraryRecords.stream()
                                .anyMatch(record ->
                                        record.getDueDate().isBefore(currentDate) &&
                                                record.getSubscriber().equals(subscriber)
                                )
                )
                .map(subscriber ->
                        new Debtor(
                                subscriber,
                                libraryRecords.stream()
                                        .filter(record ->
                                                record.getDueDate().isBefore(currentDate) &&
                                                        record.getSubscriber().equals(subscriber)
                                        )
                                        .toList(),
                                currentDate
                        )
                )
                .collect(Collectors.toList());
    }
}
