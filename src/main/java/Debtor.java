import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Debtor {
    private Subscriber subscriber;
    private Map<LibraryRecord, Integer> overdueRecords;
    private LocalDate currentDate;

    public Debtor(Subscriber subscriber, List<LibraryRecord> overdueRecords, LocalDate currentDate) {
        this.subscriber = subscriber;
        this.overdueRecords = calculateOverdueDays(overdueRecords, currentDate);
        this.currentDate = currentDate;
    }

    private Map<LibraryRecord, Integer> calculateOverdueDays(List<LibraryRecord> records, LocalDate currentDate) {
        Map<LibraryRecord, Integer> overdueMap = new HashMap<>();
        for (LibraryRecord record : records) {
            var diff = ChronoUnit.DAYS.between(record.getDueDate(), currentDate);

            overdueMap.put(record, Math.toIntExact(diff));
        }
        return overdueMap;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public Map<LibraryRecord, Integer> getOverdueRecords() {
        return overdueRecords;
    }

    public void setOverdueRecords(Map<LibraryRecord, Integer> overdueRecords) {
        this.overdueRecords = overdueRecords;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nDebtor: ").append(subscriber).append("\n");
        sb.append("Overdue Records:\n");

        for (Map.Entry<LibraryRecord, Integer> entry : overdueRecords.entrySet()) {
            LibraryRecord record = entry.getKey();
            int daysOverdue = entry.getValue();

            sb.append("\tBook: ").append(record.getBook()).append("\n");
            sb.append("\tDue Date: ").append(record.getDueDate()).append("\n");
            sb.append("\tDays Overdue: ").append(daysOverdue).append("\n");
        }

        return sb.toString();
    }
}
