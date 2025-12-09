package com.wtp.dashboard.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sensor_readings")
@NamedQueries({
    @NamedQuery(
        name = "SensorReading.findByProcessStageAndParameter",
        query = "SELECT sr FROM SensorReading sr WHERE sr.processStage.id = :stageId AND sr.parameterName = :parameterName ORDER BY sr.timestamp DESC"
    ),
    @NamedQuery(
        name = "SensorReading.findLatestByProcessStage",
        query = "SELECT sr FROM SensorReading sr WHERE sr.processStage.id = :stageId ORDER BY sr.timestamp DESC"
    )
})
public class SensorReading {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "process_stage_id", nullable = false)
    private ProcessStage processStage;
    
    @Column(name = "parameter_name", nullable = false)
    private String parameterName;
    
    @Column(name = "sensor_value", nullable = false)
    private Double value;
    
    @Column(name = "unit")
    private String unit;
    
    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;
    
    @Column(name = "threshold_min")
    private Double thresholdMin;
    
    @Column(name = "threshold_max")
    private Double thresholdMax;
    
    @Column(name = "is_alert")
    private Boolean isAlert = false;
    
    @Column(name = "sensor_id")
    private String sensorId;
    
    @Column(name = "equipment_name")
    private String equipmentName;
    
    // Constructors
    public SensorReading() {
        this.timestamp = LocalDateTime.now();
    }
    
    public SensorReading(ProcessStage processStage, String parameterName, Double value, String unit) {
        this();
        this.processStage = processStage;
        this.parameterName = parameterName;
        this.value = value;
        this.unit = unit;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public ProcessStage getProcessStage() {
        return processStage;
    }
    
    public void setProcessStage(ProcessStage processStage) {
        this.processStage = processStage;
    }
    
    public String getParameterName() {
        return parameterName;
    }
    
    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }
    
    public Double getValue() {
        return value;
    }
    
    public void setValue(Double value) {
        this.value = value;
        this.checkThresholds();
    }
    
    public String getUnit() {
        return unit;
    }
    
    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    public Double getThresholdMin() {
        return thresholdMin;
    }
    
    public void setThresholdMin(Double thresholdMin) {
        this.thresholdMin = thresholdMin;
        this.checkThresholds();
    }
    
    public Double getThresholdMax() {
        return thresholdMax;
    }
    
    public void setThresholdMax(Double thresholdMax) {
        this.thresholdMax = thresholdMax;
        this.checkThresholds();
    }
    
    public Boolean getIsAlert() {
        return isAlert;
    }
    
    public void setIsAlert(Boolean isAlert) {
        this.isAlert = isAlert;
    }
    
    public String getSensorId() {
        return sensorId;
    }
    
    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }
    
    public String getEquipmentName() {
        return equipmentName;
    }
    
    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }
    
    // Helper method to check thresholds and set alert
    private void checkThresholds() {
        if (value != null) {
            boolean alert = false;
            if (thresholdMin != null && value < thresholdMin) {
                alert = true;
            }
            if (thresholdMax != null && value > thresholdMax) {
                alert = true;
            }
            this.isAlert = alert;
        }
    }
}
