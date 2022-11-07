package biz.intelix.focuX.followup.model.dto;


/**
 * This object might be provisionary for now until we figure out the best way to send data
 */
public class GraphAnswer {
    // The label used in the graph
    private String label;
    private Integer data;


    public GraphAnswer(String label, Integer data) {
        this.label = label;
        this.data = data;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getData() {
        return this.data;
    }

    public void setData(Integer data) {
        this.data = data;
    }
}
