package context;

import command.AuthCommand;
import command.ClientProfileCommand;
import command.Command;
import command.FeedbackCommand;
import command.HomeCommand;
import command.LoginCommand;
import command.RegisterCommand;
import command.SpecialistCabinetCommand;
import dao.AppointmentDao;
import dao.DBConnector;
import dao.FeedbackDao;
import dao.TimeslotDao;
import dao.TreatmentDao;
import dao.UserDao;
import dao.impl.AppointmentDaoImpl;
import dao.impl.FeedbackDaoImpl;
import dao.impl.TimeslotDaoImpl;
import dao.impl.TreatmentDaoImpl;
import dao.impl.UserDaoImpl;
import dto.UserDto;
import entity.User;
import mapper.AppointmentMapper;
import mapper.Mapper;
import mapper.UserMapper;
import service.AppointmentService;
import service.FeedbackService;
import service.TreatmentService;
import service.UserService;
import service.encoder.PasswordEncoder;
import service.impl.AppointmentServiceImpl;
import service.impl.FeedbackServiceImpl;
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
    private static final FeedbackDao FEEDBACK_DAO = new FeedbackDaoImpl(DB_CONNECTOR);
    private static final AppointmentDao APPOINTMENT_DAO = new AppointmentDaoImpl(DB_CONNECTOR);
    private static final TimeslotDao TIMESLOT_DAO = new TimeslotDaoImpl(DB_CONNECTOR);

    private static final UserValidator USER_VALIDATOR = new UserValidator();
    private static final Mapper<User, UserDto> USER_MAPPER = new UserMapper();
    private static final AppointmentMapper APPOINTMENT_MAPPER = new AppointmentMapper(TIMESLOT_DAO, TREATMENT_DAO, USER_DAO);
    private static final PasswordEncoder PASSWORD_ENCODER = new PasswordEncoder();

    private static final UserService USER_SERVICE = new UserServiceImpl(USER_DAO, USER_VALIDATOR, USER_MAPPER, PASSWORD_ENCODER);
    private static final FeedbackService FEEDBACK_SERVICE = new FeedbackServiceImpl(FEEDBACK_DAO, USER_DAO);
    private static final TreatmentService TREATMENT_SERVICE = new TreatmentServiceImpl(TREATMENT_DAO, USER_DAO);
    private static final AppointmentService APPOINTMENT_SERVICE = new AppointmentServiceImpl(APPOINTMENT_DAO, TIMESLOT_DAO, TREATMENT_DAO, APPOINTMENT_MAPPER);

    static {
        COMMANDS.put("", new HomeCommand(TREATMENT_SERVICE));
        COMMANDS.put("login", new LoginCommand());
        COMMANDS.put("register", new RegisterCommand(USER_SERVICE));
        COMMANDS.put("auth", new AuthCommand(USER_SERVICE));
        COMMANDS.put("profile", new ClientProfileCommand());
        COMMANDS.put("feedback", new FeedbackCommand(USER_SERVICE, FEEDBACK_SERVICE));
        COMMANDS.put("cabinet", new SpecialistCabinetCommand(APPOINTMENT_SERVICE));

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
