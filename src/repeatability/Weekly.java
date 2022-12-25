package repeatability;

import java.time.LocalDateTime;

public class Weekly implements Repeatability{
    @Override
    public LocalDateTime nextTime(LocalDateTime currentDateTime) {
        return currentDateTime.plusWeeks(1);
    }

    @Override
    public String toString() {
        return "еженедельная";
    }
}
