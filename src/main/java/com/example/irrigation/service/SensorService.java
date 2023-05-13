package com.example.irrigation.service;


import com.example.irrigation.model.Sensor;
import com.example.irrigation.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SensorService {

    private  final SensorRepository sensorRepository;
    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public boolean configureSensor(Long Id) {
        Optional<Sensor> optionalSensor = sensorRepository.findById(Id);
        if (optionalSensor.isPresent()) {
            return optionalSensor.get().isIrrigationEnabled();
        }
        throw new IllegalArgumentException("Sensor not found with ID: " + Id);
        // Simulate the configuration of the sensor device
    }

    public Sensor addNewSensor(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

}
