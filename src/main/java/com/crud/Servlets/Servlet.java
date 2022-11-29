package com.crud.Servlets;


import com.crud.model.FileDB;
import com.crud.model.oldModel.Label;
import com.crud.repository.HibernateNew.HiberFileRepository;
import com.crud.service.LabelService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/label", initParams = {
        @WebInitParam(name = "port", value = "8081"),
        @WebInitParam(name = "version", value = "v1"),
        @WebInitParam(name = "upload.location", value = "D:/java/home/avvakumova/REST_API_App/src/main/java/com/crud/files")
})
@MultipartConfig
public class Servlet extends HttpServlet {
    //ObjectMapper получить JSON
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter printWriter = resp.getWriter();

        String json = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        FileDB file = new ObjectMapper().readValue(json, FileDB.class);
        System.out.println(file);
        HiberFileRepository hiberFileRepository = new HiberFileRepository();
        hiberFileRepository.create(file);


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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String description = req.getParameter("loading");
        Part filePart = req.getPart("filePath");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        InputStream fileContent = filePart.getInputStream();

        String text = new String(fileContent.readAllBytes(), StandardCharsets.UTF_8);
        System.out.println(text);//чтение файла
        File uploads = new File(getServletConfig().getInitParameter("upload.location"));
        System.out.println(uploads);
        File file = new File(uploads, fileName);
        InputStream input = filePart.getInputStream();
        Files.copy(input, file.toPath());
    }
}
