/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.List;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author Mauro
 */
public class ChartSeriesData {
    private String title = "";
    private List<ChartPoint> data = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ChartPoint> getData() {
        return data;
    }

    public void setData(List<ChartPoint> data) {
        this.data = data;
    }
    
    public Integer getMin() {
        Integer nReturn = 0;
        for (ChartPoint point : data) {
            if (nReturn > point.getValue()){
                nReturn = point.getValue();
            }
        }
        return nReturn;
    }
    
    public Integer getMax() {
        Integer nReturn = 0;
        for (ChartPoint point : data) {
            if (nReturn < point.getValue()){
                nReturn = point.getValue();
            }
        }
        return nReturn;
    }
    
    public ChartSeries getChartSeries(){
        ChartSeries oReturn = new ChartSeries();
        oReturn.setLabel(this.title);
        for (ChartPoint point : data) {
            oReturn.set(point.getName(), point.getValue());
        }
        return oReturn;
    }
    
}
