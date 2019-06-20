package by.gsu.epamlab.controllers.authorization;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.ConstantsAddress;
import by.gsu.epamlab.constants.ConstantsError;
import by.gsu.epamlab.controllers.AbstractServlet;
import by.gsu.epamlab.dao.users.UserDao;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.factories.UserDaoFactory;
import by.gsu.epamlab.model.User;
import by.gsu.epamlab.service.MD5Encoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/login", name = "login")
public class LoginServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(Constants.LOGIN);
        String password = req.getParameter(Constants.PASSWORD);

        UserDao dao = UserDaoFactory.getClassFromFactory();
        try {
            if (login == null || password == null || login.trim().isEmpty()) {
                jumpError(ConstantsError.LOGIN_OR_PASSWORD_ABSENT_ERROR, ConstantsAddress.LOGIN_PAGE, req, resp);
                return;
            }

            User user = dao.getUser(login);
            password = MD5Encoder.getEncodedString(password);
            if (user == null || !dao.checkPassword(login, password)) {
                jumpError(ConstantsError.WRONG_LOGIN_OR_PASSWORD, ConstantsAddress.LOGIN_PAGE, req, resp);
                return;
            }
            if (LoggedUsers.usersSet.contains(user)) {
                jumpError(ConstantsError.USER_ALREADY_LOGGED, ConstantsAddress.LOGIN_PAGE, req, resp);
                return;
            }
            HttpSession session = req.getSession();
            session.setAttribute(Constants.USER, user);

            resp.sendRedirect(ConstantsAddress.MAIN_PAGE_SERVLET);

        } catch (DaoException e) {
            jumpError(ConstantsError.DAO_ERROR, ConstantsAddress.REGISTER_PAGE, req, resp);
        }
    }
}
