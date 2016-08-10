package by.gsu.epamlab.filters;

import javax.servlet.*;
import java.io.IOException;

public class AbstractFilter implements Filter {

    //one more coment
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }
}
