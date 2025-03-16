package chrome.tasks;

import chrome.exceptions.DoneException;
import chrome.exceptions.InvalidInputException;

public class Task {

    private final String name;
    private Boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void setDone(Boolean done) throws DoneException {
        if (this.done == done){
            throw new DoneException("Task already set as done");
        }
        this.done = done;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        if (!done) {
            return "[ ] " + name;
        } else {
            return "[X] " + name;
        }
    }


    public Task getTask() throws InvalidInputException {
        String[] parts = getName().split(" ", 2); //Cannot be empty exception
        String details;
        String type = parts[0];

        if (!(type.equals("event") || type.equals("todo") || type.equals("deadline"))) {
            throw new InvalidInputException("Please begin task with relevant command");
        }
        try {
            details = parts[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidInputException("Please provide details");
        }

        return switch (type) {
            case "event" -> {
                String[] eventDetails = details.split("/", 3);
                try {
                    yield new Event(eventDetails[0].trim(), eventDetails[1].trim(), eventDetails[2].trim());
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new InvalidInputException("Please provide adequate timing");
                }
            }
            case "deadline" -> {
                String[] deadlineDetails = details.split("/", 2);
                try {
                    yield new Deadline(deadlineDetails[0].trim(), deadlineDetails[1].trim());
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new InvalidInputException("Please provide adequate timing");
                }
            }
            default -> new ToDo(details.trim());
        };
    }

    public String toFileFormat(){
        if (!done) {
            return "[-] " + name;
        } else {
            return "[X] " + name;
        }
    }
}
