package com.example.irrigation.controller;

import com.example.irrigation.model.TimeSlot;
import com.example.irrigation.service.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-slots")
public class TimeSlotController {

    private final TimeSlotService timeSlotService;

    @Autowired
    public TimeSlotController(TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }

    @PostMapping
    public ResponseEntity<TimeSlot> addNewTimeSlot(@RequestBody TimeSlot timeSlot) {
        TimeSlot newTimeSlot = timeSlotService.addNewTimeSlot(timeSlot);
        return new ResponseEntity<>(newTimeSlot, HttpStatus.CREATED);
    }

    @PostMapping("/configure/{timeSlotId}")
    public ResponseEntity<TimeSlot> configureTimeSlot(@PathVariable Long timeSlotId,
                                                      @RequestParam String status) {
        TimeSlot configuredTimeSlot = timeSlotService.configureTimeSlot(timeSlotId, status);
        return ResponseEntity.ok(configuredTimeSlot);
    }

    @PutMapping("/{timeSlotId}")
    public ResponseEntity<TimeSlot> editTimeSlot(@PathVariable Long timeSlotId,
                                                 @RequestBody TimeSlot updatedTimeSlot) {
        TimeSlot editedTimeSlot = timeSlotService.editTimeSlot(timeSlotId, updatedTimeSlot);
        return ResponseEntity.ok(editedTimeSlot);
    }

    @GetMapping
    public ResponseEntity<List<TimeSlot>> getAllTimeSlots() {
        List<TimeSlot> timeSlots = timeSlotService.getAllTimeSlots();
        return ResponseEntity.ok(timeSlots);
    }
}
