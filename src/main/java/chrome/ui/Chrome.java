package chrome.ui;
import chrome.exceptions.*;
import chrome.tasks.*;
import java.nio.file.*;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.io.FileWriter;
import java.util.Scanner;

public class Chrome {

    static final String LINE = "____________________________________________________________";
    static ArrayList<Task> toDoList = new ArrayList<>();
    static int count = 0;
    static final String PATH = "./data/chrome.txt";

    public static void main(String[] args) {
        String logo = """
                 CCCC  H   H  RRRR   OOO   M   M  EEEEE\s
                C      H   H  R   R O   O  MM MM  E    \s
                C      HHHHH  RRRR  O   O  M M M  EEEE \s
                C      H   H  R  R  O   O  M   M  E    \s
                 CCCC  H   H  R   R  OOO   M   M  EEEEE\s
                """;
        load();
        System.out.println("Welcome to\n\n" + logo);
        greet();
        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            String command = input.split(" ")[0];
            if (input.equals("bye")) {
                exit();
                break;
            }
            switch (command) {
                case ("list"):
                    list();
                    break;
                case ("mark"):
                    mark(input);
                    break;
                case ("unmark"):
                    unmark(input);
                    break;
                case ("delete"):
                    delete(input);
                    break;
                case("save"):
                    save();
                    break;
                default:
                    add(input);
                    break;
            }
        }
    }

    public static void greet() {
        System.out.println(LINE + "\nHello! I'm Chrome \n" +
                "How can I help you today?\n" + LINE);
    }

    public static void exit() {
        System.out.println("\nHave a nice day!\n" + LINE);
    }

    public static void add(String description) {
        Task task = new Task(description);
        try {
            task = task.getTask();
        } catch (InvalidInputException e) {
            System.out.println(LINE + "\n" + e.getMessage() + "\n" + LINE);
            return;
        }
        toDoList.add(task);
        System.out.println(LINE + "\nGot it! I've added this task: "
                + task + "\n");
        String plural;
        if (count == 0) {
            plural = "";
        } else {
            plural = "s";
        }
        count++;
        System.out.println("Now you have: " + String.valueOf(count) +
                " task" + plural + " in the list\n" + LINE);
    }

    public static void list() {
        System.out.println(LINE);
        int index = 1;
        for (Task task : toDoList) {
            if (task != null) {
                System.out.println(index + ". " + task.toString());
                index++;
            }
        }
        System.out.println(LINE);
    }

    public static void mark(String input) {
        Task task = taskGetter(input);
        if (task == null) {
            return;
        }

        try {
            task.setDone(true);
        } catch (DoneException e) {
            System.out.println(LINE + "\nTask already marked as done\n" + LINE);
            return;
        }
        System.out.println(LINE + "\nNice! I've marked this task as done:\n"
                + task.toString() + "\n" + LINE);
    }

    public static void unmark(String input) {
        Task task = taskGetter(input);
        if (task == null) {
            return;
        }
        try {
            task.setDone(false);
        } catch (DoneException e) {
            System.out.println(LINE + "\nTask already marked as undone\n" + LINE);
            return;
        }
        System.out.println(LINE + "\nOK, I've marked this task as not done yet:\n"
                + task.toString() + "\n" + LINE);
    }

    public static void delete(String input) {
        Task task = taskGetter(input);
        if (task == null) {
            return;
        }

        toDoList.remove(task);
        count--;

        String plural = (count == 1) ? "" : "s";
        System.out.println(LINE + "\nOk . I've removed this task:\n" + task.toString() +
                "\nYou now have " + String.valueOf(count) + " task" + plural + " in the list!\n" + LINE);
    }

    public static Task taskGetter(String input) {
        int index;
        Task task;
        String[] parts = input.split(" ");

        try {
            index = Integer.parseInt(parts[1]) - 1;
            if (index < 0) {
                throw new InvalidNumberException();
            }
        } catch (NumberFormatException e) {
            System.out.println(LINE + "\nInvalid number!\n" + LINE);
            return null;
        } catch (InvalidNumberException e) {
            System.out.println(LINE + "\n" + "Number must be 1 or higher" + "\n" + LINE);
            return null;
        }

        try {
            task = toDoList.get(index);
            return task;
        } catch (IndexOutOfBoundsException e) {
            System.out.println(LINE + "\nTask doesn't exist!\n" + LINE);
            return null;
        }
    }

    public static void load() {
        Path path = Paths.get(PATH);
        File file = new File(path.toString());

        try {
            if (!Files.exists(path)) {
                System.out.println(LINE + "\nChrome.txt not found.\n" +
                        "Creating Chrome.txt...\n" + LINE);
                Files.createDirectories(path.getParent());
                Files.createFile(path);
                System.out.println(LINE + "\nChrome.txt created!\n" + LINE);
            }
            Scanner fileScan = new Scanner(file);
            System.out.println(LINE + "\nLoading Chrome.txt...\n" + LINE);
            while (fileScan.hasNext()) {
                String line = fileScan.nextLine();
                try {
                    toDoList.add(toTask(line));
                } catch (CorruptedFileException e) {
                    System.out.println(LINE + "\nFile corrupted\n" + LINE);
                    return;
                }
            }
        } catch (IOException ignored) {
            System.out.println(LINE + "\nCould not load file!\n" + LINE);
            return;
        }
        System.out.println(LINE + "\nChrome.txt loaded successfully!\n" + LINE);
        count = toDoList.size();
    }

    public static void save()  {
        try (FileWriter overWrite = new FileWriter(PATH)){
            for (Task task : toDoList) {
                overWrite.write(task.toFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println(LINE + "\nCould not save file!\n" + LINE);
        }
        System.out.println(LINE + "\nSaved to " + PATH + "\n" + LINE);
    }

    private static Task toTask(String line) throws CorruptedFileException {
        Task task = null;
        String[] parts;
        String description;
        String type;
        String details;

        try {
            parts = line.split(" ");
            details = parts[0];
            description = parts[1];
            type = details.substring(1, 2);
        } catch (IndexOutOfBoundsException e) {
            throw new CorruptedFileException();
        }

        if (!(type.equals("D") || type.equals("E") || type.equals("T"))) {
            throw new CorruptedFileException();
        }

        try {
            switch (type) {
                case ("D"):
                    String[] info = description.split("/");
                    String deadlineName = info[0];
                    String time = info[1];
                    task = new Deadline(deadlineName, time);

                    if (details.charAt(4) == 'X') {
                        task.setDone(true);
                    }
                    return task;
                case ("E"):
                    String[] information = parts[1].split("/");
                    String name =  information[0];
                    String start = information[1];
                    String end = information[2];
                    task = new Event(name, start, end);

                    if (details.charAt(4) == 'X') {
                        task.setDone(true);
                    }
                    return task;
                default:
                    task = new ToDo(description);
                    if (details.charAt(4) == 'X') {
                        task.setDone(true);
                    }
                    return task;
            }
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new CorruptedFileException();
        } catch (DoneException ignored) {
            return task;
        }
    }
}
