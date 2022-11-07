package biz.intelix.focuX.followup.service;

import biz.intelix.focuX.followup.model.TypeStatus;
import biz.intelix.focuX.followup.repository.TypeStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeStatusService {

    private final TypeStatusRepository typeStatusRepository;

    @Autowired
    public TypeStatusService(TypeStatusRepository typeStatusRepository) {
        this.typeStatusRepository = typeStatusRepository;
    }

    public List<TypeStatus> getAllTypeStatuses() {
        return typeStatusRepository.findAll();
    }

    public ResponseEntity<TypeStatus> findTypeStatusById(Integer id) {
        TypeStatus typeRequest = typeStatusRepository.findTypeStatusById(id);
        return ResponseEntity.ok().body(typeRequest);
    }

    public TypeStatus saveTypeStatus(TypeStatus typeStatus) {
        return typeStatusRepository.save(typeStatus);
    }

    public TypeStatus updateTypeStatus(TypeStatus newTypeStatus, Integer id) {

        return typeStatusRepository.findById(id)
                .map(typeStatus -> {
                    typeStatus.setId(newTypeStatus.getId());
                    typeStatus.setCode(newTypeStatus.getCode());
                    typeStatus.setName(newTypeStatus.getName());
                    typeStatus.setDescription(newTypeStatus.getDescription());
                    typeStatus.setUpdated(newTypeStatus.getUpdated());
                    typeStatus.setUpdatedBy(newTypeStatus.getUpdatedBy());
                    return typeStatusRepository.save(typeStatus);
                })
                .orElseGet(() -> {
                    newTypeStatus.setId(id);
                    return typeStatusRepository.save(newTypeStatus);
                });
    }

    public TypeStatus updateTypeStatusStatus(Integer id, TypeStatus typeStatus) {

        return typeStatusRepository.findById(id)
                .map(typeStatusWithNewStatus -> {
                    typeStatusWithNewStatus.setIsActive(typeStatus.getIsActive());
                    typeStatusWithNewStatus.setUpdated(typeStatus.getUpdated());
                    typeStatusWithNewStatus.setUpdatedBy(typeStatus.getUpdatedBy());
                    return typeStatusRepository.save(typeStatusWithNewStatus);
                }).get();

    }
}
