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
            return "[x] " + name;
        }
    }

}
