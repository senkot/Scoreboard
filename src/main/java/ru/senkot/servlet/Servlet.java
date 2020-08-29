package ru.senkot.servlet;

import ru.senkot.messaging.Producer;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/send")
public class Servlet extends HttpServlet {

    @EJB
    Producer producer;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        producer.produceMessage();

        resp.getWriter().println("method produceMessage worked");
    }
}
