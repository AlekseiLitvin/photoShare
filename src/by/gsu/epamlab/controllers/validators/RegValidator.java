package by.gsu.epamlab.controllers.validators;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.ConstantsError;

public class RegValidator {
    public static String validation(String login, String password, String passwordCheck) {
        if (login == null || password == null || login.trim().isEmpty()) {
            return ConstantsError.LOGIN_OR_PASSWORD_ABSENT_ERROR;
        }
        login = login.trim();

        if (login.length() < Constants.MIN_LOGIN_LENGTH || login.length() > Constants.MAX_LOGIN_LENGTH) {
            return ConstantsError.WRONG_INPUT_LENGTH;
        }

        if (password.length() < Constants.MIN_PASS_LENGTH || password.length() > Constants.MAX_PASS_LENGTH) {
            return ConstantsError.WRONG_INPUT_LENGTH;
        }

        if (!password.equals(passwordCheck)) {
            return ConstantsError.PASSWORDS_ARE_NOT_EQUALS;
        }

        return Constants.OK;
    }
}
