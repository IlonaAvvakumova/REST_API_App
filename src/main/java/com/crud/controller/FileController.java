package com.crud.controller;

import com.crud.model.Event;
import com.crud.model.FileDB;
import com.crud.model.User;
import com.crud.service.FileService;
import com.crud.service.UserService;
import com.crud.utils.HibernateSessionFactoryUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/api/v1/files", initParams = {
        @WebInitParam(name = "upload.location", value = "D:/java/home/avvakumova/REST_API_App/src/main/java/com/crud/files")
})

@MultipartConfig()
public class FileController extends HttpServlet {

    FileService fileService = new FileService();

    public FileController() {
    }

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        if (req.getQueryString().equals("createfile")) {
            String json = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            FileDB file = new ObjectMapper().readValue(json, FileDB.class);
            file.setFilePath(getServletConfig().getInitParameter("upload.location"));
            FileDB fileDB = fileService.create(file);

            UserService userService = new UserService();
            List<Event> events = new ArrayList<>();
            User user = new User();
            Enumeration s2 = req.getHeaders("user_id");
            while (s2.hasMoreElements()){
                Integer id = Integer.parseInt((String) s2.nextElement());
                user = userService.getById(id);
                Event event = new Event();
                event.setFileDB(fileDB);
                event.setUser(user);
                events.add(event);

                try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()
                ) {
                    Transaction tx1 = session.beginTransaction();
                    session.save(event);
                    tx1.commit();

                }
            }
            user.setEvents(events);
            userService.update(user);
            return;
        }
      }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        PrintWriter printWriter = resp.getWriter();
        req.getParameter("getAll");
        if (req.getQueryString().equals("getAll")) {
            List<FileDB> files = fileService.getAll();
            printWriter.format("List Files " + files.toString());
            return;
        }

        Object param = req.getParameter("byid");
        if (req.getQueryString().equals("byid=" + param)) {
            FileDB byId = fileService.getById(Integer.parseInt(param.toString()));
            printWriter.format("File " + byId);
            return;
        }
        printWriter.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        String d = req.getParameter("deleteId");
        Integer deleteId = Integer.parseInt(d);
        if (req.getQueryString().equals("deleteId=" + deleteId)) {
            fileService.deleteById(deleteId);
            return;
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getQueryString().equals("loading")) {

            Part filePart = req.getPart("filePath");
            File uploads = new File(getServletConfig().getInitParameter("upload.location"));
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            File file = new File(uploads, fileName);
            InputStream input = filePart.getInputStream();
            Files.copy(input, file.toPath());

        }
    }
}
