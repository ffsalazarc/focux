package biz.intelix.focuX.followup.controller;

import biz.intelix.focuX.followup.exception.ResourceNotFoundException;
import biz.intelix.focuX.followup.model.RequestRole;
import biz.intelix.focuX.followup.service.RequestRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/followup/requestrole")
@CrossOrigin("*")
public class RequestRoleController {

    private RequestRoleService requestRoleService;

    @Autowired
    public RequestRoleController(RequestRoleService requestRoleService) {
        this.requestRoleService = requestRoleService;
    }

    @GetMapping("/all")
    public List<RequestRole> getAllRequestRole() {
        return requestRoleService.getAllRequestRoles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestRole> findRequestRoleByIdFile(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        return requestRoleService.findRequestRoleById(id);
    }

    @PostMapping("/save")
    public RequestRole createRequestRole(@RequestBody RequestRole requestRole)
            throws ParseException {
        return requestRoleService.saveRequestRole(requestRole);
    }

    @PutMapping("/requestrole/{id}")
    public RequestRole updateRequestRole(@RequestBody RequestRole newRequestRole, @PathVariable(value = "id") Integer id)
            throws ParseException {
        return requestRoleService.updateRequestRole(newRequestRole, id);
    }

    @PutMapping("/status/{id}")
    public RequestRole updateRequestRoleStatus(@RequestBody RequestRole requestRole, @PathVariable(value = "id") Integer id)
            throws ParseException {
        return requestRoleService.updateRequestRoleStatus(id, requestRole);
    }
}
