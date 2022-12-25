package task;

import constants.Constant;
import exception.WrongTaskParameterException;
import repeatability.Once;
import repeatability.Repeatability;

import java.time.LocalDateTime;

public class Task {
    private static int idCount=1;
    private final int id;
    private String title;
    private String description;
    private Type type;
    private LocalDateTime dateTime;
    private Repeatability repeatability;

    public Task(String title, String description, Type type, LocalDateTime dateTime,
                Repeatability repeatability) throws WrongTaskParameterException {
        id=idCount++;
        setTitle(title);
        setDescription(description);
        setType(type);
        setDateTime(dateTime);
        setRepeatability(repeatability);
    }

    public int getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws WrongTaskParameterException {
        if (title == null || title.isEmpty()) {
            throw new WrongTaskParameterException("заголовок задачи");
        }
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws WrongTaskParameterException {
        if (description == null || description.isEmpty()) {
            throw new WrongTaskParameterException("описание задачи");
        }
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        if (type == null){
            type=type.PERSONAL;
        }
        this.type = type;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) throws WrongTaskParameterException {
        if (dateTime == null) {
            throw new WrongTaskParameterException("дата и время задачи");
        }
        this.dateTime = dateTime;
    }
    public Repeatability getRepeatability() {
        if (repeatability == null){
            repeatability=new Once();
        }
        return repeatability;
    }
    public void setRepeatability(Repeatability repeatability) {
        this.repeatability = repeatability;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("id: ").append(id).append("\n")
                .append("название: ").append("\"").append(title).append("\"").append("\n")
                .append("описание: ").append("\"").append(description).append("\"").append("\n")
                .append("тип: ").append(type==Type.PERSONAL ? "личная" : "рабочая").append("\n")
                .append("дата и время: ").append(dateTime.format(Constant.DATE_TIME_FORMATTER)).append("\n")
                .append("повторяемость: ").append(repeatability).append("\n");
        return stringBuilder.toString();
    }
}
