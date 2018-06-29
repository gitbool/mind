package modules.myfilter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by shenzhen on 2018/6/1 16:40.
 */
@WebFilter(filterName = "logFilter", urlPatterns = {"/ngin"})
public class LogFilter  implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(this.getClass() + "init--->>>");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

//        if(request.getParameter())
        System.out.println(this.getClass() + "doFilter--->>>");
        if(true)


        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println(this.getClass() + "destroy--->>>");
    }
}
