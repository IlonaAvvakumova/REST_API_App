package com.crud.controller;

import com.crud.model.Label;
import com.crud.service.LabelService;

import java.util.List;

public class LabelController {

 private final   LabelService labelService = new LabelService();

 public List<Label> getAll() {
  return labelService.getAll();
 }


 public Label getById(Integer integer) {
  return labelService.getById(integer);
 }


 public Label create(String name) {
  Label label = new Label();
  label.setName(name);
  return labelService.create(label);
 }


 public Label update(Integer id, String name) {
  Label label = new Label();
  label.setId(id);
  label.setName(name);
  return labelService.update(label);
 }


 public void deleteById(Integer id) {
labelService.deleteById(id);
 }

}
