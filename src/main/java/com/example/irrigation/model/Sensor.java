package com.example.irrigation.model;

import javax.persistence.*;

@Entity
@Table(name = "sensors")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.EAGER) // Fetch association eagerly
    @JoinColumn(name = "plot_id", nullable = false)
    private Plot plot;
    @Column(name = "sensor_id", nullable = false, unique = true)
    private String sensorId;

    @Column(name = "irrigation_enabled", nullable = false)
    private boolean irrigationEnabled;

    // Constructors, getters, and setters

    public Sensor() {
    }

    public Sensor(String sensorId, boolean irrigationEnabled) {
        this.sensorId = sensorId;
        this.irrigationEnabled = irrigationEnabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public boolean isIrrigationEnabled() {
        return irrigationEnabled;
    }

    public void setIrrigationEnabled(boolean irrigationEnabled) {
        this.irrigationEnabled = irrigationEnabled;
    }

    public Plot getPlot() {
        return plot;
    }

    public void setPlot(Plot plot) {
        this.plot = plot;
    }
}
