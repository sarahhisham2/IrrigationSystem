package com.example.irrigation.service;

import com.example.irrigation.model.Plot;
import com.example.irrigation.repository.PlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlotService {

    private final PlotRepository plotRepository;

    @Autowired
    public PlotService(PlotRepository plotRepository) {
        this.plotRepository = plotRepository;
    }

    public Plot addNewPlot(Plot plot) {
        return plotRepository.save(plot);
    }

    public Plot configurePlot(Long plotId, String cropType, Double irrigationThreshold) {
        Optional<Plot> optionalPlot = plotRepository.findById(plotId);
        if (optionalPlot.isPresent()) {
            Plot plot = optionalPlot.get();
            plot.setCropType(cropType);
            plot.setIrrigationThreshold(irrigationThreshold);
            return plotRepository.save(plot);
        }
        throw new IllegalArgumentException("Plot not found with ID: " + plotId);
    }

    public Plot editPlot(Long plotId, Plot updatedPlot) {
        Optional<Plot> optionalPlot = plotRepository.findById(plotId);
        if (optionalPlot.isPresent()) {
            Plot plot = optionalPlot.get();
            plot.setName(updatedPlot.getName());
            plot.setArea(updatedPlot.getArea());
            plot.setSoilType(updatedPlot.getSoilType());
            plot.setCropType(updatedPlot.getCropType());
            plot.setIrrigationThreshold(updatedPlot.getIrrigationThreshold());
//            plot.setSensor(updatedPlot.getSensor());
//            plot.setTimeSlots(updatedPlot.getTimeSlots());
            return plotRepository.save(plot);
        }
        throw new IllegalArgumentException("Plot not found with ID: " + plotId);
    }

    public List<Plot> getAllPlots() {
        return plotRepository.findAll();
    }

    public Plot getPlotById(Long id) throws ChangeSetPersister.NotFoundException {
        return plotRepository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }
}

