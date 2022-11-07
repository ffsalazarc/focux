package biz.intelix.focuX.followup.service;

import biz.intelix.focuX.followup.model.TechnicalArea;
import biz.intelix.focuX.followup.repository.TechnicalAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnicalAreaService {

    private final TechnicalAreaRepository technicalAreaRepository;

    @Autowired
    public TechnicalAreaService(TechnicalAreaRepository technicalAreaRepository) {
        this.technicalAreaRepository = technicalAreaRepository;
    }

    public List<TechnicalArea> getAllTechnicalAreas() {
        return technicalAreaRepository.findAll();
    }

    public ResponseEntity<TechnicalArea> findTechnicalAreaById(Integer id) {
        TechnicalArea technicalArea = technicalAreaRepository.findTechnicalAreaById(id);
        return ResponseEntity.ok().body(technicalArea);
    }

    public TechnicalArea saveTechnicalArea(TechnicalArea technicalArea) {
        return technicalAreaRepository.save(technicalArea);
    }

    public TechnicalArea updateTechnicalArea(TechnicalArea newTechnicalArea, Integer id) {

        return technicalAreaRepository.findById(id)
                .map(technicalArea -> {
                    technicalArea.setId(newTechnicalArea.getId());
                    technicalArea.setCode(newTechnicalArea.getCode());
                    technicalArea.setName(newTechnicalArea.getName());
                    technicalArea.setDescription(newTechnicalArea.getDescription());
                    technicalArea.setUpdated(newTechnicalArea.getUpdated());
                    technicalArea.setUpdatedBy(newTechnicalArea.getUpdatedBy());
                    return technicalAreaRepository.save(technicalArea);
                })
                .orElseGet(() -> {
                    newTechnicalArea.setId(id);
                    return technicalAreaRepository.save(newTechnicalArea);
                });
    }

    public TechnicalArea updateTechnicalAreaStatus(Integer id, TechnicalArea technicalArea) {

        return technicalAreaRepository.findById(id)
                .map(technicalAreaWithNewStatus -> {
                    technicalAreaWithNewStatus.setIsActive(technicalArea.getIsActive());
                    technicalAreaWithNewStatus.setUpdated(technicalArea.getUpdated());
                    technicalAreaWithNewStatus.setUpdatedBy(technicalArea.getUpdatedBy());
                    return technicalAreaRepository.save(technicalAreaWithNewStatus);
                }).get();

    }
}
