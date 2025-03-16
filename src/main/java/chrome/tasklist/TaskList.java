package chrome.tasklist;

import chrome.exceptions.DoneException;
import chrome.exceptions.InvalidInputException;
import chrome.exceptions.InvalidNumberException;
import chrome.tasks.Task;
import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;
    private static final String LINE = "____________________________________________________________";

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(String description) {
        Task task = new Task(description);

        try {
            task = task.getTask();
        } catch (InvalidInputException e) {
            System.out.println(LINE + "\n" + e.getMessage() + "\n" + LINE);
            return;
        }

        tasks.add(task);
        System.out.println(LINE + "\nGot it! I've added this task: "
                + task + "\n");

        int count = tasks.size();
        String plural = (count == 1) ? "" : "s";

        System.out.println("Now you have: " + String.valueOf(count) +
                " task" + plural + " in the list\n" + LINE);
    }

    public void list() {
        System.out.println(LINE);
        int index = 1;
        for (Task task : tasks) {
            if (task != null) {
                System.out.println(index + ". " + task.toString());
                index++;
            }
        }
        System.out.println(LINE);
    }

    public void mark(String input) {
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

    public void unmark(String input) {
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

    public void delete(String input) {
        Task task = taskGetter(input);
        if (task == null) {
            return;
        }

        tasks.remove(task);

        int count = tasks.size();
        String plural = (count == 1) ? "" : "s";
        System.out.println(LINE + "\nOk . I've removed this task:\n" + task.toString() +
                "\nYou now have " + String.valueOf(count) + " task" + plural + " in the list!\n" + LINE);
    }

    public Task taskGetter(String input) {
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
            task = tasks.get(index);
            return task;
        } catch (IndexOutOfBoundsException e) {
            System.out.println(LINE + "\nTask doesn't exist!\n" + LINE);
            return null;
        }
    }

}
