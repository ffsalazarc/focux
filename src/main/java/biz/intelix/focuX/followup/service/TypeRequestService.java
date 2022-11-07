package biz.intelix.focuX.followup.service;

import biz.intelix.focuX.followup.model.TypeRequest;
import biz.intelix.focuX.followup.repository.TypeRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeRequestService {

    private final TypeRequestRepository typeRequestRepository;

    @Autowired
    public TypeRequestService(TypeRequestRepository typeRequestRepository) {
        this.typeRequestRepository = typeRequestRepository;
    }

    public List<TypeRequest> getAllTypeRequests() {
        return typeRequestRepository.findAll();
    }

    public ResponseEntity<TypeRequest> findTypeRequestById(Integer id) {
        TypeRequest typeRequest = typeRequestRepository.findTypeRequestById(id);
        return ResponseEntity.ok().body(typeRequest);
    }

    public TypeRequest saveTypeRequest(TypeRequest typeRequest) {
        return typeRequestRepository.save(typeRequest);
    }

    public TypeRequest updateTypeRequest(TypeRequest newTypeRequest, Integer id) {

        return typeRequestRepository.findById(id)
                .map(typeRequest -> {
                    typeRequest.setId(newTypeRequest.getId());
                    typeRequest.setCode(newTypeRequest.getCode());
                    typeRequest.setName(newTypeRequest.getName());
                    typeRequest.setDescription(newTypeRequest.getDescription());
                    typeRequest.setUpdated(newTypeRequest.getUpdated());
                    typeRequest.setUpdatedBy(newTypeRequest.getUpdatedBy());
                    return typeRequestRepository.save(typeRequest);
                })
                .orElseGet(() -> {
                    newTypeRequest.setId(id);
                    return typeRequestRepository.save(newTypeRequest);
                });
    }

    public TypeRequest updateTypeRequestStatus(Integer id, TypeRequest typeRequest) {

        return typeRequestRepository.findById(id)
                .map(typeRequestWithNewStatus -> {
                    typeRequestWithNewStatus.setIsActive(typeRequest.getIsActive());
                    typeRequestWithNewStatus.setUpdated(typeRequest.getUpdated());
                    typeRequestWithNewStatus.setUpdatedBy(typeRequest.getUpdatedBy());
                    return typeRequestRepository.save(typeRequestWithNewStatus);
                }).get();

    }
}
