package biz.intelix.focuX.followup.service;

import biz.intelix.focuX.followup.model.EmployeePosition;
import biz.intelix.focuX.followup.repository.EmployeePositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeePositionService {

    private final EmployeePositionRepository employeePositionRepository;

    @Autowired
    public EmployeePositionService(EmployeePositionRepository employeePositionRepository) {
        this.employeePositionRepository = employeePositionRepository;
    }

    public List<EmployeePosition> getAllEmployeePositions() {
        return employeePositionRepository.findAll();
    }

    public ResponseEntity<EmployeePosition> findEmployeePositionById(Integer id) {
        EmployeePosition employeePosition = employeePositionRepository.findEmployeePositionById(id);
        return ResponseEntity.ok().body(employeePosition);
    }

    public EmployeePosition saveEmployeePosition(EmployeePosition employeePosition) {
        return employeePositionRepository.save(employeePosition);
    }

    public EmployeePosition updateEmployeePosition(EmployeePosition newEmployeePosition, Integer id) {

        return employeePositionRepository.findById(id)
                .map(employeePosition -> {
                    employeePosition.setId(newEmployeePosition.getId());
                    employeePosition.setDepartment(newEmployeePosition.getDepartment());
                    employeePosition.setName(newEmployeePosition.getName());                    
                    employeePosition.setDescription(newEmployeePosition.getDescription());
                    employeePosition.setUpdated(newEmployeePosition.getUpdated());
                    employeePosition.setUpdatedBy(newEmployeePosition.getUpdatedBy());
                    return employeePositionRepository.save(employeePosition);
                })
                .orElseGet(() -> {
                    newEmployeePosition.setId(id);
                    return employeePositionRepository.save(newEmployeePosition);
                });
    }

    public EmployeePosition updateEmployeePositionStatus(Integer id, EmployeePosition employeePosition) {

        return employeePositionRepository.findById(id)
                .map(employeePositionWithNewStatus -> {
                    employeePositionWithNewStatus.setIsActive(employeePosition.getIsActive());
                    employeePositionWithNewStatus.setUpdated(employeePosition.getUpdated());
                    employeePositionWithNewStatus.setUpdatedBy(employeePosition.getUpdatedBy());
                    return employeePositionRepository.save(employeePositionWithNewStatus);
                }).get();

    }
}
