package context;

import command.Command;
import command.HomeCommand;
import command.LoginCommand;
import command.RegisterCommand;
import dao.DBConnector;
import dao.TreatmentDao;
import dao.UserDao;
import dao.impl.TreatmentDaoImpl;
import dao.impl.UserDaoImpl;
import dto.UserDto;
import entity.User;
import mapper.Mapper;
import mapper.UserMapper;
import service.TreatmentService;
import service.UserService;
import service.encoder.PasswordEncoder;
import service.impl.TreatmentServiceImpl;
import service.impl.UserServiceImpl;
import service.validator.UserValidator;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContextInjector {
    private static ApplicationContextInjector applicationInjector;
    private static final Map<String, Command> COMMANDS = new HashMap<>();
    private static final DBConnector DB_CONNECTOR = new DBConnector();
    private static final UserDao USER_DAO = new UserDaoImpl(DB_CONNECTOR);
    private static final TreatmentDao TREATMENT_DAO = new TreatmentDaoImpl(DB_CONNECTOR);
    private static final UserValidator USER_VALIDATOR = new UserValidator();
    private static final Mapper<User, UserDto> USER_MAPPER = new UserMapper();
    private static final PasswordEncoder PASSWORD_ENCODER = new PasswordEncoder();
    private static final UserService USER_SERVICE = new UserServiceImpl(USER_DAO, USER_VALIDATOR, USER_MAPPER, PASSWORD_ENCODER);
    private static final TreatmentService TREATMENT_SERVICE = new TreatmentServiceImpl(TREATMENT_DAO, USER_DAO);

    static {
        COMMANDS.put("", new HomeCommand(TREATMENT_SERVICE));
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
