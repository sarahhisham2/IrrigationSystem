package com.example.irrigation.DataSeeder;

import com.example.irrigation.model.Plot;
import com.example.irrigation.model.Sensor;
import com.example.irrigation.model.TimeSlot;
import com.example.irrigation.repository.PlotRepository;
import com.example.irrigation.repository.SensorRepository;
import com.example.irrigation.repository.TimeSlotRepository;
import com.example.irrigation.service.PlotService;
import com.example.irrigation.service.SensorService;
import com.example.irrigation.service.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final PlotRepository plotRepository;
    private final SensorRepository sensorRepository;
    private final TimeSlotRepository timeSlotRepository;

    private final PlotService plotService;
    private final TimeSlotService timeSlotService;

    private final SensorService sensorService;


    @Autowired
    public DataSeeder(PlotRepository plotRepository, SensorRepository sensorRepository,
                      TimeSlotRepository timeSlotRepository, PlotService plotService,
                      TimeSlotService timeSlotService, SensorService sensorService) {
        this.plotRepository = plotRepository;
        this.sensorRepository = sensorRepository;
        this.timeSlotRepository = timeSlotRepository;
        this.plotService = plotService;
        this.sensorService = sensorService;
        this.timeSlotService = timeSlotService;
    }

    @Override
    public void run(String... args) throws ChangeSetPersister.NotFoundException {
        generatePlots();
        generateTimeSlots();
        generateSensors();
    }

    private void generatePlots() {
        // Create and save two sample plots
        Plot plot1 = new Plot("Plot 1", 10.0, "Rice", 0.7, "Sandy");
        Plot plot2 = new Plot("Plot 2", 8.0, "Wheat", 0.4, "Clay");
        plotRepository.saveAll(List.of(plot1, plot2));

        // Create and save two sensors
//        Sensor sensor1 = new Sensor("Sensor 1", false);
//        Sensor sensor2 = new Sensor("Sensor 2", false);
//        sensorRepository.saveAll(List.of(sensor1, sensor2));
//
//        // Associate sensors with plots
//        plot1.setSensor(sensor1);
//        plot2.setSensor(sensor2);
//        plotRepository.saveAll(List.of(plot1, plot2));

        // Print the saved plots and associated data
        System.out.println("Plots and associated data seeded successfully:");
        System.out.println(plot1);
      //  System.out.println(sensor1);
        System.out.println(plot2);
        //System.out.println(sensor2);
    }

    private void generateTimeSlots() throws ChangeSetPersister.NotFoundException {
        Plot plot1 = plotService.getPlotById(1L);
        Plot plot2 = plotService.getPlotById(2L);

        TimeSlot timeSlot1 = new TimeSlot();
        timeSlot1.setPlot(plot1);
        timeSlot1.setStartTime(LocalTime.of(8, 0));
        timeSlot1.setEndTime(LocalTime.of(10, 0));
        timeSlot1.setStatus("idle");
        timeSlotService.addNewTimeSlot(timeSlot1);

        TimeSlot timeSlot2 = new TimeSlot();
        timeSlot2.setPlot(plot2);
        timeSlot2.setStartTime(LocalTime.of(14, 0));
        timeSlot2.setEndTime(LocalTime.of(16, 0));
        timeSlot2.setStatus("idle");
        timeSlotService.addNewTimeSlot(timeSlot2);

        // Add more time slots as needed
    }

    private void generateSensors() throws ChangeSetPersister.NotFoundException {
        Plot plot1 = plotService.getPlotById(1L);
        Plot plot2 = plotService.getPlotById(2L);

        Sensor sensor1 = new Sensor();
        sensor1.setPlot(plot1);
        sensor1.setSensorId("123456789");
        sensor1.setIrrigationEnabled(false);
        sensorService.addNewSensor(sensor1);

        Sensor sensor2 = new Sensor();
        sensor2.setPlot(plot1);
        sensor2.setSensorId("123456788");
        sensor2.setIrrigationEnabled(true);
        sensorService.addNewSensor(sensor2);

        Sensor sensor3 = new Sensor();
        sensor3.setPlot(plot2);
        sensor3.setSensorId("123456787");
        sensor3.setIrrigationEnabled(true);
        sensorService.addNewSensor(sensor3);

    }
}

