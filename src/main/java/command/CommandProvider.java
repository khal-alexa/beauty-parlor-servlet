package command;

import context.ApplicationContextInjector;

import java.util.Map;

public class CommandProvider {
    private final Map<String, Command> commands;

    public CommandProvider() {
        commands = ApplicationContextInjector.getCommands();
    }

    public Command getCommand(String requestPath) {
        String commandName = CommandUtils.getCommandNameFromPath(requestPath);
        return commands.getOrDefault(commandName, commands.get("login"));
    }

}
