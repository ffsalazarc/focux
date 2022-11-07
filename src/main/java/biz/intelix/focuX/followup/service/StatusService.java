package biz.intelix.focuX.followup.service;

import biz.intelix.focuX.followup.model.Status;
import biz.intelix.focuX.followup.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {

    private final StatusRepository statusRepository;

    @Autowired
    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<Status> getAllStatus() {
        return statusRepository.findAll();
    }

    public ResponseEntity<Status> findStatusById(Integer id) {
        Status status = statusRepository.findStatusById(id);
        return ResponseEntity.ok().body(status);
    }

    public Status saveStatus(Status status) {
        return statusRepository.save(status);
    }

    public Status updateStatus(Status newStatus, Integer id) {

        return statusRepository.findById(id)
                .map(status -> {
                    status.setId(newStatus.getId());
                    status.setTypeStatus(newStatus.getTypeStatus());
                    status.setName(newStatus.getName());
                    status.setDescription(newStatus.getDescription());
                    status.setUpdated(newStatus.getUpdated());
                    status.setUpdatedBy(newStatus.getUpdatedBy());
                    return statusRepository.save(status);
                })
                .orElseGet(() -> {
                    newStatus.setId(id);
                    return statusRepository.save(newStatus);
                });
    }

    public Status updateStatusStatus(Integer id, Status status) {

        return statusRepository.findById(id)
                .map(statusWithNewStatus -> {
                    statusWithNewStatus.setIsActive(status.getIsActive());
                    statusWithNewStatus.setUpdated(status.getUpdated());
                    statusWithNewStatus.setUpdatedBy(status.getUpdatedBy());
                    return statusRepository.save(statusWithNewStatus);
                }).get();

    }
}
