package com.example.irrigation.controller;

import com.example.irrigation.model.Plot;
import com.example.irrigation.service.PlotService;
import com.example.irrigation.service.SensorService;
import com.example.irrigation.service.TimeSlotService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class PlotControllerTest {

    @Mock
    private PlotService plotService;

    @Mock
    private SensorService sensorService;

    @Mock
    private TimeSlotService timeSlotService;

    @InjectMocks
    private PlotController plotController;

    public PlotControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddNewPlot() {
        Plot plot = new Plot();
        when(plotService.addNewPlot(plot)).thenReturn(plot);

        ResponseEntity<Plot> response = plotController.addNewPlot(plot);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(plot, response.getBody());
    }

    @Test
    public void testConfigurePlot() {
        Plot configuredPlot = new Plot();
        when(plotService.configurePlot(1L, "cropType", 0.5)).thenReturn(configuredPlot);

        when(sensorService.configureSensor(1L)).thenReturn(true);

        ResponseEntity<Plot> response = plotController.configurePlot(1L, "cropType", 0.5, 1L, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(configuredPlot, response.getBody());
    }

    @Test
    public void testEditPlot() {
        Long plotId = 1L;
        Plot updatedPlot = new Plot();
        Plot editedPlot = new Plot();

        when(plotService.editPlot(plotId, updatedPlot)).thenReturn(editedPlot);

        ResponseEntity<Plot> response = plotController.editPlot(plotId, updatedPlot);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(editedPlot, response.getBody());
    }

    @Test
    public void testGetAllPlots() {
        List<Plot> plots = new ArrayList<>();
        plots.add(new Plot());
        plots.add(new Plot());

        when(plotService.getAllPlots()).thenReturn(plots);

        ResponseEntity<List<Plot>> response = plotController.getAllPlots();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(plots, response.getBody());
    }

}
