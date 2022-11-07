package biz.intelix.focuX.followup.controller;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import biz.intelix.focuX.followup.model.Request;
import biz.intelix.focuX.followup.model.dto.PieCharts;
import biz.intelix.focuX.followup.model.dto.ResponseColumnCharts;
import biz.intelix.focuX.followup.model.dto.ColumnCharts;
import biz.intelix.focuX.followup.model.dto.GraphAnswer;
import biz.intelix.focuX.followup.service.DashboardService;


@RestController
@RequestMapping("/api/v1/followup/dashboard")
@CrossOrigin("*")
public class DashboardController {
        
    private final DashboardService dashboardService;

    @Autowired
    DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }
    
    @GetMapping("/activerequestsperiod")
    public List<Request> getActiveRequestsLastYear() {
        return dashboardService.getActiveRequestsLastYear();
    }

    @GetMapping("/cumulativeactiverequests")
    public List<GraphAnswer> getCumulativeActiveRequests(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateInit, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateEnd) {
        return dashboardService.getCumulativeSumActiveRequests(dateInit, dateEnd);
    }

    @GetMapping("/finishedmonthlyrequests")
    public List<GraphAnswer> finishedMonthlyRequests(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateInit, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateEnd) {
        return dashboardService.getNumberOfInactiveRequestsByMonth(dateInit, dateEnd);
    }

    @GetMapping("/requestsbydepartment")
    public PieCharts requestsByDepartment() {
        return dashboardService.getRequestsByDepartment();
    }

    @GetMapping("/requestsbyarea")
    public PieCharts requestsByArea() {
        return dashboardService.getRequestsByArea();
    }

    @GetMapping("/requestsbybusiness")
    public ResponseColumnCharts requestsByBusiness() {
        return dashboardService.getRequestsByBusiness();
    }
}
