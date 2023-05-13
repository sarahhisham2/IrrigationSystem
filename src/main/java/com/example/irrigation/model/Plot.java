package com.example.irrigation.model;

import javax.persistence.*;

@Entity
@Table(name = "plots")
public class Plot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double area;

    @Column(name = "soil_type", nullable = false)
    private String soilType;

    @Column(name = "crop_type")
    private String cropType;

    @Column(name = "irrigation_threshold")
    private Double irrigationThreshold;



    // Constructors, getters, and setters

    public Plot() {
    }

    public Plot(String name, Double area,String cropType,Double irrigationThreshold,String soilType) {
        this.name = name;
        this.area = area;
        this.cropType = cropType;
        this.irrigationThreshold = irrigationThreshold;
        this.soilType = soilType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getSoilType() {
        return soilType;
    }

    public void setSoilType(String soilType) {
        this.soilType = soilType;
    }

    public String getCropType() {
        return cropType;
    }

    public void setCropType(String cropType) {
        this.cropType = cropType;
    }

    public Double getIrrigationThreshold() {
        return irrigationThreshold;
    }

    public void setIrrigationThreshold(Double irrigationThreshold) {
        this.irrigationThreshold = irrigationThreshold;
    }

}



