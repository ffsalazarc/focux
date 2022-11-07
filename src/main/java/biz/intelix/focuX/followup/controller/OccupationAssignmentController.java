package biz.intelix.focuX.followup.controller;

import biz.intelix.focuX.followup.exception.ResourceNotFoundException;
import biz.intelix.focuX.followup.model.OccupationAssignment;
import biz.intelix.focuX.followup.service.OccupationAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/followup/occupationassignments")
@CrossOrigin("*")
public class OccupationAssignmentController {

    private OccupationAssignmentService occupationAssignmentService;

    @Autowired
    public OccupationAssignmentController(OccupationAssignmentService occupationAssignmentService) {
        this.occupationAssignmentService = occupationAssignmentService;
    }

    @GetMapping("/all")
    public List<OccupationAssignment> getAllOccupationAssignment() {
        return occupationAssignmentService.getAllOccupationAssignments();
    }

    @GetMapping("/collaborator/{id}")
    public ResponseEntity<List<OccupationAssignment>> findOccupationAssignmentsByIdCollaborator(
            @PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        return occupationAssignmentService.findOccupationAssignmentsByIdCollaborator(id);
    }

    @GetMapping("/request/{id}")
    public ResponseEntity<Set<OccupationAssignment>> findOccupationAssignmentsByIdRequest(
            @PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        return occupationAssignmentService.findOccupationAssignmentsByIdRequest(id);
    }

    @PostMapping("/save")
    public OccupationAssignment createOccupationAssignment(@RequestBody OccupationAssignment ocupationAssigment)
            throws ParseException {
        return occupationAssignmentService.saveOccupationAssignment(ocupationAssigment);
    }

    @PutMapping("/occupationassigment/{id}")
    public ResponseEntity<OccupationAssignment> updateOccupationAssignment(@RequestBody OccupationAssignment newOccupationAssignment,
                                                           @PathVariable Integer id)
            throws ParseException {
        return occupationAssignmentService.updateOccupationAssignment(newOccupationAssignment, id);
    }

    @PutMapping("/status/{id}")
    public OccupationAssignment updateOccupationAssignmentStatus(@RequestBody OccupationAssignment ocupationAssigment,
                                                                 @PathVariable Integer id)
            throws ParseException {
        return occupationAssignmentService.updateOccupationAssignmentStatus(id, ocupationAssigment);
    }

    @PutMapping("/updateall/{id}")
    public List<OccupationAssignment>  updateAllCollaboratorAssignments(@RequestBody List<OccupationAssignment> assignments, @PathVariable Integer id) throws ParseException{
        return occupationAssignmentService.updateAllCollaboratorAssignments(assignments, id);
    }
}
