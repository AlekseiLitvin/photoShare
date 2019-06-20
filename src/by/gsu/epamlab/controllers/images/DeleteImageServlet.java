package by.gsu.epamlab.controllers.images;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.ConstantsAddress;
import by.gsu.epamlab.dao.images.ImageDao;
import by.gsu.epamlab.factories.ImageDaoFactory;
import by.gsu.epamlab.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(urlPatterns = "/delete/*", name = "deleteImageServlet")
public class DeleteImageServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String path = req.getPathInfo().substring(1);

        int id = Integer.parseInt(path);
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(Constants.USER);

        ImageDao dao = ImageDaoFactory.getClassFromFactory();
        dao.deleteImageById(user.getId(), id);

        resp.sendRedirect(ConstantsAddress.MAIN_PAGE_SERVLET);

    }
}
