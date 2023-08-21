import constants.Constant;
import exception.TaskNotFoundException;
import exception.WrongTaskParameterException;
import repeatability.*;
import task.Task;
import task.TaskService;
import task.Type;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final Pattern DATE_TIME_PETTERN=Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4} \\d{2}:\\d{2}");
    private static final Pattern DATE_PETTERN=Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}");
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        label:
        while (true) {
            printMenu();
            System.out.print("Выберите пункт меню: ");
            if (scanner.hasNextInt()) {
                int menu = scanner.nextInt();
                switch (menu) {
                    case 1:
                        addTask(scanner);
                        break;
                    case 2:
                        deleteTask(scanner);
                        break;
                    case 3:
                        printTasksByDay(scanner);
                        break;
                    case 0:
                        break label;
                }
            } else {
                scanner.next();
                System.out.println("Выберите пункт меню из списка!");
            }
        }
    }
    private static void printTasksByDay(Scanner scanner) {
        do {
            System.out.println("Введите дату в формате \""+ LocalDate.now().format(Constant.DATE_FORMATTER)+"\";");
            if (scanner.hasNext(DATE_PETTERN)) {
                   LocalDate day= checkDate(scanner.next(DATE_PETTERN));
                if (day == null) {
                    System.out.println("Некорректный формат даты!");
                    continue;
                }
                Collection<Task> tasksByDay = TaskService.getTaskByDay(day);
                if (tasksByDay.isEmpty()) {
                    System.out.println("Задачи на " + day.format(Constant.DATE_FORMATTER) + " не найдены!");
                } else {
                    System.out.println("Задачи на "+day.format(Constant.DATE_FORMATTER)+": ");
                    for (Task task : tasksByDay) {
                        System.out.println(task);
                    }
                }
                break;
            } else {
                scanner.next();
            }
        } while (true);
    }

    private static void deleteTask(Scanner scanner) {
        try {
            do {
                System.out.println("Введите id задачи: ");
                if (scanner.hasNextInt()) {
                    int id= scanner.nextInt();
                    TaskService.deleteById(id);
                    System.out.println("Задача с id - "+id+" удалена!");
                    break;
                } else {
                    scanner.next();
                }
            } while (true);
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());;
        }
    }

    private static void addTask(Scanner scanner) {
        try {
            System.out.print("Введите название задачи: ");
            String title = scanner.next();
            System.out.print("Введите описание задачи: ");
            String description = scanner.next();
            Type type=inputType(scanner);
            LocalDateTime dateTime=inputDT(scanner);
            Repeatability repeatability = inputR(scanner);
            Task task=new Task(title,description,type,dateTime,repeatability);
            TaskService.add(task);
            System.out.println("Задача добавлена!");
            System.out.println(task);
        } catch (WrongTaskParameterException e) {
            System.out.println(e.getMessage());;
        }

    }

    private static Type inputType(Scanner scanner) {
        Type type;
        do {
            System.out.println("Введите тип задачи:\n1. Личная\n2. Рабочая\nТип задачи: ");
            if (scanner.hasNextInt()) {
                int number= scanner.nextInt();
               if (number!= 1 && number != 2) {
                    System.out.println("Введите 1 или 2");
                    continue;
                }
                if (number == 1) {
                    type = Type.PERSONAL;
                } else {
                    type = Type.WORK;
                }
                break;
            } else {
                scanner.next();
            }
        } while (true);
        return type;
    }
    private static LocalDateTime inputDT(Scanner scanner) {
        LocalDateTime dateTime;
        do {
            System.out.println("Введите дату и время задачи в формате \""+LocalDateTime.now().format(Constant.DATE_TIME_FORMATTER)+"\": ");
            if (scanner.hasNext(DATE_TIME_PETTERN)) {
               dateTime=checkDateTime(scanner.next(DATE_TIME_PETTERN));
                if (dateTime==null) {
                    System.out.println("Некорректный формат даты и времени!");
                    continue;
                }
                break;
            } else {
                scanner.next();
            }
        } while (true);

        return dateTime;
    }

    private static LocalDateTime checkDateTime(String dateTime) {
        try {
            return LocalDateTime.parse(dateTime, Constant.DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
    private static LocalDate checkDate(String date) {
        try {
            return LocalDate.parse(date, Constant.DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
    private static Repeatability inputR (Scanner scanner) {
        Repeatability repeatability;
        do {
            System.out.println("Введите тип повторяемости задачи:\n1. Однократная\n2. Ежедневная\n3. Еженедельная" +
                    "\n4. Ежемесячная\n5. Ежегодная: ");
            if (scanner.hasNextInt()) {
                int number= scanner.nextInt();
                if (number< 1 || number >5) {
                    System.out.println("Введите цифру от 1 до 5");
                    continue;
                }
                switch (number) {
                    default:
                    case 1:
                        repeatability=new Once();
                        break;
                    case 2:
                        repeatability=new Daily();
                        break;
                    case 3:
                        repeatability=new Weekly();
                        break;
                    case 4:
                        repeatability=new Monthly();
                        break;
                    case 5:
                        repeatability=new Annually();
                        break;
                }break;

            } else {
                scanner.next();
            }
        } while (true);
        return repeatability;
    }

    private static void printMenu() {
        System.out.println("1. Добавить задачу\n2. Удалить задачу\n3. Получить задачу на указанный день\n0. Выход");
    }
}