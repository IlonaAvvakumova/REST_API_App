package com.crud.view;

import com.crud.controller.PostController;
import com.crud.controller.WriterController;
import com.crud.model.Post;
import com.crud.model.Writer;

import java.util.List;
import java.util.Scanner;

public class WriterView {
    private final Scanner scan = new Scanner(System.in);
    private final WriterController controller = new WriterController();
    private final PostController postController = new PostController();

    public void getAll() {
        System.out.println("Все writers:\n");
        List<Writer> writerList = controller.getAll();
        System.out.println(writerList);
    }


    public void getById() {
        System.out.println("Which id needs show");
        Integer id = scan.nextInt();
        Writer writer = controller.getById(id);
        System.out.println(writer);
    }


    public void createWriterView() {
        System.out.println("Enter First Name: ");
        String next1 = scan.nextLine();
        System.out.println("Enter Last Name: ");
        String next2 = scan.nextLine();
        Writer writer = controller.create(next1, next2);
        System.out.println("Created Writer: " + writer);
    }


    public void updateWriterView() {
        System.out.println("Enter change First Name: ");
        String next1 = scan.nextLine();
        System.out.println("Enter change Last Name: ");
        String next2 = scan.nextLine();
        System.out.println("Enter id for change: ");
        Integer id = scan.nextInt();
        List<Post> list = postController.getAll();
        Writer writer = controller.update(id, next1, next2, list);
        System.out.println("Update writer, new writer: " + writer);
    }


    public void deleteWriterView() {
        System.out.println("Which id needs delete: ");
        Integer id = scan.nextInt();
        controller.deleteById(id);
        System.out.println("Удаление прошло успешно");

    }


}
