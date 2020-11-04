package pl.ttpsc.javaupdate.project;

import pl.ttpsc.javaupdate.project.config.Config;
import pl.ttpsc.javaupdate.project.view.console.ConsoleMenu;

public class ConsoleApplication {

    public static void main(String[] args) {
        Config config = new Config()
                .withSqlPersistence();

        ConsoleMenu menu = new ConsoleMenu();
    }
}
