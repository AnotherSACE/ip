package chrome.ui;

public class Ui {
    private static final String LINE = "____________________________________________________________";

    public void greet() {
    System.out.println(LINE + "\nHello! I'm Chrome \n" +
            "How can I help you today?\n" + LINE);
    }

    public void exit() {
        System.out.println(LINE + "\nHave a nice day!\n" + LINE);
    }

}
