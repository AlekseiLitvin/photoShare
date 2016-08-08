package by.gsu.epamlab.controllers.authorization;

import by.gsu.epamlab.constants.ConstantsAddress;
import by.gsu.epamlab.controllers.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(urlPatterns = "/logout", name = "logout")
public class LogoutServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        resp.sendRedirect(ConstantsAddress.START_SERVLET);
    }
}
