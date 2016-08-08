package by.gsu.epamlab.controllers.images;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.ConstantsAddress;
import by.gsu.epamlab.dao.images.ImageDao;
import by.gsu.epamlab.factories.ImageDaoFactory;
import by.gsu.epamlab.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet(urlPatterns = "/mainpage", name = "mainpage")
public class MainPageInitializerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ImageDao imageDao = ImageDaoFactory.getClassFromFactory();
        HttpSession session = req.getSession();
        User user  = (User) session.getAttribute(Constants.USER);
        req.setAttribute(Constants.USER_IMAGE_LIST, imageDao.getImageListById(user.getId()));
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(ConstantsAddress.MAIN_PAGE);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
