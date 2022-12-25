package repeatability;

import java.time.LocalDateTime;

public interface Repeatability {
    LocalDateTime nextTime(LocalDateTime currentDateTime);

}
