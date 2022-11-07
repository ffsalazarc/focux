package biz.intelix.focuX.followup.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biz.intelix.focuX.followup.model.Request;
import biz.intelix.focuX.followup.model.dto.PieCharts;
import biz.intelix.focuX.followup.model.dto.ResponseColumnCharts;
import biz.intelix.focuX.followup.model.dto.ColumnCharts;
import biz.intelix.focuX.followup.model.dto.GraphAnswer;
import biz.intelix.focuX.followup.repository.DashboardRepository;
import biz.intelix.focuX.followup.repository.RequestRepository;

@Service
public class DashboardService {

    private final RequestRepository requestRepository;

    @Autowired
    DashboardService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public List<Request> getActiveRequestsLastYear() {
        return requestRepository.getActiveRequestsLastYear();
    }

    public List<GraphAnswer> getCumulativeSumActiveRequests(Date dateInit, Date dateEnd) {                 
        return  requestRepository.cumulativeSumActiveRequests(dateInit, dateEnd);
    }
    
    public List<GraphAnswer> getNumberOfInactiveRequestsByMonth(Date dateInit, Date dateEnd) {                 
        return  requestRepository.numberOfInactiveRequestsByMonth(dateInit, dateEnd);
    }

    public PieCharts getRequestsByDepartment() {
        return requestRepository.requestsByDepartment();
    }

    public PieCharts getRequestsByArea() {
        return requestRepository.requestsByArea();
    }

    public ResponseColumnCharts  getRequestsByBusiness() {
        return requestRepository.requestsByBusiness();
    }

}
