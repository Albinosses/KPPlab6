import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class LibraryRecord implements Serializable {
    private Subscriber subscriber;
    private Book book;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate actualReturnDate;

    public LibraryRecord(Subscriber subscriber, Book book, LocalDate issueDate, LocalDate dueDate) {
        this.subscriber = subscriber;
        this.book = book;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
    }

    public Subscriber getSubscriber(){
        return subscriber;
    }

    public LocalDate getActualReturnDate() {
        return actualReturnDate;
    }

    public void setActualReturnDate(LocalDate actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Book getBook() {
        return book;
    }

    @Override
    public String toString() {
        return "\nLibraryRecord{" +
                "subscriber =" + subscriber +
                ", book =" + book +
                ", issueDate =" + issueDate +
                ", dueDate =" + dueDate +
                ", actualReturnDate =" + actualReturnDate +
                '}';
    }
}
