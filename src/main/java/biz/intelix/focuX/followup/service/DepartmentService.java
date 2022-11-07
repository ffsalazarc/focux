package biz.intelix.focuX.followup.service;

import biz.intelix.focuX.followup.model.Department;
import biz.intelix.focuX.followup.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public ResponseEntity<Department> findDepartmentById(Integer id) {
        Department department = departmentRepository.findDepartmentById(id);
        return ResponseEntity.ok().body(department);
    }

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Department newDepartment, Integer id) {

        return departmentRepository.findById(id)
                .map(department -> {
                    department.setId(newDepartment.getId());
                    department.setCode(newDepartment.getCode());
                    department.setName(newDepartment.getName());
                    department.setDescription(newDepartment.getDescription());
                    department.setUpdated(newDepartment.getUpdated());
                    department.setUpdatedBy(newDepartment.getUpdatedBy());
                    return departmentRepository.save(department);
                })
                .orElseGet(() -> {
                    newDepartment.setId(id);
                    return departmentRepository.save(newDepartment);
                });
    }

    public Department updateDepartmentStatus(Integer id, Department department) {

        return departmentRepository.findById(id)
                .map(businessTypeWithNewStatus -> {
                    businessTypeWithNewStatus.setIsActive(department.getIsActive());
                    businessTypeWithNewStatus.setUpdated(department.getUpdated());
                    businessTypeWithNewStatus.setUpdatedBy(department.getUpdatedBy());
                    return departmentRepository.save(businessTypeWithNewStatus);
                }).get();

    }
}
