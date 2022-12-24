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
            LocalDateTime currentDT=task.getDateTime();
            LocalDateTime nextDT = task.getRepeatability().nextT(currentDT);
            if (nextDT == null || currentDT.equals(day)) {
                tasksByDay.add(task);
                continue;
            }
            do {
                if (nextDT.toLocalDate().equals(day)) {
                    tasksByDay.add(task);
                    break;
                }
                nextDT = task.getRepeatability().nextT(currentDT);
            } while (nextDT.toLocalDate().isBefore(day));
        }
        return tasksByDay;
    }
    public static void deleteById(int id) throws TaskNotFoundException {
        if (TASKS.remove(id) == null) {
            throw new TaskNotFoundException(id);
        }
    }
}
