package chrome.ui;

import static chrome.Chrome.*;

public class Ui {

public static void greet() {
    System.out.println(LINE + "\nHello! I'm Chrome \n" +
            "How can I help you today?\n" + LINE);
}

public static boolean exit() {
    System.out.println(LINE + "\nHave a nice day!\n" + LINE);
    return false;
}

}
