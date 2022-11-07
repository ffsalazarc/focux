package biz.intelix.focuX.followup.model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "EVALUATION_TEMPLATE_NAME", schema = "FOLLOWUP")
public class EvaluationTemplateName {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;
    
    @OneToMany(mappedBy =  "evaluationTemplateName", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<EvaluationTemplate> templates;

    @Column(name = "is_active")
    private Integer isActive;

    public EvaluationTemplateName() {
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

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<EvaluationTemplate> getTemplates() {
        return this.templates;
    }

    public void setTemplates(List<EvaluationTemplate> templates) {
        this.templates = templates;
    }

    public void addTemplate(EvaluationTemplate template) {
        templates.add(template);
        template.setEvaluationTemplateName(this);
    }

    public void removeEvaluationTemplate(EvaluationTemplate template) {
        templates.remove(template);
        template.setEvaluationTemplateName(null);
    }

    public void addTemplates(List<EvaluationTemplate> templates) {
        for (EvaluationTemplate template : templates) {
            this.templates.add(template);
            template.setEvaluationTemplateName(this);
        }
    }

    // No realmente elegante. Toma O(n^2)
    public void removeTemplates(List<EvaluationTemplate> templates) {
        for (EvaluationTemplate template : templates) {
            this.templates.remove(template);
            template.setEvaluationTemplateName(null);
        }
    }

    public Integer getIsActive() {
        return this.isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", code='" + getCode() + "'" +
            ", templates='" + getTemplates() + "'" +
            "}";
    }

}
