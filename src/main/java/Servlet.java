import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/test")
public class Servlet extends HttpServlet {

    @EJB
    EventBean eventBean;

    @EJB
    Producer producer;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        eventBean.setString("some text");
        eventBean.setCount(8);
        eventBean.setSerialNumber("wvwvwv888Algo");

        resp.getWriter().println("new EventBean was initialized");
        producer.produceMessage();

        resp.getWriter().println("method produceMessage worked");

    }
}
