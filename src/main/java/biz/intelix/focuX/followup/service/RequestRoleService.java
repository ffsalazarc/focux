package biz.intelix.focuX.followup.service;

import biz.intelix.focuX.followup.model.RequestRole;
import biz.intelix.focuX.followup.repository.RequestRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestRoleService {

    private final RequestRoleRepository requestRoleRepository;

    @Autowired
    public RequestRoleService(RequestRoleRepository requestRoleRepository) {
        this.requestRoleRepository = requestRoleRepository;
    }

    public List<RequestRole> getAllRequestRoles() {
        return requestRoleRepository.findAll();
    }

    public ResponseEntity<RequestRole> findRequestRoleById(Integer id) {
        RequestRole requestRole = requestRoleRepository.findRequestRoleById(id);
        return ResponseEntity.ok().body(requestRole);
    }

    public RequestRole saveRequestRole(RequestRole requestRole) {
        return requestRoleRepository.save(requestRole);
    }

    public RequestRole updateRequestRole(RequestRole newRequestRole, Integer id) {

        return requestRoleRepository.findById(id)
                .map(requestRole -> {
                    requestRole.setId(newRequestRole.getId());
                    requestRole.setName(newRequestRole.getName());
                    requestRole.setDescription(newRequestRole.getDescription());
                    return requestRoleRepository.save(requestRole);
                })
                .orElseGet(() -> {
                    newRequestRole.setId(id);
                    return requestRoleRepository.save(newRequestRole);
                });
    }

    public RequestRole updateRequestRoleStatus(Integer id, RequestRole requestRole) {

        return requestRoleRepository.findById(id)
                .map(requestRoleWithNewStatus -> {
                    requestRoleWithNewStatus.setIsActive(requestRole.getIsActive());
                    return requestRoleRepository.save(requestRoleWithNewStatus);
                }).get();

    }
}
