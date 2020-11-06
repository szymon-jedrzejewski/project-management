package pl.ttpsc.javaupdate.project.view.console;

import pl.ttpsc.javaupdate.project.action.Action;
import pl.ttpsc.javaupdate.project.config.Config;

import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {

    public int getUserChoice() {
        return new Scanner(System.in).nextInt();
    }

    public Action start(List<Action> actions) {
        displayActions(actions);
        int userChoice = getUserChoice();
        return actions.get(userChoice);
    }

    private void displayActions(List<Action> actions) {
        for (Action action : actions) {
            System.out.println(actions.indexOf(action) + "." + action.getDisplayName());
        }
    }
}
