import java.io.Serializable;
import java.util.ArrayList;

public class Subscriber implements Serializable {
    private String surname;
    private String name;
    private String fatherName;
    private String email;
    private ArrayList<Book> borrowedBooks;

    public Subscriber(String surname, String name, String fatherName, String email) {
        this.surname = surname;
        this.name = name;
        this.fatherName = fatherName;
        this.email = email;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(ArrayList<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    @Override
    public String toString() {
        return "\nSubscriber{" +
                "surname ='" + surname + '\'' +
                ", name ='" + name + '\'' +
                ", fathername ='" + fatherName + '\'' +
                ", email='" + email + '\'' +
                ", borrowedBooks=" + borrowedBooks +
                '}';
    }
}
