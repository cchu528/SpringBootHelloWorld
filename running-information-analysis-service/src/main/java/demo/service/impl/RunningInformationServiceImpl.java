package demo.service.impl;

import demo.domain.RunningInformation;
import demo.domain.RunningInformationRepository;
import demo.service.RunningInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunningInformationServiceImpl implements RunningInformationService {
    private RunningInformationRepository runningInformationRepository;

    // constructor dependency injection
    @Autowired
    public RunningInformationServiceImpl(RunningInformationRepository runningInformationRepository) {
        this.runningInformationRepository = runningInformationRepository;
    }

    @Override
    public List<RunningInformation> saveRunningInformation(List<RunningInformation> runningInformationList) {
        return runningInformationRepository.save(runningInformationList);
    }

    @Override
    public Page<RunningInformation> findByHeartRate(int heartRate, Pageable pageable) {
        return runningInformationRepository.findByHeartRate(heartRate, pageable);
    }

    @Override
    public Page<RunningInformation> findByHeartRateGreaterThan(int heartRate, Pageable pageable) {
        return runningInformationRepository.findByHeartRateGreaterThan(heartRate, pageable);
    }

    @Override
    public Page<RunningInformation> findAllRunningInformations(Pageable pageable) {
        return runningInformationRepository.findAll(pageable);
    }

    @Override
    public Page<RunningInformation> findByHealthWarningLevel(String healthWarningLevel, Pageable pageable) {
        return runningInformationRepository.findByHealthWarningLevel(RunningInformation.HealthWarningLevel.valueOf(healthWarningLevel), pageable);
    }

    @Override
    public RunningInformation findByRunningId(String runningId) {
        return runningInformationRepository.findFirstByRunningId(runningId);
    }

    @Override
    public void deleteByRunningId(String runningId) {
        runningInformationRepository.deleteByRunningId(runningId);
    }

    @Override
    public void deleteAll() {
        runningInformationRepository.deleteAll();
    }
}
