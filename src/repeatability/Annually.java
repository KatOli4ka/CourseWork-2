package repeatability;

import java.time.LocalDateTime;

public class Annually implements Repeatability{
    @Override
    public LocalDateTime nextT(LocalDateTime currentDT) {
        return currentDT.plusYears(1);
    }
}
