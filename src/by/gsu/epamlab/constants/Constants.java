package by.gsu.epamlab.constants;

public class Constants {

    public static final String USER = "user";
    public static final String ID = "id";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String PASSWORD_CHECK = "password check";
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String FILE = "file";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE_PREFIX = "image";
    public static final String BLACK_AND_WHITE_FILTER = "blackAndWhite";
    public static final String USER_IMAGE_LIST = "userImageList";
    public static final int MAX_PASS_LENGTH = 10, MAX_LOGIN_LENGTH = 10;
    public static final int MIN_PASS_LENGTH = 3, MIN_LOGIN_LENGTH =3;
    public static final int MAX_DESCRIPTION_LENGTH = 30;
    public static final String OK = "ok";
    public static final String LOGIN_PATTERN = ".{" + MIN_LOGIN_LENGTH + "," + MAX_LOGIN_LENGTH + "}";
    public static final String PASSWORD_PATTERN = ".{" + MIN_PASS_LENGTH + "," + MAX_PASS_LENGTH + "}";

}
