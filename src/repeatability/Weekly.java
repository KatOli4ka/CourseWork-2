package repeatability;

import java.time.LocalDateTime;

public class Weekly implements Repeatability{
    @Override
    public LocalDateTime nextT(LocalDateTime currentDT) {
        return currentDT.plusWeeks(1);
    }

    @Override
    public String toString() {
        return "еженедельная";
    }
}
