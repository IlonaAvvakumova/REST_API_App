package com.crud.controller;
import com.crud.model.Event;
import com.crud.model.FileEntity;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/api/v1/files/*", initParams = {
        @WebInitParam(name = "upload.location", value = "D:/java/home/avvakumova/REST_API_App/src/main/java/com/crud/files")
})
@MultipartConfig()
public class FileController extends HttpServlet {
    FileService fileService;
    public FileController() {
        fileService = new FileService();
    }
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (req.getRequestURI().equals("/REST_API_App/api/v1/files/create")) {
            String json = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            FileEntity file = new ObjectMapper().readValue(json, FileEntity.class);
            file.setFilePath(getServletConfig().getInitParameter("upload.location"));
            FileEntity fileEntity = fileService.create(file);
            UserService userService = new UserService();
            List<Event> events = new ArrayList<>();
            User user = new User();
            Enumeration s2 = req.getHeaders("user_id");
            while (s2.hasMoreElements()) {
                Integer id = Integer.parseInt((String) s2.nextElement());
                user = userService.getById(id);
                Event event = new Event();
                event.setFileEntity(fileEntity);
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter printWriter = resp.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        if (req.getRequestURI().equals("/REST_API_App/api/v1/files")) {
            List<FileEntity> files = fileService.getAll();
            for (FileEntity u : files
            ) {
                String jsonString = objectMapper.writeValueAsString(u);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                printWriter.print(jsonString);
                printWriter.flush();
            }
            return;
        }
        int index = req.getRequestURI().lastIndexOf("/");
        String s = req.getRequestURI().substring(index + 1);
        Integer id = Integer.parseInt(s);
        if (req.getRequestURI().equals("/REST_API_App/api/v1/files/" + id)) {
            FileEntity byId = fileService.getById(id);
            String jsonString = objectMapper.writeValueAsString(byId);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            printWriter.print(jsonString);
            printWriter.flush();
            return;
        }
        printWriter.close();
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        int index = req.getRequestURI().lastIndexOf("/");
        String s = req.getRequestURI().substring(index + 1);
        Integer id = Integer.parseInt(s);
        if (req.getRequestURI().equals("/REST_API_App/api/v1/files/delete/" + id)) {
            fileService.deleteById(id);
            return;
        }
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().equals("/REST_API_App/api/v1/files/loading")) {
            Part filePart = req.getPart("filePath");
            File uploads = new File(getServletConfig().getInitParameter("upload.location"));
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            File file = new File(uploads, fileName);
            InputStream input = filePart.getInputStream();
            Files.copy(input, file.toPath());
        }
    }
}
