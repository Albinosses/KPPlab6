import java.time.LocalDate;
import java.util.Date;

public class Administrator {
    public static LibraryRecord issueBook(Subscriber reader, Book book, LocalDate issueDate, LocalDate dueDate) {
        var borrowedBooks = reader.getBorrowedBooks();
        borrowedBooks.add(book);
        reader.setBorrowedBooks(borrowedBooks);
        return new LibraryRecord(reader, book, issueDate, dueDate);
    }

    public static void returnBook(LibraryRecord record, LocalDate actualReturnDate) {
        var book = record.getBook();
        var subscriber = record.getSubscriber();
        var borrowedBooks = subscriber.getBorrowedBooks();
        borrowedBooks.remove(book);
        subscriber.setBorrowedBooks(borrowedBooks);
        record.setActualReturnDate(actualReturnDate);
    }
}
