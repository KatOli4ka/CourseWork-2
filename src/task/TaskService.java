package task;

import exception.TaskNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TaskService {
    private static final Map<Integer,Task> TASKS=new HashMap<>();

    public TaskService() {
    }

    public static void add(Task task) {
        TASKS.put(task.getId(),task);
    }

    public static Collection<Task> getTaskByDay(LocalDate day) {
        Collection<Task> tasksByDay=new ArrayList<>();
        Collection<Task> allTasks=TASKS.values();
        for (Task task : allTasks) {
            LocalDateTime currentDateTime=task.getDateTime();
            if (currentDateTime.toLocalDate().equals(day)) {
                tasksByDay.add(task);
                continue;
            }
            LocalDateTime nextDateTime=currentDateTime;
            do {
                nextDateTime=task.getRepeatability().nextTime(nextDateTime);
                if (nextDateTime==null) {
                    break;
                }
                if (nextDateTime.toLocalDate().equals(day)) {
                    tasksByDay.add(task);
                    break;
                }
            } while (nextDateTime.toLocalDate().isBefore(day));
        }
        return tasksByDay;
    }
    public static void deleteById(int id) throws TaskNotFoundException {
        if (TASKS.remove(id) == null) {
            throw new TaskNotFoundException(id);
        }
    }
}
