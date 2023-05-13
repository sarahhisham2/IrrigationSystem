package com.example.irrigation.controller;

import com.example.irrigation.model.Plot;
import com.example.irrigation.model.Sensor;
import com.example.irrigation.model.TimeSlot;
import com.example.irrigation.service.PlotService;
import com.example.irrigation.service.SensorService;
import com.example.irrigation.service.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plots")
public class PlotController {

    private final PlotService plotService;
    private final SensorService sensorService;
    private final TimeSlotService timeSlotService;

    @Autowired
    public PlotController(PlotService plotService, SensorService sensorService, TimeSlotService timeSlotService) {
        this.plotService = plotService;
        this.sensorService = sensorService;
        this.timeSlotService = timeSlotService;
    }

    @PostMapping
    public ResponseEntity<Plot> addNewPlot(@RequestBody Plot plot) {
        Plot newPlot = plotService.addNewPlot(plot);
        return new ResponseEntity<>(newPlot, HttpStatus.CREATED);
    }

    @PostMapping("/configure/{plotId}")
    public ResponseEntity<Plot> configurePlot(@PathVariable Long plotId,
                                              @RequestParam String cropType,
                                              @RequestParam Double irrigationThreshold,
                                              @RequestParam Long timeSlotId,
                                              @RequestParam Long sensorId) {
        // Retrieve the plot from the plot service
        Plot configuredPlot = plotService.configurePlot(plotId, cropType, irrigationThreshold);

        // Simulate the integration with the sensor by configuring it
       Sensor sensor = new Sensor();

        // Retry the sensor configuration for a certain number of times if it's not available
        int retryCount = 0;
        int maxRetries = 3;
        while (retryCount < maxRetries) {
            try {
                if(sensorService.configureSensor(sensorId)){

                    // Update the status of the time slot to "active" once the configuration is successfully sent to the sensor
                    TimeSlot timeSlot = timeSlotService.configureTimeSlot(timeSlotId, "active");
                    break;

                }
                else{
                    retryCount++;
                    System.out.println("Sensor configuration failed. Retrying... Attempt: " + retryCount);
                }
            } catch (Exception e) {

            }
        }

        if (retryCount == maxRetries) {
            // Handle the case when the sensor is not available after maxRetries
            // Implementing the alerting system to notify about the sensor unavailability
            System.out.println("Sensor configuration failed after maximum retries. Alerting system triggered.");

        }


        return ResponseEntity.ok(configuredPlot);

    }


    @PutMapping("/{plotId}")
    public ResponseEntity<Plot> editPlot(@PathVariable Long plotId, @RequestBody Plot updatedPlot) {
        Plot editedPlot = plotService.editPlot(plotId, updatedPlot);
        return ResponseEntity.ok(editedPlot);
    }

    @GetMapping
    public ResponseEntity<List<Plot>> getAllPlots() {
        List<Plot> plots = plotService.getAllPlots();
        return ResponseEntity.ok(plots);
    }
}


