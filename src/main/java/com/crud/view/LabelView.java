package com.crud.view;

import com.crud.controller.LabelController;
import com.crud.model.Label;
import java.util.List;
import java.util.Scanner;

public class LabelView {
    private final Scanner scan = new Scanner(System.in);
    private final LabelController controller = new LabelController();


    public void getAll() {
        System.out.println("Все labels:\n");
        List<Label> labelList = controller.getAll();
        System.out.println(labelList);
    }

    public void getById() {
        System.out.println("Which id needs show");
        Integer id = scan.nextInt();
        Label label = controller.getById(id);
        System.out.println(label);
    }

    public void createLabelView() {
        System.out.println("Enter name: ");
        String name = scan.nextLine();
        Label label = controller.create(name);
        System.out.println("Created Label: " + label);
    }
    public void updateLabelView() {
        System.out.println("Enter change name: ");
        String name = scan.nextLine();
        System.out.println("Enter id for change: ");
        Integer id = scan.nextInt();
        Label label = controller.update(id, name);
        System.out.println("Update label, new label: " + label);
    }

    public void deleteLabelView() {
        System.out.println("Which id needs delete: ");
        Integer id = scan.nextInt();
        controller.deleteById(id);
        System.out.println("Удаление прошло успешно");
    }
}
