package repeatability;

import java.time.LocalDateTime;

public class Monthly implements Repeatability{
    @Override
    public LocalDateTime nextTime(LocalDateTime currentDateTime) {
        return currentDateTime.plusMonths(1);
    }

    @Override
    public String toString() {
        return "ежемесячная";
    }
}
