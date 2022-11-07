package biz.intelix.focuX.followup.model.dto;

public class ResponseColumnCharts {
    
    private String[] categories;
    private ColumnCharts[] series;



    public ResponseColumnCharts(String[] categories, ColumnCharts[] series) {
        this.categories = categories;
        this.series = series;
    }

    public String[] getCategories() {
        return this.categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public ColumnCharts[] getSeries() {
        return this.series;
    }

    public void setSeries(ColumnCharts[] series) {
        this.series = series;
    }
}
