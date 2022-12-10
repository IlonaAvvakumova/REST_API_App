package com.crud.service;
import com.crud.model.Event;
import com.crud.repository.HiberEventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    HiberEventRepository hiberEventRepository = Mockito.mock(HiberEventRepository.class);

    EventService eventService = new EventService(hiberEventRepository);

    @Test
    void getAll() {
        List<Event> eventList = new ArrayList();
        when(hiberEventRepository.getAll()).thenReturn(eventList);
        List<Event> eventEntities = eventService.getAll();
        assertEquals(eventList, eventEntities);
    }
    @Test
    void getById() {
        when(hiberEventRepository.getById(Mockito.anyInt())).thenReturn(new Event());
        assertEquals(new Event(), eventService.getById(1));
    }
}