package by.future.common.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ${DESCRIPTION}
 *
 * @author by@Deng
 * @create 2017-10-13 19:25
 */
@WebServlet(name = "TestServlet", urlPatterns = "/TestServlet",loadOnStartup=1)
public class TestServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String aa="fdafdafdafdas";
        for(int i=0;i<100;i++){
            aa+="fdsaaaaaaaa";
        }
        response.getWriter().write(aa);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
}
