package biz.intelix.focuX.followup.service;

import biz.intelix.focuX.followup.model.Indicator;
import biz.intelix.focuX.followup.repository.IndicatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndicatorService {

    private final IndicatorRepository indicatorRepository;

    @Autowired
    public IndicatorService(IndicatorRepository indicatorRepository) {
        this.indicatorRepository = indicatorRepository;
    }

    public List<Indicator> getAllIndicator() {
        return indicatorRepository.findAll();
    }

    public ResponseEntity<Indicator> findIndicatorById(Integer id) {
        Indicator indicator = indicatorRepository.findIndicatorById(id);
        return ResponseEntity.ok().body(indicator);
    }

    public Indicator saveIndicator(Indicator indicator) {
        return indicatorRepository.save(indicator);
    }

    public Indicator updateIndicator(Indicator newIndicator, Integer id) {

        return indicatorRepository.findById(id)
                .map(client -> {
                    client.setId(newIndicator.getId());
                    client.setType(newIndicator.getType());
                    client.setName(newIndicator.getName());
                    client.setDescription(newIndicator.getDescription());
                    return indicatorRepository.save(client);
                })
                .orElseGet(() -> {
                    newIndicator.setId(id);
                    return indicatorRepository.save(newIndicator);
                });
    }

    public Indicator updateIndicatorStatus(Integer id, Indicator indicator) {

        return indicatorRepository.findById(id)
                .map(clientWithNewStatus -> {
                    clientWithNewStatus.setIsActive(indicator.getIsActive());
                    return indicatorRepository.save(clientWithNewStatus);
                }).get();

    }
}
