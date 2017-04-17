package demo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface RunningInformationRepository extends JpaRepository<RunningInformation, Long> {

    Page<RunningInformation> findByHeartRateGreaterThan(@Param("heartRate") int heartRate, Pageable pageable);
    Page<RunningInformation> findByHeartRate(@Param("heartRate") int heartRate, Pageable pageable);
    Page<RunningInformation> findByHealthWarningLevel(@Param("healthWarningLevel") RunningInformation.HealthWarningLevel healthWarningLevel, Pageable pageable);
    Page<RunningInformation> findAll(Pageable pageable);

    RunningInformation findFirstByRunningId(@Param("runningId") String runningId);

    @Transactional
    void deleteByRunningId(@Param("runningId") String runningId);
}