package repeatability;

import java.time.LocalDateTime;

public class Monthly implements Repeatability{
    @Override
    public LocalDateTime nextT(LocalDateTime currentDT) {
        return currentDT.plusMonths(1);
    }

    @Override
    public String toString() {
        return "ежемесячная";
    }
}
