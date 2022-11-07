package biz.intelix.focuX.followup.service;

import biz.intelix.focuX.followup.model.BusinessType;
import biz.intelix.focuX.followup.repository.BusinessTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessTypeService {

    private final BusinessTypeRepository businessTypeRepository;

    @Autowired
    public BusinessTypeService(BusinessTypeRepository businessTypeRepository) {
        this.businessTypeRepository = businessTypeRepository;
    }

    public List<BusinessType> getAllBusinessTypes() {
        return businessTypeRepository.findAll();
    }

    public ResponseEntity<BusinessType> findBusinessTypeById(Integer id) {
        BusinessType businessType = businessTypeRepository.findBusinessTypeById(id);
        return ResponseEntity.ok().body(businessType);
    }

    public BusinessType saveBusinessType(BusinessType businessType) {
        return businessTypeRepository.save(businessType);
    }

    public BusinessType updateBusinessType(BusinessType newBusinessType, Integer id) {

        return businessTypeRepository.findById(id)
                .map(businessType -> {
                    businessType.setId(newBusinessType.getId());
                    businessType.setCode(newBusinessType.getCode());
                    businessType.setName(newBusinessType.getName());
                    businessType.setDescription(newBusinessType.getDescription());
                    businessType.setUpdated(newBusinessType.getUpdated());
                    businessType.setUpdatedBy(newBusinessType.getUpdatedBy());

                    return businessTypeRepository.save(businessType);
                })
                .orElseGet(() -> {
                    newBusinessType.setId(id);
                    return businessTypeRepository.save(newBusinessType);
                });
    }

    public BusinessType updateBusinessTypeStatus(Integer id, BusinessType businessType) {

        return businessTypeRepository.findById(id)
                .map(businessTypeWithNewStatus -> {
                    businessTypeWithNewStatus.setIsActive(businessType.getIsActive());
                    businessTypeWithNewStatus.setUpdated(businessType.getUpdated());
                    businessTypeWithNewStatus.setUpdatedBy(businessType.getUpdatedBy());
                    return businessTypeRepository.save(businessTypeWithNewStatus);
                }).get();

    }
}
