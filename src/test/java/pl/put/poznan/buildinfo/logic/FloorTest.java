package pl.put.poznan.buildinfo.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FloorTest {

    private Floor floor;
    private Room roomMock1;
    private Room roomMock2;

    @BeforeEach
    void setUp(){
        roomMock1 = mock(Room.class);
        roomMock2 = mock(Room.class);
        List<Room> rooms = new ArrayList<>();
        roomMock1.setId(1);
        roomMock1.setName("Pokoj 1");
        roomMock2.setId(2);
        roomMock2.setName("Pokoj 2");
        rooms.add(roomMock1);
        rooms.add(roomMock2);

        floor = new Floor(1, "Floor 1", rooms);
    }

    @Test
    void calculateArea() {
        when(roomMock1.getArea()).thenReturn(5.0);
        when(roomMock2.getArea()).thenReturn(10.0);
        assertEquals(floor.calculateArea(), 15.0);
    }

    @Test
    void calculateVolume() {
        when(roomMock1.getArea()).thenReturn(20.0);
        when(roomMock2.getArea()).thenReturn(25.0);
        assertEquals(floor.calculateArea(), 45.0);
    }

    @Test
    void calculateLightPower() {
        when(roomMock1.getArea()).thenReturn(45.0);
        when(roomMock2.getArea()).thenReturn(50.0);
        assertEquals(floor.calculateArea(), 95.0);
    }

    @Test
    void calculateHeatingUsage() {
        when(roomMock1.getArea()).thenReturn(95.0);
        when(roomMock2.getArea()).thenReturn(100.0);
        assertEquals(floor.calculateArea(), 195.0);
    }

    @Test
    void getNestedList() {
        List<Room> roomsTest = new ArrayList<>();
        roomsTest.add(roomMock1);
        roomsTest.add(roomMock2);
        assertEquals(floor.getNestedList(), roomsTest);
    }
}