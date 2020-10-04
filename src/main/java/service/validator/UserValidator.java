package service.validator;

import entities.User;

import java.util.regex.Pattern;

public class UserValidator implements Validator<User> {
    private static final String USERNAME_REGEX = "^[a-zA-Z][a-zA-Z0-9-_\\.]{3,19}$";
    private static final String EMAIL_REGEX = "^\\w+\\.?\\w+@\\w+\\.[a-z]{2,4}$";
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    private static final String PHONE_NUMBER_REGEX = "^(\\s*)?(\\+)?([- _():=+]?\\d[- _():=+]?){10,14}(\\s*)?$";

    @Override
    public boolean isValid(User user) {
        return validateUsername(user.getUsername()) &&
                validatePassword(user.getPassword()) &&
                validateEmail(user.getEmail()) &&
                validatePhoneNumber(user.getPhoneNumber());
    }

    private boolean validatePhoneNumber(String phoneNumber) {
        return checkWithRegex(phoneNumber, PHONE_NUMBER_REGEX);
    }

    private boolean validateUsername(String name) {
        return checkWithRegex(name, USERNAME_REGEX);
    }

    private boolean validatePassword(String password) {
        return checkWithRegex(password, PASSWORD_REGEX);
    }

    private boolean validateEmail(String email) {
        return checkWithRegex(email, EMAIL_REGEX);
    }

    private boolean checkWithRegex(String input, String regex) {
        return Pattern.matches(regex, input);
    }

}
