package repeatability;

import repeatability.Repeatability;

import java.time.LocalDateTime;

public class Once implements Repeatability {

    @Override
    public LocalDateTime nextT(LocalDateTime currentDT) {
        return null;
    }

    @Override
    public String toString() {
        return "единоразовая";
    }
}
