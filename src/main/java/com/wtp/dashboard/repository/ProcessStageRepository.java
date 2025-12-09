package com.wtp.dashboard.repository;

import com.wtp.dashboard.entity.ProcessStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProcessStageRepository extends JpaRepository<ProcessStage, Long> {
    
    /**
     * Find all active process stages
     */
    List<ProcessStage> findByIsActiveTrue();
    
    /**
     * Find process stages by OEM
     */
    List<ProcessStage> findByOemAndIsActiveTrue(String oem);
    
    /**
     * Find all unique OEMs
     */
    @Query("SELECT DISTINCT p.oem FROM ProcessStage p WHERE p.isActive = true")
    List<String> findDistinctOems();
    
    /**
     * Find process stage by name
     */
    Optional<ProcessStage> findByStageNameAndIsActiveTrue(String stageName);
    
    /**
     * Count active process stages by OEM
     */
    @Query("SELECT COUNT(p) FROM ProcessStage p WHERE p.oem = :oem AND p.isActive = true")
    Long countByOem(@Param("oem") String oem);
}
