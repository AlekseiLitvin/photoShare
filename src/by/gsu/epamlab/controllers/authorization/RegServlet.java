package by.gsu.epamlab.controllers.authorization;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.ConstantsAddress;
import by.gsu.epamlab.constants.ConstantsError;
import by.gsu.epamlab.controllers.AbstractServlet;
import by.gsu.epamlab.controllers.validators.RegValidator;
import by.gsu.epamlab.dao.users.UserDao;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.factories.UserDaoFactory;
import by.gsu.epamlab.service.MD5Encoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "registration", urlPatterns = "/registration")
public class RegServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(Constants.LOGIN);
        String password = req.getParameter(Constants.PASSWORD);
        String passwordCheck = req.getParameter(Constants.PASSWORD_CHECK);

        String validationResult = RegValidator.validation(login, password, passwordCheck);
        if (!validationResult.equals(Constants.OK)) {
            jumpError(validationResult, ConstantsAddress.REGISTER_PAGE, req, resp);
            return;
        }

        password = MD5Encoder.getEncodedString(password);
        UserDao dao = UserDaoFactory.getClassFromFactory();
        try {
            if (dao.addUser(login, password)) {
                resp.sendRedirect(ConstantsAddress.START_SERVLET);
            } else {
                jumpError(ConstantsError.USER_ALREADY_EXISTS, ConstantsAddress.REGISTER_PAGE, req, resp);
            }
        } catch (DaoException e) {
            System.err.println(e.getMessage());
            jumpError(ConstantsError.DAO_ERROR, ConstantsAddress.REGISTER_PAGE, req, resp);
        }
    }
}