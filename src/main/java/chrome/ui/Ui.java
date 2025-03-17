package chrome.ui;

/**
 * The Ui class is responsible for handling user interface interactions.
 * It provides methods to greet the user and exit the program with a farewell message.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";

    /**
     * Greets the user with a welcome message when the program starts.
     */
    public void greet() {
    System.out.println(LINE + "\nHello! I'm Chrome \n" +
            "How can I help you today?\n" + LINE);
    }

    /**
     * Prints a farewell message when the user exits the program.
     */
    public void exit() {
        System.out.println(LINE + "\nHave a nice day!\n" + LINE);
    }

}
