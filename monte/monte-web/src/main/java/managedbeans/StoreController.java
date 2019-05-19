/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import EJB.UserFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.ChartPoint;
import models.ChartSeriesData;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.*;

@Named(value = "StoreController")
@SessionScoped
public class StoreController implements Serializable {
    /* Grafica de Barras */
    private BarChartModel barModel;
    
    /* Data de grafica de barras */
    private List<ChartSeriesData> SeriesBarChart = null; 
    private final String Title = "Reporte de ventas";        
    private final String AxisX = "X";    
    private final String AxisY = "Y";
    private Integer Min = 0; 
    private Integer Max = 2000; 
    
    private String Name = "Tienda";
 
    
    public BarChartModel bar() {
        return barModel;
    }
    
    @PostConstruct
    public void init() {
        SeriesBarChart = new ArrayList(); 
        ChartSeriesData data = new ChartSeriesData();
        data.setTitle("Datos 1");
        List lista = new ArrayList(); 
        ChartPoint punto = new ChartPoint("2010", 5);
        lista.add(punto);
        punto = new ChartPoint("2011", 20);
        lista.add(punto);
        data.setData(lista);
        SeriesBarChart.add(data);
        createBarModels();
    }
 
    private void createBarModels() {
        barModel = initBarModel();
 
        barModel.setTitle(Title);
        barModel.setLegendPosition("ne");
 
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel(AxisX);
 
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel(AxisY);
        yAxis.setMin(0);
        yAxis.setMax(200);
    }
    
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
        Max = SeriesBarChart.get(0).getMax();        
        Min = SeriesBarChart.get(0).getMin();
        for (ChartSeriesData serie : SeriesBarChart) {
            model.addSeries(serie.getChartSeries());
            if (serie.getMin() < Min) Min = serie.getMin();
            if (serie.getMax() > Max) Max = serie.getMax();
        }
        return model;
    }
    
    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());
 
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
}
