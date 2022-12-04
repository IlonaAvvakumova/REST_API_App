package com.crud.service;
import com.crud.model.FileEntity;
import com.crud.repository.HiberFileRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
class FileServiceTest {

    HiberFileRepository hiberFileRepository = Mockito.mock(HiberFileRepository.class);
    FileService fileService = new FileService(hiberFileRepository);

    @Test
    void getAll() {
        List<FileEntity> fileEntityList = new ArrayList();
        when(hiberFileRepository.getAll()).thenReturn(fileEntityList);
        List<FileEntity> files = fileService.getAll();
        assertEquals(fileEntityList, files );
    }
    @Test
    void getById() {
        when(hiberFileRepository.getById(Mockito.anyInt())).thenReturn(new FileEntity());
        assertEquals(new FileEntity(), fileService.getById(1));
    }
    @Test
    void create() {
        FileEntity fileEntity = new FileEntity();
        when(hiberFileRepository.create(fileEntity)).thenReturn(fileEntity);
        assertEquals(fileEntity, fileService.create(fileEntity));
    }

    @Test
    void deleteById() {
        FileService fileService1 = Mockito.mock(FileService.class);
        doNothing().when(fileService1).deleteById(1);
    }
}