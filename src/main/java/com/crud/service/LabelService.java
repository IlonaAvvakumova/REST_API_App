package com.crud.service;

import com.crud.model.Label;
import com.crud.repository.Hibernate.HiberLabelRepository;
import com.crud.repository.LabelRepository;

import java.util.List;

public class LabelService {
    LabelRepository labelRepository;

    public LabelService(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;

    }

    public LabelService() {
        this.labelRepository = new HiberLabelRepository();
    }


    public List<Label> getAll() {
        return labelRepository.getAll();
    }


    public Label getById(Integer id) {
        return labelRepository.getById(id);
    }


    public Label create(Label label) {
        return labelRepository.create(label);
    }


    public Label update(Label label) {
        return labelRepository.update(label);
    }


    public void deleteById(Integer id) {
        labelRepository.deleteById(id);
    }
}
