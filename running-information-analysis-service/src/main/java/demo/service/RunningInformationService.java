package demo.service;

import demo.domain.RunningInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface RunningInformationService {

    List<RunningInformation> saveRunningInformation(List<RunningInformation> runningInformationList);

    Page<RunningInformation> findByHeartRate(int heartRate, Pageable pageable);

    Page<RunningInformation> findByHeartRateGreaterThan(int heartRate, Pageable pageable);

    Page<RunningInformation> findAllRunningInformations(Pageable pageable);

    Page<RunningInformation> findByHealthWarningLevel(String healthWarningLevel, org.springframework.data.domain.Pageable pageable);

    RunningInformation findByRunningId(String runningId);

    void deleteByRunningId(String runningId);

    void deleteAll();
}
