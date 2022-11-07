package biz.intelix.focuX.followup.controller;

import biz.intelix.focuX.followup.exception.ResourceNotFoundException;
import biz.intelix.focuX.followup.model.Indicator;
import biz.intelix.focuX.followup.service.IndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/followup/indicator")
@CrossOrigin("*")
public class IndicatorController {

    private IndicatorService indicatorService;
    @Autowired
    public IndicatorController(IndicatorService indicatorService) {
        this.indicatorService = indicatorService;
    }

    @GetMapping("/all")
    public List<Indicator> getAllIndicator() {
        return indicatorService.getAllIndicator();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Indicator> findIndicatorById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        return indicatorService.findIndicatorById(id);
    }

    @PostMapping("/save")
    public Indicator createIndicator(@RequestBody Indicator indicator)
            throws ParseException {
        return indicatorService.saveIndicator(indicator);
    }

    @PutMapping("/indicator/{id}")
    public Indicator updateIndicator(@RequestBody Indicator newIndicator, @PathVariable(value = "id") Integer id)
            throws ParseException {
        return indicatorService.updateIndicator(newIndicator, id);
    }

    @PutMapping("/status/{id}")
    public Indicator updateIndicatorStatus(@RequestBody Indicator indicator, @PathVariable(value = "id") Integer id)
            throws ParseException {
        return indicatorService.updateIndicatorStatus(id, indicator);
    }
}