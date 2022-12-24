package task;

import exception.WrongTaskParameterException;
import repeatability.Once;
import repeatability.Repeatability;

import java.time.LocalDateTime;

public class Task {
    private static int idCount=1;
    private final int id;
    private String title;
    private String descriptionTask;
    private Type type;
    private LocalDateTime dateTime;
    private Repeatability repeatability;

    public Task(String title, String descriptionTask, Type type, LocalDateTime dateTime,
                Repeatability repeatability) throws WrongTaskParameterException {
        id=idCount++;
        setTitle(title);
        setDescriptionTask(descriptionTask);
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

    public String getDescriptionTask() {
        return descriptionTask;
    }

    public void setDescriptionTask(String descriptionTask) throws WrongTaskParameterException {
        if (descriptionTask == null || descriptionTask.isEmpty()) {
            throw new WrongTaskParameterException("описание задачи");
        }
        this.descriptionTask = descriptionTask;
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
}
