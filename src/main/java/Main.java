import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Main {
    private static final String filename = "library.ser";

    public static void main(String[] args){
        Library library = (Library) SerializationManager.deserializeObject(filename);
        Scanner scanner = new Scanner(System.in);
        int operation;

        do {
            System.out.println("Library Management System Menu:");
            System.out.println("1. Відсортувати всі книги за роком видання.");
            System.out.println("2. Створити список адрес для розсилки повідомлень для читачів, що взяли більше ніж 2 книги.");
            System.out.println("3. Перевірити, скільки користувачів взяли книги заданого автора.");
            System.out.println("4. Знайти найбільшу кількість книг, взятого читачем бібліотеки.");
            System.out.println("5. Здійснити різну розсилку 2 групам користувачів.");
            System.out.println("6. Створити список боржників на біжучу дату.");
            System.out.println("7. Додати абонента");
            System.out.println("8. Додати книгу");
            System.out.println("9. Здійснити видачу книги");
            System.out.println("10. Здійснити повернення книги");
            System.out.println("0. Вийти з програми");

            System.out.print("Введіть номер операції: ");
            operation = scanner.nextInt();
            scanner.nextLine();

            switch (operation) {
                case 1 -> {

                    System.out.println("В якому порядку (1: ascending; 2: descending): ");
                    int order = scanner.nextInt();
                    List<Book> sortedBooks = new ArrayList<>();
                    if (order == 1) {
                        sortedBooks = library.SortBooksByYear(Order.Ascending);
                    }
                    if (order == 2) {
                        sortedBooks = library.SortBooksByYear(Order.Descending);
                    }
                    System.out.println("Посортований список:");
                    System.out.println(sortedBooks);
                }
                case 2 -> {
                    var emails = library.getEmailsForReadersWithMoreThanTwoBooks();
                    System.out.println("Список електронних адерс абонентів, що взяли більше двох книг");
                    System.out.println(emails);
                }
                case 3 -> {
                    System.out.println("Введіть ім'я автора: ");
                    String author = scanner.nextLine();
                    var readersByAuthor = library.countReadersByAuthor(author);
                    System.out.println("Введений автор має: " + readersByAuthor + " читачів");
                }
                case 4 -> {
                    System.out.println("Найбільша кількість книг, взятих одним користувачем: ");
                    System.out.println(library.findMaxBooksBorrowed());
                }
                case 5 -> {
                    var newBooksNotificationEmails = library.newBooksNotificationEmails();
                    var OnTimeReturnNotificationEmails = library.OnTimeReturnNotificationEmails();
                    library.sendNotification(newBooksNotificationEmails, "Агов, тут є кілька нових книг в " +
                            "нашій бібліотеці, запрошуємо переглянути наш оновлений каталог");
                    library.sendNotification(OnTimeReturnNotificationEmails, "Дорого дня, нагадуємо, що " +
                            "скоро термін повернення книг, які ви взяли в нашій бібліотеці");
                }
                case 6 -> {
                    LocalDate currentDate = LocalDate.now();
                    var debtors = library.findDebtors(currentDate);
                    System.out.println("Поточні боржники:");
                    System.out.println(debtors);
                }
                case 7 -> {
                    System.out.println("Введіть ім'я абонента: ");
                    String name = scanner.nextLine();

                    System.out.println("Введіть прізвище абонента: ");
                    String surname = scanner.nextLine();

                    System.out.println("Введіть по-батькові абонента: ");
                    String fatherName = scanner.nextLine();

                    System.out.println("Введіть електронну адресу абонента: ");
                    String email = scanner.nextLine();

                    var newSubscriber = new Subscriber(surname, name, fatherName, email);
                    library.addSubscriber(newSubscriber);
                }
                case 8 -> {
                    System.out.println("Введіть ім'я автора: ");
                    String author = scanner.nextLine();

                    System.out.println("Введіть назву книжки: ");
                    String title = scanner.nextLine();

                    System.out.println("Введіть рік видання: ");
                    int publicationYear = scanner.nextInt();
                    scanner.nextLine();

                    var newBook = new Book(author, title, publicationYear);
                    library.addBook(newBook);
                }
                case 9 -> {
                    for (int i = 0; i < library.getAllSubscribers().size(); i++){
                        System.out.println("Index " + i + ": " + library.getAllSubscribers().get(i));
                    }
                    System.out.println("Введіть номер абонента: ");
                    int abonentIndex = scanner.nextInt();
                    scanner.nextLine();

                    for (int i = 0; i < library.getAllBooks().size(); i++){
                        System.out.println("Index " + i + ": " + library.getAllBooks().get(i));
                    }
                    System.out.println("Введіть номер книги: ");
                    int bookIndex = scanner.nextInt();
                    scanner.nextLine();

                    try {
                        library.addLibraryRecords(Administrator.issueBook(
                                library.getAllSubscribers().get(abonentIndex),
                                library.getAllBooks().get(bookIndex),
                                LocalDate.now(),
                                LocalDate.now().plusMonths(2)
                        ));
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 10 -> {
                    for (int i = 0; i < library.getAllLibraryRecords().size(); i++){
                        System.out.println("Index " + i + ": " + library.getAllLibraryRecords().get(i));
                    }
                    System.out.println("Введіть номер запису, який хочете позначити завершеним: ");
                    int recordIndex = scanner.nextInt();
                    scanner.nextLine();

                    Administrator.returnBook(library.getAllLibraryRecords().get(recordIndex), LocalDate.now());
                }
                case 0 -> {
                    System.out.println("Програма завершує роботу.");
                    SerializationManager.serializeObject(library, filename);
                }
                default -> System.out.println("Невірний номер операції. Будь ласка, спробуйте ще раз.");
            }

        } while (operation != 0);

    }
}