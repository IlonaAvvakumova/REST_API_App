package com.crud.service;

import com.crud.model.Writer;
import com.crud.repository.WriterRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class WriterServiceTest {
    WriterRepository writerRepository = Mockito.mock(WriterRepository.class);
    WriterService writerService = new WriterService(writerRepository);

    @Test
    void getAll() {
        List<Writer> list = new ArrayList<>();
        when(writerRepository.getAll()).thenReturn(list);
        List<Writer> writerList =  writerRepository.getAll();
        assertNotNull(writerService.getAll());
        assertArrayEquals(list.toArray(), writerList.toArray());
    }

    @Test
    void getById() {
        Writer writer = new Writer();
        when(writerRepository.getById(1)).thenReturn(writer);
        assertEquals(writer, writerService.getById(1));
    }

    @Test
    void create() {
        Writer writer = new Writer();
        when(writerRepository.create(writer)).thenReturn(writer);
        assertEquals(writer, writerService.create(writer));
    }

    @Test
    void update() {
        Writer writer = new Writer();
        when(writerRepository.update(writer)).thenReturn(writer);
        assertEquals(writer, writerService.update(writer));
    }

    @Test
    void deleteById() {
       WriterService writerService2 = Mockito.mock(WriterService.class);
            doNothing().when(writerService2).deleteById(1);
    }
}