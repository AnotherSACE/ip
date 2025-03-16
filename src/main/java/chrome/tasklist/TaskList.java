package chrome.tasklist;

import chrome.exceptions.DoneException;
import chrome.exceptions.InvalidInputException;
import chrome.exceptions.InvalidNumberException;
import chrome.tasks.Task;
import java.util.ArrayList;

/**
 * The TaskList class manages a list of tasks, providing methods to add, mark, unmark, delete, list,
 * and search for tasks. It handles task creation, user interaction, and task manipulation operations.
 * It is used to manage a collection of Task objects by adding and deleting tasks, marking tasks as done
 * or undone, listing all tasks, and finding tasks by keyword.
 */
public class TaskList {

    private final ArrayList<Task> tasks;
    private static final String LINE = "____________________________________________________________";

    /**
     * Constructor for the TaskList class.
     * Initializes the task list with an existing list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task with the specified description to the task list.
     * If the task is valid, it is added to the list, and the user is notified.
     * If the task description is invalid, an error message is shown.
     *
     * @param description The description of the task to be added.
     */
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

        System.out.println("Now you have: " + (count) +
                " task" + plural + " in the list\n" + LINE);
    }

    /**
     * Lists all tasks currently in the task list.
     * Displays each task with an index and prints the task details.
     */
    public void list() {
        System.out.println(LINE);
        int index = 1;
        for (Task task : tasks) {
            if (task != null) {
                System.out.println(index + ". " + task);
                index++;
            }
        }
        System.out.println(LINE);
    }

    /**
     * Marks a specific task as done.
     * If the task is already marked as done, a message is shown to the user.
     *
     * @param number The task number to be marked as done.
     */
    public void mark(String number) {
        Task task = taskGetter(number);
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
                + task + "\n" + LINE);
    }

    /**
     * Marks a specific task as undone.
     * If the task is already marked as undone, a message is shown to the user.
     *
     * @param number The task number to be marked as undone.
     */
    public void unmark(String number) {
        Task task = taskGetter(number);
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
                + task + "\n" + LINE);
    }

     /**
     * Deletes a specific task from the list.
     * If the task does not exist, a message is shown to the user.
     *
     * @param number The task number to be deleted.
     */
    public void delete(String number) {
        Task task = taskGetter(number);
        if (task == null) {
            return;
        }

        tasks.remove(task);

        int count = tasks.size();
        String plural = (count == 1) ? "" : "s";
        System.out.println(LINE + "\nOk . I've removed this task:\n" + task +
                "\nYou now have " + count + " task" + plural + " in the list!\n" + LINE);
    }


    /**
     * Finds and displays tasks that contain the given keyword in their description.
     * This method searches through all tasks in the list and prints matching tasks.
     *
     * @param input The input containing the keyword to search for (e.g., "find book").
     */
    public void find(String input) {
        String keyword = input.substring(5);
        System.out.println("Here are the results for: " + keyword);
        for (Task task : tasks) {
            if (task.toString().contains(keyword)){
                System.out.println(task);
            }
        }
        System.out.println(LINE);
    }

    /**
     * Retrieves a task based on the given input (task number).
     * Converts the input into an index and checks for validity (e.g., non-negative, integer).
     *
     * @param input The input containing the task number.
     * @return The Task object if valid, or null if the task does not exist.
     */
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
