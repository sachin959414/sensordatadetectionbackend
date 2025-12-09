package com.wtp.dashboard.controller;

import com.wtp.dashboard.dto.SensorReadingDTO;
import com.wtp.dashboard.entity.ProcessStage;
import com.wtp.dashboard.service.SensorDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "*")
@Tag(name = "Dashboard API", description = "IoT Dashboard endpoints for WTP monitoring")
public class DashboardController {
    
    private final SensorDataService sensorDataService;
    
    @Autowired
    public DashboardController(SensorDataService sensorDataService) {
        this.sensorDataService = sensorDataService;
    }
    
    @Operation(summary = "Get latest sensor readings", description = "Retrieve the latest readings for all active process stages")
    @GetMapping("/latest-readings")
    public ResponseEntity<List<SensorReadingDTO>> getLatestReadings() {
        List<SensorReadingDTO> readings = sensorDataService.getLatestReadings();
        return ResponseEntity.ok(readings);
    }
    
    @Operation(summary = "Get readings by OEM", description = "Retrieve sensor readings filtered by OEM manufacturer")
    @GetMapping("/readings/oem/{oem}")
    public ResponseEntity<List<SensorReadingDTO>> getReadingsByOem(
            @Parameter(description = "OEM manufacturer name") @PathVariable String oem) {
        List<SensorReadingDTO> readings = sensorDataService.getReadingsByOem(oem);
        return ResponseEntity.ok(readings);
    }
    
    @Operation(summary = "Get historical data", description = "Retrieve historical sensor data for a specific parameter and process stage")
    @GetMapping("/historical")
    public ResponseEntity<List<SensorReadingDTO>> getHistoricalData(
            @Parameter(description = "Process stage ID") @RequestParam Long stageId,
            @Parameter(description = "Parameter name") @RequestParam String parameter,
            @Parameter(description = "Start time (ISO format)") 
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @Parameter(description = "End time (ISO format)") 
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        
        List<SensorReadingDTO> readings = sensorDataService.getHistoricalData(stageId, parameter, startTime, endTime);
        return ResponseEntity.ok(readings);
    }
    
    @Operation(summary = "Get alert readings", description = "Retrieve all current alert readings")
    @GetMapping("/alerts")
    public ResponseEntity<List<SensorReadingDTO>> getAlertReadings() {
        List<SensorReadingDTO> alerts = sensorDataService.getAlertReadings();
        return ResponseEntity.ok(alerts);
    }
    
    @Operation(summary = "Get recent readings", description = "Retrieve recent readings with pagination (last 24 hours)")
    @GetMapping("/recent")
    public ResponseEntity<Page<SensorReadingDTO>> getRecentReadings(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "20") int size) {
        
        Page<SensorReadingDTO> readings = sensorDataService.getRecentReadings(page, size);
        return ResponseEntity.ok(readings);
    }
    
    @Operation(summary = "Get available parameters", description = "Retrieve list of all available sensor parameters")
    @GetMapping("/parameters")
    public ResponseEntity<List<String>> getAvailableParameters() {
        List<String> parameters = sensorDataService.getAvailableParameters();
        return ResponseEntity.ok(parameters);
    }
    
    @Operation(summary = "Get process stages", description = "Retrieve all active process stages")
    @GetMapping("/process-stages")
    public ResponseEntity<List<ProcessStage>> getProcessStages() {
        List<ProcessStage> stages = sensorDataService.getActiveProcessStages();
        return ResponseEntity.ok(stages);
    }
    
    @Operation(summary = "Get OEMs", description = "Retrieve list of all OEM manufacturers")
    @GetMapping("/oems")
    public ResponseEntity<List<String>> getOems() {
        List<String> oems = sensorDataService.getAllOems();
        return ResponseEntity.ok(oems);
    }
    
    @Operation(summary = "Get readings by parameter", description = "Retrieve latest readings for a specific parameter across all stages")
    @GetMapping("/readings/parameter/{parameter}")
    public ResponseEntity<List<SensorReadingDTO>> getReadingsByParameter(
            @Parameter(description = "Parameter name") @PathVariable String parameter) {
        List<SensorReadingDTO> readings = sensorDataService.getLatestReadingsByParameter(parameter);
        return ResponseEntity.ok(readings);
    }
    
    @Operation(summary = "Get dashboard statistics", description = "Retrieve overall dashboard statistics")
    @GetMapping("/stats")
    public ResponseEntity<SensorDataService.DashboardStats> getDashboardStats() {
        SensorDataService.DashboardStats stats = sensorDataService.getDashboardStats();
        return ResponseEntity.ok(stats);
    }
}
