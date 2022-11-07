package biz.intelix.focuX.followup.service;

import biz.intelix.focuX.followup.model.CommercialArea;
import biz.intelix.focuX.followup.repository.CommercialAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommercialAreaService {

    private final CommercialAreaRepository commercialAreaRepository;

    @Autowired
    public CommercialAreaService(CommercialAreaRepository commercialAreaRepository) {
        this.commercialAreaRepository = commercialAreaRepository;
    }

    public List<CommercialArea> getAllCommercialAreas() {
        return commercialAreaRepository.findAll();
    }

    public ResponseEntity<CommercialArea> findCommercialAreaById(Integer id) {
        CommercialArea commercialArea = commercialAreaRepository.findCommercialAreaById(id);
        return ResponseEntity.ok().body(commercialArea);
    }

    public CommercialArea saveCommercialArea(CommercialArea commercialArea) {
        return commercialAreaRepository.save(commercialArea);
    }

    public CommercialArea updateCommercialArea(CommercialArea newCommercialArea, Integer id) {

        return commercialAreaRepository.findById(id)
                .map(commercialArea -> {
                    commercialArea.setId(newCommercialArea.getId());
                    commercialArea.setCode(newCommercialArea.getCode());
                    commercialArea.setName(newCommercialArea.getName());
                    commercialArea.setDescription(newCommercialArea.getDescription());
                    commercialArea.setUpdated(newCommercialArea.getUpdated());
                    commercialArea.setUpdatedBy(newCommercialArea.getUpdatedBy());
                    return commercialAreaRepository.save(commercialArea);
                })
                .orElseGet(() -> {
                    newCommercialArea.setId(id);
                    return commercialAreaRepository.save(newCommercialArea);
                });
    }

    public CommercialArea updateCommercialAreaStatus(Integer id, CommercialArea commercialArea) {

        return commercialAreaRepository.findById(id)
                .map(businessTypeWithNewStatus -> {
                    businessTypeWithNewStatus.setIsActive(commercialArea.getIsActive());
                    businessTypeWithNewStatus.setUpdated(commercialArea.getUpdated());
                    businessTypeWithNewStatus.setUpdatedBy(commercialArea.getUpdatedBy());
                    return commercialAreaRepository.save(businessTypeWithNewStatus);
                }).get();

    }
}
