package by.gsu.epamlab.controllers.images;

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


@WebServlet(urlPatterns = "/image/*", name = "getImage")
public class GetImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ImageDao imageDao = ImageDaoFactory.getClassFromFactory();
        int id = Integer.parseInt(req.getPathInfo().substring(1));
        Image image = imageDao.getImageById(id);

        if (image == null) {
            resp.sendRedirect(ConstantsAddress.ERROR_PAGE);
            return;
        }

        resp.setContentType("image/jpg");
        resp.setContentLength(image.getImage().length);
        resp.getOutputStream().write(image.getImage());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
