package com.crud.service;

import com.crud.model.Label;
import com.crud.repository.LabelRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class LabelServiceTest {

         LabelRepository labelRepository = Mockito.mock(LabelRepository.class);
         LabelService labelService = new LabelService(labelRepository);

    @Test
    void getAll() {

        List<Label> list = new ArrayList<>();
        when(labelRepository.getAll()).thenReturn(list);
        List<Label> labelList =  labelRepository.getAll();
        assertNotNull(labelService.getAll());
        assertArrayEquals(list.toArray(), labelList.toArray());
    }

    @Test
    void getById() {

        when(labelRepository.getById(1)).thenReturn(new Label());
        assertEquals(new Label(), labelService.getById(1));
    }

    @Test
    void create() {

        Label label = new Label();
        when(labelRepository.create(label)).thenReturn(label);
        assertEquals(label, labelService.create(label));
    }

    @Test
    void update() {
        Label label = new Label();
        when(labelRepository.update(label)).thenReturn(label);
        assertEquals(label, labelService.update(label));
    }

    @Test
    void deleteById() {
       LabelService labelService2 = Mockito.mock(LabelService.class);
        doNothing().when(labelService2).deleteById(1);
    }
}