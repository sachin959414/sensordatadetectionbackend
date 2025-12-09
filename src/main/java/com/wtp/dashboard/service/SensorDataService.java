package com.wtp.dashboard.service;

import com.wtp.dashboard.dto.SensorReadingDTO;
import com.wtp.dashboard.entity.ProcessStage;
import com.wtp.dashboard.entity.SensorReading;
import com.wtp.dashboard.repository.ProcessStageRepository;
import com.wtp.dashboard.repository.SensorReadingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SensorDataService {
    
    private final SensorReadingRepository sensorReadingRepository;
    private final ProcessStageRepository processStageRepository;
    private final ModelMapper modelMapper;
    
    @Autowired
    public SensorDataService(SensorReadingRepository sensorReadingRepository,
                           ProcessStageRepository processStageRepository,
                           ModelMapper modelMapper) {
        this.sensorReadingRepository = sensorReadingRepository;
        this.processStageRepository = processStageRepository;
        this.modelMapper = modelMapper;
    }
    
    /**
     * Get latest readings for all process stages
     */
    @Transactional(readOnly = true)
    public List<SensorReadingDTO> getLatestReadings() {
        List<ProcessStage> activeStages = processStageRepository.findByIsActiveTrue();
        return activeStages.stream()
                .flatMap(stage -> sensorReadingRepository.findLatestReadingsByProcessStageId(stage.getId()).stream())
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Get readings by OEM
     */
    @Transactional(readOnly = true)
    public List<SensorReadingDTO> getReadingsByOem(String oem) {
        return sensorReadingRepository.findByOem(oem).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Get historical data for a specific parameter and process stage
     */
    @Transactional(readOnly = true)
    public List<SensorReadingDTO> getHistoricalData(Long stageId, String parameter, 
                                                   LocalDateTime startTime, LocalDateTime endTime) {
        return sensorReadingRepository.findByProcessStageAndParameterBetween(
                stageId, parameter, startTime, endTime).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Get all alert readings
     */
    @Transactional(readOnly = true)
    public List<SensorReadingDTO> getAlertReadings() {
        return sensorReadingRepository.findByIsAlertTrueOrderByTimestampDesc().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Get recent readings with pagination
     */
    @Transactional(readOnly = true)
    public Page<SensorReadingDTO> getRecentReadings(int page, int size) {
        LocalDateTime since = LocalDateTime.now().minusHours(24);
        Pageable pageable = PageRequest.of(page, size);
        return sensorReadingRepository.findRecentReadings(since, pageable)
                .map(this::convertToDTO);
    }
    
    /**
     * Get all available parameters
     */
    @Transactional(readOnly = true)
    public List<String> getAvailableParameters() {
        return sensorReadingRepository.findDistinctParameterNames();
    }
    
    /**
     * Get all active process stages
     */
    @Transactional(readOnly = true)
    public List<ProcessStage> getActiveProcessStages() {
        return processStageRepository.findByIsActiveTrue();
    }
    
    /**
     * Get all unique OEMs
     */
    @Transactional(readOnly = true)
    public List<String> getAllOems() {
        return processStageRepository.findDistinctOems();
    }
    
    /**
     * Get latest readings for a specific parameter across all stages
     */
    @Transactional(readOnly = true)
    public List<SensorReadingDTO> getLatestReadingsByParameter(String parameter) {
        return sensorReadingRepository.findLatestReadingsByParameter(parameter).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Get dashboard statistics
     */
    @Transactional(readOnly = true)
    public DashboardStats getDashboardStats() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime last24Hours = now.minusHours(24);
        
        long totalReadings = sensorReadingRepository.countTotalReadings();
        long recentAlerts = sensorReadingRepository.countAlertsBetween(last24Hours, now);
        long activeStages = processStageRepository.findByIsActiveTrue().size();
        long totalParameters = sensorReadingRepository.findDistinctParameterNames().size();
        
        return new DashboardStats(totalReadings, recentAlerts, activeStages, totalParameters);
    }
    
    /**
     * Convert SensorReading entity to DTO
     */
    private SensorReadingDTO convertToDTO(SensorReading reading) {
        SensorReadingDTO dto = modelMapper.map(reading, SensorReadingDTO.class);
        dto.setProcessStage(reading.getProcessStage().getStageName());
        dto.setOem(reading.getProcessStage().getOem());
        return dto;
    }
    
    /**
     * Dashboard statistics inner class
     */
    public static class DashboardStats {
        private final long totalReadings;
        private final long recentAlerts;
        private final long activeStages;
        private final long totalParameters;
        
        public DashboardStats(long totalReadings, long recentAlerts, long activeStages, long totalParameters) {
            this.totalReadings = totalReadings;
            this.recentAlerts = recentAlerts;
            this.activeStages = activeStages;
            this.totalParameters = totalParameters;
        }
        
        // Getters
        public long getTotalReadings() { return totalReadings; }
        public long getRecentAlerts() { return recentAlerts; }
        public long getActiveStages() { return activeStages; }
        public long getTotalParameters() { return totalParameters; }
    }
}
