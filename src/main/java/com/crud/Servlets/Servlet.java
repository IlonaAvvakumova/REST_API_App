package com.crud.Servlets;
import com.crud.model.Label;
import com.crud.service.LabelService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/label")
public class Servlet extends HttpServlet {
    //ObjectMapper получить JSON
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        PrintWriter printWriter = resp.getWriter();
        Object param = req.getParameter("param");
        String param2 = req.getParameter("param2");
        System.out.println(param.toString());
        LabelService labelService = new LabelService();
        Label byId = labelService.getById(Integer.parseInt(param.toString()));
        printWriter.write("<html>");
        printWriter.write("<body>");
        printWriter.format("<p>privet %s</p>", byId);
        printWriter.format(param2 + " two parametr");
        printWriter.write("</body>");
        printWriter.write("</html>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();

        printWriter.write("poka");

    }
}
