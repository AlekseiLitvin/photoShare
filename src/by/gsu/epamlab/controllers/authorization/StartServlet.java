package by.gsu.epamlab.controllers.authorization;

import by.gsu.epamlab.constants.ConstantsAddress;
import by.gsu.epamlab.dao.images.ImageDao;
import by.gsu.epamlab.factories.ImageDaoFactory;
import by.gsu.epamlab.model.Image;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;


@WebServlet (urlPatterns = "/start", name = "start")
public class StartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ImageDao imageDao = ImageDaoFactory.getClassFromFactory();
        List<Image> list = imageDao.getImageList();
        Collections.shuffle(list);
        req.setAttribute("list", list);
        getServletContext().getRequestDispatcher(ConstantsAddress.START_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
