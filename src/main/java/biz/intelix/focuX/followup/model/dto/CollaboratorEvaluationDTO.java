package biz.intelix.focuX.followup.model.dto;

import java.util.List;

import biz.intelix.focuX.followup.model.EvaluationPerformance;

public class CollaboratorEvaluationDTO {
    Integer id;
    String name;
    String lastName;
    String client;
    Integer isEvaluated;
    String position;
    List<EvaluationPerformance> evaluations;


    public CollaboratorEvaluationDTO(Integer id, String name, String lastName, String client, Integer isEvaluated, String position, List<EvaluationPerformance> evaluations) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.client = client;
        this.isEvaluated = isEvaluated;
        this.position = position;
        this.evaluations = evaluations;
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getClient() {
        return this.client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Integer getIsEvaluated() {
        return this.isEvaluated;
    }

    public void setIsEvaluated(Integer isEvaluated) {
        this.isEvaluated = isEvaluated;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<EvaluationPerformance> getEvaluations() {
        return this.evaluations;
    }

    public void setEvaluations(List<EvaluationPerformance> evaluations) {
        this.evaluations = evaluations;
    }

}
