package com.crud.controller.newController;

import com.crud.model.FileDB;
import com.crud.service.FileService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
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
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/files", initParams = {
        @WebInitParam(name = "upload.location", value = "D:/java/home/avvakumova/REST_API_App/src/main/java/com/crud/files")
})
public class FileController extends HttpServlet {
    FileService fileService = new FileService();

    public FileController() {
    }

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter printWriter = resp.getWriter();
        req.getParameter("createfile");
        if (req.getQueryString().equals("createfile")) {
            String json = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            FileDB file = new ObjectMapper().readValue(json, FileDB.class);
            fileService.create(file);
            return;
        }

        String d = req.getParameter("deleteId");
        Integer deleteId = Integer.parseInt(d);
        if (req.getQueryString().equals("deleteId=" + deleteId)) {
            fileService.deleteById(deleteId);
            return;
        }

    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
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



        req.getParameter("loading");
        if (req.getQueryString().equals("loading")) {
            Part filePart = req.getPart("filePath");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            File uploads = new File(getServletConfig().getInitParameter("upload.location"));
            System.out.println(uploads);
            File file = new File(uploads, fileName);
            InputStream input = filePart.getInputStream();
            Files.copy(input, file.toPath());
        }
    }
}
