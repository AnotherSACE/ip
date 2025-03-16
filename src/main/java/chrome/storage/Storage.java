package chrome.storage;

import chrome.exceptions.CorruptedFileException;
import chrome.exceptions.DoneException;
import chrome.tasks.Deadline;
import chrome.tasks.Event;
import chrome.tasks.Task;
import chrome.tasks.ToDo;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final ArrayList<Task> toDoList;
    private final String filePath;
    private static final String LINE = "____________________________________________________________";

    public Storage(String filePath) {
        this.toDoList = new ArrayList<>();
        this.filePath = filePath;
    }

    public void load() {
        Path path = Paths.get(filePath);
        File file = new File(path.toString());

        try {
            if (!Files.exists(path)) {
                System.out.println(LINE + "\nChrome.txt not found.\n" +
                        "Creating Chrome.txt...\n" + LINE);
                Files.createDirectories(path.getParent());
                Files.createFile(path);
                System.out.println("\nChrome.txt created!\n" + LINE);
            }
            Scanner fileScan = new Scanner(file);
            System.out.println(LINE + "\nLoading Chrome.txt...");
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
    }

    public void save()  {
        try (FileWriter overWrite = new FileWriter(filePath)){
            for (Task task : toDoList) {
                overWrite.write(task.toFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println(LINE + "\nCould not save file!\n" + LINE);
        }
        System.out.println(LINE + "\nSaved to " + filePath + "\n" + LINE);
    }

    private static Task toTask(String line) throws CorruptedFileException {
        Task task = null;
        String[] parts;
        String description;
        String type;
        String details;

        try {
            parts = line.split(" ", 2);
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
                    break;

                case ("E"):
                    String[] information = parts[1].split("/");
                    String name =  information[0];
                    String start = information[1];
                    String end = information[2];
                    task = new Event(name, start, end);
                    break;

                default:
                    task = new ToDo(description);
                    break;
            }
            if (details.charAt(4) == 'X') {
                task.setDone(true);
            }
            return task;
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new CorruptedFileException();
        } catch (DoneException ignored) {
            return task;
        }
    }

    public ArrayList<Task> getTasks() {
        return toDoList;
    }


}
