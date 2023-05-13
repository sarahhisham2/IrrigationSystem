package com.example.irrigation.service;

import com.example.irrigation.model.TimeSlot;
import com.example.irrigation.repository.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;

    @Autowired
    public TimeSlotService(TimeSlotRepository timeSlotRepository) {
        this.timeSlotRepository = timeSlotRepository;
    }

    public TimeSlot addNewTimeSlot(TimeSlot timeSlot) {
        return timeSlotRepository.save(timeSlot);
    }

    public TimeSlot configureTimeSlot(Long timeSlotId, String status) {
        TimeSlot timeSlot = timeSlotRepository.findById(timeSlotId)
                .orElseThrow(() -> new RuntimeException("Time slot not found with ID: " + timeSlotId));

        timeSlot.setStatus(status);
        return timeSlotRepository.save(timeSlot);
    }

    public TimeSlot editTimeSlot(Long timeSlotId, TimeSlot updatedTimeSlot) {
        TimeSlot timeSlot = timeSlotRepository.findById(timeSlotId)
                .orElseThrow(() -> new RuntimeException("Time slot not found with ID: " + timeSlotId));

        timeSlot.setStartTime(updatedTimeSlot.getStartTime());
        timeSlot.setEndTime(updatedTimeSlot.getEndTime());
        timeSlot.setStatus(updatedTimeSlot.getStatus());
        return timeSlotRepository.save(timeSlot);
    }

    public List<TimeSlot> getAllTimeSlots() {
        return timeSlotRepository.findAll();
    }
}
