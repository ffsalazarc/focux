package biz.intelix.focuX.followup.model.dto;

import java.util.Map;

import javax.persistence.Tuple;

/**
 * This object might be provisionary for now until we figure out the best way to send data
 */
public class ColumnCharts {
    // The label used in the graph
    private String name;
    private Integer[] data;



    public ColumnCharts(String name, Integer[] data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer[] getData() {
        return this.data;
    }

    public void setData(Integer[] data) {
        this.data = data;
    }

}
