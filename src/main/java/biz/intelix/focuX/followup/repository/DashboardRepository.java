package biz.intelix.focuX.followup.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Tuple;

import org.springframework.stereotype.Repository;

import biz.intelix.focuX.followup.model.Request;
import biz.intelix.focuX.followup.model.dto.PieCharts;
import biz.intelix.focuX.followup.model.dto.ResponseColumnCharts;
import biz.intelix.focuX.followup.model.dto.ColumnCharts;
import biz.intelix.focuX.followup.model.dto.GraphAnswer;
public interface DashboardRepository {
    List<Request> getActiveRequestsLastYear();

    List<GraphAnswer> cumulativeSumActiveRequests(Date dateInit, Date dateEnd);
    List<GraphAnswer> numberOfInactiveRequestsByMonth(Date dateInit, Date dateEnd);
    String requestStatusByBusiness();
    PieCharts requestsByDepartment();
    PieCharts requestsByArea();
    ResponseColumnCharts  requestsByBusiness();

}
