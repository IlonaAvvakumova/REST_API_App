package com.crud.Servlets;

import com.crud.model.Label;
import com.crud.service.LabelService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/label")
public class Servlet extends HttpServlet {
    //ObjectMapper получить JSON
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter printWriter = resp.getWriter();
        Object param = req.getParameter("param");
        req.getParameter("getAll");
        LabelService labelService = new LabelService();

        //показать по ИД

        if (req.getQueryString().equals("param=" + param)) {
            Label byId = labelService.getById(Integer.parseInt(param.toString()));
            printWriter.write("<html>");
            printWriter.write("<body>");
            printWriter.format("<p>privet %s</p>", byId);
            printWriter.write("</body>");
            printWriter.write("</html>");
            return;
        }
        log("Log info");
        //показать Все
        if (req.getQueryString().equals("getAll")) {
            List<Label> labels = labelService.getAll();
            printWriter.format(labels.toString());
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter printWriter = resp.getWriter();
        printWriter.write("poka");
        LabelService labelService = new LabelService();
        Label label = new Label();
        Label label1 = new Label();

        String createLabel = req.getParameter("createName");
        if (req.getQueryString().equals("createName=" + createLabel)) {
            label.setName(createLabel);
            labelService.create(label);
            return;
        }

        String updateLabel = req.getParameter("updateName");
        String updateLabel2 = req.getParameter("updateId");

        if (req.getQueryString().equals("updateName=" + updateLabel + "&updateId=" + updateLabel2) ||
                req.getQueryString().equals("updateId=" + updateLabel2 + "&updateName=" + updateLabel)) {
            label1.setId(Integer.parseInt(updateLabel2));
            label1.setName(updateLabel);
            System.out.println(label1);
            labelService.update(label1);
           return;
        }

        String d = req.getParameter("deleteId");
        Integer deleteId = Integer.parseInt(d);
        if (req.getQueryString().equals("deleteId=" + deleteId)) {
            labelService.deleteById(deleteId);
            return;
        }
    }
}
