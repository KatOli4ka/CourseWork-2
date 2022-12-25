import constants.Constant;
import exception.WrongTaskParameterException;
import repeatability.*;
import task.Task;
import task.TaskService;
import task.Type;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final Pattern DATE_TIME_PETTERN=Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4} \\d{2}\\:\\d{2}");
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
                        inputTask(scanner);
                        break;
                        case 2:
                            // todo: обрабатываем пункт меню 2
                            break;
                        case 3:
                            // todo: обрабатываем пункт меню 3
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

    private static void inputTask(Scanner scanner) {
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
        } catch (WrongTaskParameterException e) {
            System.out.println(e.getMessage());;
        }

    }

    private static Type inputType(Scanner scanner) {
        Type type;
        do {
            System.out.println("Введите тип задачи: 1. Личная\n2. Рабочая\nТип задачи: ");
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
            System.out.println("Введите дату и время задачи в формате \"25.12.2022 12:00\": ");
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

        return null;
    }

    private static LocalDateTime checkDateTime(String dateTime) {
        try {
            return LocalDateTime.parse(dateTime, Constant.DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
    private static Repeatability inputR (Scanner scanner) {
        Repeatability repeatability;
        do {
            System.out.println("Введите тип повторяемости задачи: 1. Однократная\n2. Ежедневная\n3. Еженедельная" +
                    "\n4. Ежемесячная\n5. Ежегодгная: ");
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