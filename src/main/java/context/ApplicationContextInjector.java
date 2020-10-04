package context;

import command.Command;
import command.LoginCommand;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContextInjector {
    private static ApplicationContextInjector applicationInjector;
    private static final Map<String, Command> COMMANDS = new HashMap<>();

    static {
        COMMANDS.put("login", new LoginCommand());
    }

    private ApplicationContextInjector() {
    }

    public static ApplicationContextInjector getInstance() {
        if (applicationInjector == null) {
            synchronized (ApplicationContextInjector.class) {
                if (applicationInjector == null) {
                    applicationInjector = new ApplicationContextInjector();
                }
            }
        }
        return applicationInjector;
    }

    public static Map<String, Command> getCommands() {
        return COMMANDS;
    }

}
