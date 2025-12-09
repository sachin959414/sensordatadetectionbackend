package com.wtp.dashboard.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "process_stages")
public class ProcessStage {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "stage_name", nullable = false)
    private String stageName;
    
    @Column(name = "oem", nullable = false)
    private String oem;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "is_active")
    private Boolean isActive = true;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "processStage", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SensorReading> sensorReadings;
    
    // Constructors
    public ProcessStage() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    public ProcessStage(String stageName, String oem) {
        this();
        this.stageName = stageName;
        this.oem = oem;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getStageName() {
        return stageName;
    }
    
    public void setStageName(String stageName) {
        this.stageName = stageName;
    }
    
    public String getOem() {
        return oem;
    }
    
    public void setOem(String oem) {
        this.oem = oem;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Boolean getIsActive() {
        return isActive;
    }
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public List<SensorReading> getSensorReadings() {
        return sensorReadings;
    }
    
    public void setSensorReadings(List<SensorReading> sensorReadings) {
        this.sensorReadings = sensorReadings;
    }
    
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
