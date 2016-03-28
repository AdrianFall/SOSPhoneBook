package filter;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Adrian on 28/03/2016.
 */
@Order(Ordered.HIGHEST_PRECEDENCE) // so that it is definitely applied before the main Spring Security filter.
public class SimpleCORSFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("SIMPLECORSFILTER INIT");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("doFilter");
        HttpServletResponse response = (HttpServletResponse) res;
        if (req instanceof HttpServletRequest)  {
            HttpServletRequest httpRequest = (HttpServletRequest) req;
            response.setHeader("Access-Control-Allow-Origin", httpRequest.getHeader("Origin"));
        } else {
            response.setHeader("Access-Control-Allow-Origin", "*");
        }
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        System.out.println("SIMPLECORSFILTER DESTROY");
    }
}