//package com.example.irrigation.DataSeeder;
//
//import com.example.irrigation.model.Plot;
//import com.example.irrigation.model.Sensor;
//import com.example.irrigation.model.TimeSlot;
//import com.example.irrigation.repository.PlotRepository;
//import com.example.irrigation.repository.SensorRepository;
//import com.example.irrigation.repository.TimeSlotRepository;
//import com.example.irrigation.service.PlotService;
//import com.example.irrigation.service.TimeSlotService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.data.crossstore.ChangeSetPersister;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalTime;
//import java.util.List;
//
//@Component
//public class DataSeeder implements CommandLineRunner {
//
//    private final PlotRepository plotRepository;
//    private final SensorRepository sensorRepository;
//    private final TimeSlotRepository timeSlotRepository;
//
//    @Autowired
//    public DataSeeder(PlotRepository plotRepository, SensorRepository sensorRepository,
//                      TimeSlotRepository timeSlotRepository) {
//        this.plotRepository = plotRepository;
//        this.sensorRepository = sensorRepository;
//        this.timeSlotRepository = timeSlotRepository;
//    }
//
//    @Override
//    public void run(String... args) throws ChangeSetPersister.NotFoundException {
//        seedData();
//        generateTimeSlots();
//    }
//
//    private void seedData() {
//        // Create and save two sample plots
//        Plot plot1 = new Plot("Plot 1",10.0,"Rice",0.7, "Sandy");
//        Plot plot2 = new Plot("Plot 2", 8.0,"Wheat",0.4, "Clay");
//        plotRepository.saveAll(List.of(plot1, plot2));
//
//        // Create and save two sensors
//        Sensor sensor1 = new Sensor("Sensor 1", false);
//        Sensor sensor2 = new Sensor("Sensor 2", false);
//        sensorRepository.saveAll(List.of(sensor1, sensor2));
//
//        // Associate sensors with plots
//        plot1.setSensor(sensor1);
//        plot2.setSensor(sensor2);
//        plotRepository.saveAll(List.of(plot1, plot2));
//
////        // Create and save time slots for each plot
////        List<TimeSlot> timeSlotsPlot1 = new ArrayList<>();
////        timeSlotsPlot1.add(new TimeSlot(plot1, LocalTime.of(8, 0), LocalTime.of(10, 0), "idle"));
////        timeSlotsPlot1.add(new TimeSlot(plot1, LocalTime.of(14, 0), LocalTime.of(16, 0), "idle"));
////        timeSlotRepository.saveAll(timeSlotsPlot1);
////
////        List<TimeSlot> timeSlotsPlot2 = new ArrayList<>();
////        timeSlotsPlot2.add(new TimeSlot(plot2, LocalTime.of(9, 0), LocalTime.of(11, 0), "idle"));
////        timeSlotsPlot2.add(new TimeSlot(plot2, LocalTime.of(15, 0), LocalTime.of(17, 0), "idle"));
////        timeSlotRepository.saveAll(timeSlotsPlot2);
//
//        // Print the saved plots and associated data
//        System.out.println("Plots and associated data seeded successfully:");
//        System.out.println(plot1);
//        System.out.println(sensor1);
////        timeSlotsPlot1.forEach(System.out::println);
//        System.out.println(plot2);
//        System.out.println(sensor2);
////        timeSlotsPlot2.forEach(System.out::println);
//    }
//    private void generateTimeSlots() throws ChangeSetPersister.NotFoundException {
//        PlotService plotService = null;
//        Plot plot1 = plotService.getPlotById(1L);
//        Plot plot2 = plotService.getPlotById(2L);
//
//        TimeSlotService timeSlotService = null;
//        TimeSlot timeSlot1 = new TimeSlot();
//        timeSlot1.setPlot(plot1);
//        timeSlot1.setStartTime(LocalTime.of(8, 0));
//        timeSlot1.setEndTime(LocalTime.of(10, 0));
//        timeSlot1.setStatus("idle");
//        timeSlotService.addNewTimeSlot(timeSlot1);
//
//        TimeSlot timeSlot2 = new TimeSlot();
//        timeSlot2.setPlot(plot2);
//        timeSlot2.setStartTime(LocalTime.of(14, 0));
//        timeSlot2.setEndTime(LocalTime.of(16, 0));
//        timeSlot2.setStatus("idle");
//        timeSlotService.addNewTimeSlot(timeSlot2);
//
//        // Add more time slots as needed
//    }
//}
