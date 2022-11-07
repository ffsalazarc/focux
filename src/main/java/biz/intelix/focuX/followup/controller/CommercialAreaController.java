package biz.intelix.focuX.followup.controller;


import biz.intelix.focuX.followup.exception.ResourceNotFoundException;
import biz.intelix.focuX.followup.model.CommercialArea;
import biz.intelix.focuX.followup.service.CommercialAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/followup/commercialareas")
@CrossOrigin("*")
public class CommercialAreaController {

    private CommercialAreaService commercialAreaService;

    @Autowired
    public CommercialAreaController(CommercialAreaService commercialAreaService) {
        this.commercialAreaService = commercialAreaService;
    }

    @GetMapping("/all")
    public List<CommercialArea> getAllCommercialAreas() {
        return commercialAreaService.getAllCommercialAreas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommercialArea> findCommercialAreaById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        return commercialAreaService.findCommercialAreaById(id);
    }

    @PostMapping("/save")
    public CommercialArea createCommercialArea(@RequestBody CommercialArea commercialArea)
            throws ParseException {
        return commercialAreaService.saveCommercialArea(commercialArea);
    }

    @PutMapping("/commercialarea/{id}")
    public CommercialArea updateCommercialArea(@RequestBody CommercialArea newCommercialArea, @PathVariable Integer id)
            throws ParseException {
        return commercialAreaService.updateCommercialArea(newCommercialArea, id);
    }

    @PutMapping("/status/{id}")
    public CommercialArea updateCommercialAreaStatus(@RequestBody CommercialArea commercialArea, @PathVariable Integer id)
            throws ParseException {
        return commercialAreaService.updateCommercialAreaStatus(id, commercialArea);
    }
}
