import repeatability.Repeatability;

import java.time.LocalDateTime;

public class Task {
    private static int idCount=1;
    private int id;
    private String title;
    private String descriptionTask;
    private Type taskType;
    private LocalDateTime dateTime;
    private Repeatability repeatability;

    public Task(String title, String descriptionTask, Type taskType, LocalDateTime dateTime,
                Repeatability repeatability) {
        setTitle(title);
        setDescriptionTask(descriptionTask);
        setTaskType(taskType);
        setDateTime(dateTime);
        setRepeatability(repeatability);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescriptionTask() {
        return descriptionTask;
    }

    public void setDescriptionTask(String descriptionTask) {
        this.descriptionTask = descriptionTask;
    }

    public Type getTaskType() {
        return taskType;
    }

    public void setTaskType(Type taskType) {
        this.taskType = taskType;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Repeatability getRepeatability() {
        return repeatability;
    }

    public void setRepeatability(Repeatability repeatability) {
        this.repeatability = repeatability;
    }
}
