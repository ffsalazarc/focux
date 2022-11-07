package biz.intelix.focuX.followup.model.dto;


/**
 * This object might be provisionary for now until we figure out the best way to send data
 */
public class PieCharts {
    // The label used in the graph
    private long totaldata;
    private double[] series;
    private String[] labels;



    public PieCharts(long totaldata, double[] series, String[] labels) {
        this.totaldata = totaldata;
        this.series = series;
        this.labels = labels;
    }

    public long getTotaldata() {
        return this.totaldata;
    }

    public void setTotaldata(long totaldata) {
        this.totaldata = totaldata;
    }

    public double[] getSeries() {
        return this.series;
    }

    public void setSeries(double[] series) {
        this.series = series;
    }

    public String[] getLabels() {
        return this.labels;
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
    }

}
