package repeatability;

import java.time.LocalDateTime;

public interface Repeatability {
    LocalDateTime nextT(LocalDateTime currentDT);

}
