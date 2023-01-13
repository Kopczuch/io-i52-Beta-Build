package pl.put.poznan.buildinfo.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class BuildingTest {
    private Building building;
    private Floor floorMock;

    @BeforeEach
    void setUP(){
        floorMock = mock(Floor.class);
        List<Floor> floors = new ArrayList<>();
        floors.add(floorMock);

        building = new Building(1, "budynek", floors);
    }

    @Test
    void calculateArea() {
        when(floorMock.calculateArea()).thenReturn(10.0);
        assertEquals(building.calculateArea(), 10.0);

        when(floorMock.calculateArea()).thenReturn(15.0);
        assertEquals(building.calculateArea(), 15.0);
    }

    @Test
    void calculateVolume() {
        when(floorMock.calculateVolume()).thenReturn(20.0);
        assertEquals(building.calculateVolume(), 20.0);

        when(floorMock.calculateVolume()).thenReturn(25.0);
        assertEquals(building.calculateVolume(), 25.0);
    }

    @Test
    void calculateLightPower() {
        when(floorMock.calculateLightPower()).thenReturn(30.0);
        assertEquals(building.calculateLightPower(), 30.0);

        when(floorMock.calculateLightPower()).thenReturn(35.0);
        assertEquals(building.calculateLightPower(), 35.0);
    }

    @Test
    void calculateHeatingUsage() {
        when(floorMock.calculateHeatingUsage()).thenReturn(40.0);
        assertEquals(building.calculateHeatingUsage(), 40.0);

        when(floorMock.calculateHeatingUsage()).thenReturn(45.0);
        assertEquals(building.calculateHeatingUsage(), 45.0);
    }

    @Test
    void getNestedList(){
        List<Floor> floorsTest = new ArrayList<>();
        floorsTest.add(floorMock);
        assertEquals(building.getNestedList(), floorsTest);
    }
}