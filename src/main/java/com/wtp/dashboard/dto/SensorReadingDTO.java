package com.wtp.dashboard.dto;

import java.time.LocalDateTime;

public class SensorReadingDTO {
    
    private Long id;
    private String processStage;
    private String oem;
    private String parameterName;
    private Double value;
    private String unit;
    private LocalDateTime timestamp;
    private Boolean isAlert;
    private String sensorId;
    private String equipmentName;
    private Double thresholdMin;
    private Double thresholdMax;
    
    // Constructors
    public SensorReadingDTO() {}
    
    public SensorReadingDTO(String processStage, String parameterName, Double value, String unit) {
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
    
    public String getProcessStage() {
        return processStage;
    }
    
    public void setProcessStage(String processStage) {
        this.processStage = processStage;
    }
    
    public String getOem() {
        return oem;
    }
    
    public void setOem(String oem) {
        this.oem = oem;
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
    
    public Double getThresholdMin() {
        return thresholdMin;
    }
    
    public void setThresholdMin(Double thresholdMin) {
        this.thresholdMin = thresholdMin;
    }
    
    public Double getThresholdMax() {
        return thresholdMax;
    }
    
    public void setThresholdMax(Double thresholdMax) {
        this.thresholdMax = thresholdMax;
    }
}
