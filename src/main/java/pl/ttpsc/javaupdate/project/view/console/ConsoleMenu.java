package pl.ttpsc.javaupdate.project.view.console;

import pl.ttpsc.javaupdate.project.action.Action;
import pl.ttpsc.javaupdate.project.config.Config;

import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {

    private List<Action> actions;
    private Config config;

    public ConsoleMenu() {
    }

    public ConsoleMenu(Config config) {
        this.config = config;
    }

    public ConsoleMenu(List<Action> actions) {
        this.actions = actions;
    }

    public int getUserChoice() {
        return new Scanner(System.in).nextInt();
    }

    public void start() {
        displayActions();
        int userChoice = getUserChoice();
        Action action = actions.get(userChoice);
        action.execute();
    }

    private void displayActions() {
        for (Action action : actions) {
            System.out.println(actions.indexOf(action) + "." + action.getDisplayName());
        }
    }
}
