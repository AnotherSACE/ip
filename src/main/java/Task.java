public class Task {

    public String name;
    private Boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void setDone(Boolean done) {
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


    public Task getTask() {
        String[] parts = getName().split(" ", 2);
        String type = parts[0];
        String details = parts[1];

        return switch (type) {
            case "event" -> {
                String[] eventDetails = details.split("/", 3);
                yield new Event(eventDetails[0].trim(), eventDetails[1].trim(), eventDetails[2].trim());
            }
            case "deadline" -> {
                String[] deadlineDetails = details.split("/", 2);
                yield new Deadline(deadlineDetails[0].trim(), deadlineDetails[1].trim());
            }
            default -> new ToDo(details.trim());
        };
    }

}
