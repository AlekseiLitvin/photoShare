package by.gsu.epamlab.filters;


import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.ConstantsAddress;
import by.gsu.epamlab.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(filterName = "LoginFilter" , urlPatterns = {"/mainpage", "/mypage.jsp", "/logout", "/download/*" , "/delete/*"} )
public class LoginFilter extends AbstractFilter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.USER);
        if (user == null){
            session.invalidate();
            HttpServletResponse Response = (HttpServletResponse) servletResponse;
            Response.sendRedirect(ConstantsAddress.START_SERVLET);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
