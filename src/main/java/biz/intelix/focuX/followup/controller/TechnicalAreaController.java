package biz.intelix.focuX.followup.controller;


import biz.intelix.focuX.followup.exception.ResourceNotFoundException;
import biz.intelix.focuX.followup.model.TechnicalArea;
import biz.intelix.focuX.followup.service.TechnicalAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/followup/technicalareas")
@CrossOrigin("*")
public class TechnicalAreaController {

    private TechnicalAreaService technicalAreaService;

    @Autowired
    public TechnicalAreaController(TechnicalAreaService technicalAreaService) {
        this.technicalAreaService = technicalAreaService;
    }

    @GetMapping("/all")
    public List<TechnicalArea> getAllTechnicalAreas() {
        return technicalAreaService.getAllTechnicalAreas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechnicalArea> findTechnicalAreaById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        return technicalAreaService.findTechnicalAreaById(id);
    }

    @PostMapping("/save")
    public TechnicalArea createTechnicalArea(@RequestBody TechnicalArea technicalArea)
            throws ParseException {
        return technicalAreaService.saveTechnicalArea(technicalArea);
    }

    @PutMapping("/technicalarea/{id}")
    public TechnicalArea updateTechnicalArea(@RequestBody TechnicalArea newTechnicalArea, @PathVariable Integer id)
            throws ParseException {
        return technicalAreaService.updateTechnicalArea(newTechnicalArea, id);
    }

    @PutMapping("/status/{id}")
    public TechnicalArea updateTechnicalAreaStatus(@RequestBody TechnicalArea technicalArea, @PathVariable Integer id)
            throws ParseException {
        return technicalAreaService.updateTechnicalAreaStatus(id, technicalArea);
    }
}
