package com.wtp.dashboard.repository;

import com.wtp.dashboard.entity.SensorReading;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SensorReadingRepository extends JpaRepository<SensorReading, Long> {
    
    /**
     * Find latest readings for each parameter in a process stage
     */
    @Query("SELECT sr FROM SensorReading sr WHERE sr.processStage.id = :stageId " +
           "AND sr.timestamp = (SELECT MAX(sr2.timestamp) FROM SensorReading sr2 " +
           "WHERE sr2.processStage.id = sr.processStage.id AND sr2.parameterName = sr.parameterName)")
    List<SensorReading> findLatestReadingsByProcessStageId(@Param("stageId") Long stageId);
    
    /**
     * Find readings by process stage and parameter with time range
     */
    @Query("SELECT sr FROM SensorReading sr WHERE sr.processStage.id = :stageId " +
           "AND sr.parameterName = :parameterName AND sr.timestamp BETWEEN :startTime AND :endTime " +
           "ORDER BY sr.timestamp DESC")
    List<SensorReading> findByProcessStageAndParameterBetween(
            @Param("stageId") Long stageId,
            @Param("parameterName") String parameterName,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );
    
    /**
     * Find all alert readings
     */
    List<SensorReading> findByIsAlertTrueOrderByTimestampDesc();
    
    /**
     * Find readings by OEM
     */
    @Query("SELECT sr FROM SensorReading sr WHERE sr.processStage.oem = :oem ORDER BY sr.timestamp DESC")
    List<SensorReading> findByOem(@Param("oem") String oem);
    
    /**
     * Find recent readings (last 24 hours) with pagination
     */
    @Query("SELECT sr FROM SensorReading sr WHERE sr.timestamp > :since ORDER BY sr.timestamp DESC")
    Page<SensorReading> findRecentReadings(@Param("since") LocalDateTime since, Pageable pageable);
    
    /**
     * Find distinct parameter names
     */
    @Query("SELECT DISTINCT sr.parameterName FROM SensorReading sr")
    List<String> findDistinctParameterNames();
    
    /**
     * Get latest reading for a specific parameter across all stages
     */
    @Query("SELECT sr FROM SensorReading sr WHERE sr.parameterName = :parameterName " +
           "AND sr.timestamp = (SELECT MAX(sr2.timestamp) FROM SensorReading sr2 " +
           "WHERE sr2.parameterName = sr.parameterName AND sr2.processStage.id = sr.processStage.id)")
    List<SensorReading> findLatestReadingsByParameter(@Param("parameterName") String parameterName);
    
    /**
     * Count total readings
     */
    @Query("SELECT COUNT(sr) FROM SensorReading sr")
    Long countTotalReadings();
    
    /**
     * Count alert readings in time range
     */
    @Query("SELECT COUNT(sr) FROM SensorReading sr WHERE sr.isAlert = true " +
           "AND sr.timestamp BETWEEN :startTime AND :endTime")
    Long countAlertsBetween(@Param("startTime") LocalDateTime startTime, 
                           @Param("endTime") LocalDateTime endTime);
}
