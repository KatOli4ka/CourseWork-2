package repeatability;

import java.time.LocalDateTime;

public class Daily implements Repeatability{
    @Override
    public LocalDateTime nextT(LocalDateTime currentDT) {
        return currentDT.plusDays(1);
    }

    @Override
    public String toString() {
        return "ежедневная";
    }
}
