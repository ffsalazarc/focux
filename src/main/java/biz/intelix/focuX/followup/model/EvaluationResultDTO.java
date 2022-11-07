package biz.intelix.focuX.followup.model;

import java.util.Map;

public class EvaluationResultDTO {
    private Double totalResult;
    private Map<Integer, Double> resultPerMonth;
    private Map<String, Map<Integer, Double>> objectives;
    private Integer period;
    private Integer collaboratorId;
    private Integer year;
    

    public EvaluationResultDTO(Double totalResult, Map<Integer,Double> resultPerMonth, Map<String,Map<Integer,Double>> objectives, Integer period, Integer collaboratorId, Integer year) {
        this.totalResult = totalResult;
        this.resultPerMonth = resultPerMonth;
        this.objectives = objectives;
        this.period = period;
        this.collaboratorId = collaboratorId;
        this.year = year;
    }

    public Integer getCollaboratorId() {
        return this.collaboratorId;
    }

    public void setCollaboratorId(Integer collaboratorId) {
        this.collaboratorId = collaboratorId;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getTotalResult() {
        return this.totalResult;
    }

    public void setTotalResult(Double totalResult) {
        this.totalResult = totalResult;
    }

    public Map<Integer,Double> getResultPerMonth() {
        return this.resultPerMonth;
    }

    public void setResultPerMonth(Map<Integer,Double> resultPerMonth) {
        this.resultPerMonth = resultPerMonth;
    }

    public Map<String,Map<Integer,Double>> getObjectives() {
        return this.objectives;
    }

    public void setObjectives(Map<String,Map<Integer,Double>> objectives) {
        this.objectives = objectives;
    }

    public Integer getPeriod() {
        return this.period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

}
