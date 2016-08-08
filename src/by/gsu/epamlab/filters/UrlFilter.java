package by.gsu.epamlab.filters;

import by.gsu.epamlab.constants.ConstantsAddress;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(filterName = "urlFilter", urlPatterns = {"/delete/*", "/download/*", "/image/*"})
public class UrlFilter extends AbstractFilter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String id = request.getPathInfo();
        if (id == null){
            response.sendRedirect(ConstantsAddress.MAIN_PAGE_SERVLET);
            return;
        }
        try {
            Integer.parseInt(id.substring(1));
        }catch (NumberFormatException e){
            response.sendRedirect(ConstantsAddress.MAIN_PAGE_SERVLET);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
