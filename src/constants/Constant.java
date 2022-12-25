package constants;

import java.time.format.DateTimeFormatter;

public class Constant {
    public static final DateTimeFormatter DATE_TIME_FORMATTER=DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    public static final DateTimeFormatter DATE_FORMATTER=DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private Constant() {

    }
}
