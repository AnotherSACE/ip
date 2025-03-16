package chrome.tasklist;

import chrome.exceptions.DoneException;
import chrome.exceptions.InvalidInputException;
import chrome.exceptions.InvalidNumberException;
import chrome.tasks.Task;
import static chrome.Chrome.*;

public class TaskList {

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

}
