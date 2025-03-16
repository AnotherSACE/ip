package chrome.tasks;

public class Event extends Task {

    protected String starts;
    protected String ends;

    public Event(String description, String starts, String ends) {
        super(description);
        this.starts = starts;
        this.ends = ends;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + starts + " to: " + ends + ")";
    }

    @Override
    public String toFileFormat(){
        return "[E]" + super.toFileFormat() + "/" + starts + "/" + ends;
    }
}
