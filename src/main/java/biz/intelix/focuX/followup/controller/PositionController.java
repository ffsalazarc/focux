package biz.intelix.focuX.followup.controller;

import biz.intelix.focuX.followup.exception.ResourceNotFoundException;
import biz.intelix.focuX.followup.model.Phone;
import biz.intelix.focuX.followup.model.Position;
import biz.intelix.focuX.followup.service.PhoneService;
import biz.intelix.focuX.followup.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/followup/position")
@CrossOrigin("*")
public class PositionController {

    private PositionService positionService;

    @Autowired
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping("/all")
    public List<Position> getAllPosition() {
        return positionService.getAllPosition();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Position> findPositionById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        return positionService.findPositionById(id);
    }

    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    @PostMapping("/save")
    public Position createPosition(@RequestBody Position position)
            throws ParseException {
        return positionService.savePosition(position);
    }

    @PutMapping("/position/{id}")
    public Position updatePosition(@RequestBody Position newPosition, @PathVariable(value = "id") Integer id) throws ParseException {
        return positionService.updatePosition(newPosition, id);
    }

    @PutMapping("/status/{id}")
    public Position updatePositionStatus(@RequestBody Position position, @PathVariable(value = "id") Integer id) throws ParseException {
        return positionService.updatePositionStatus(id, position);
    }
}
