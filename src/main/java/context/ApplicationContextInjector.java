package context;

import command.Command;
import command.LoginCommand;
import command.RegisterCommand;
import dao.DBConnector;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import service.UserService;
import service.impl.UserServiceImpl;
import service.validator.UserValidator;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContextInjector {
    private static ApplicationContextInjector applicationInjector;
    private static final Map<String, Command> COMMANDS = new HashMap<>();
    private static final DBConnector DB_CONNECTOR = new DBConnector();
    private static final UserDao USER_DAO = new UserDaoImpl(DB_CONNECTOR);
    private static final UserValidator USER_VALIDATOR = new UserValidator();
    private static final UserService USER_SERVICE = new UserServiceImpl(USER_DAO, USER_VALIDATOR);

    static {
        COMMANDS.put("login", new LoginCommand(USER_SERVICE));
        COMMANDS.put("register", new RegisterCommand(USER_SERVICE));
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
