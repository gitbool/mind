package modules.nginx;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.URL;
import java.util.Date;

/**
 * Created by shenzhen on 2018/5/29 9:55.
 */
@WebServlet(name = "nginx", urlPatterns = {"/nginx", "/nginxDemo"})
public class NginxController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();



        String method = request.getMethod();
        StringBuilder builder = new StringBuilder();
        builder.append("---method is: ").append(method);

        URL url = NginxController.class.getClassLoader().getResource("");

        builder.append("\r\nlocation is: " + url);

        InetAddress host = InetAddress.getLocalHost();

        builder.append("\r\nhost is: " + host);

        builder.append("\r\ncurrent time is: " + new Date().toLocaleString());



        String ipAddress = null;
        if (request.getHeader("x-forwarded-for") == null) {
            ipAddress = request.getRemoteAddr();
        }else{
            if(request.getHeader("x-forwarded-for").length()  > 15){
                String [] aStr = request.getHeader("x-forwarded-for").split(",");
                ipAddress = aStr[0];
            } else{
                ipAddress = request.getHeader("x-forwarded-for");
            }
        }

        String terminal = request.getHeader("User-Agent");
        builder.append("\r\n userAgent: " + terminal);
        builder.append("\r\ncustomer IP: "+ipAddress);
        builder.append("\r\ncustomer URL: "+request.getRequestURL());




        writer.write(builder.toString());
        return ;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
        return;
    }
}
