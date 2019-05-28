/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import EJB.ProductoFacadeLocal;
import entities.Producto;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LegendPlacement;




@Named(value = "ChartController")
@ViewScoped
public class ChartController implements Serializable {
    
   @EJB
   private ProductoFacadeLocal productoEJB;
   
   private Collection<Producto> listado;
   private BarChartModel barra;
   private BarChartModel barraVis;

    public ChartController() {
        
    }

    @PostConstruct
    public void init() {
        barra = new BarChartModel();
        
        for(int i=0; i<productoEJB.listar().size(); i++){
            ChartSeries series = new BarChartSeries();
            
            series.setLabel(productoEJB.listar().iterator().next().getTitulo());
            series.set(productoEJB.listar().iterator().next().getTitulo(), productoEJB.listar().iterator().next().getDisponible());
            barra.addSeries(series);
        }
        
        barra.setTitle("Productos Disponibles");
        barra.setLegendPosition("ne");
        barra.setAnimate(true);
        
        Axis xAxis = barra.getAxis(AxisType.X);
        xAxis.setLabel("Productos");
        Axis yAxis = barra.getAxis(AxisType.Y);
        yAxis.setLabel("Cantidad Disponible");
        yAxis.setMin(0);
        yAxis.setMax(10);
    }
    public Collection<Producto> getListado() {
        return listado;
    }

    public void setListado(List<Producto> listado) {
        this.listado = listado;
    }

    public BarChartModel getBarra() {
        return barra;
    }

    public void setBarra(BarChartModel barra) {
        this.barra = barra;
    }

    public BarChartModel getBarraVis() {
        return barraVis;
    }

    public void setBarraVis(BarChartModel barraVis) {
        this.barraVis = barraVis;
    }
    
    
    
    public void listar(){
        listado = productoEJB.listar();
        this.graficar();
    }
    
    public void graficar(){
        barra = new BarChartModel();
        
        ChartSeries series = new BarChartSeries();
        for(int i=0; i<productoEJB.listar().size(); i++){    
            series.setLabel(productoEJB.listar().get(i).getTitulo());
            series.set(productoEJB.listar().get(i).getTitulo(), productoEJB.listar().get(i).getDisponible());
        }
        barra.addSeries(series);
        
        barra.setTitle("Productos Disponibles");
        barra.setLegendPosition("ne");
        barra.setAnimate(true);
        
        Axis xAxis = barra.getAxis(AxisType.X);
        xAxis.setLabel("Productos");
        Axis yAxis = barra.getAxis(AxisType.Y);
        yAxis.setLabel("Cantidad Disponible");
        yAxis.setMin(0);
        yAxis.setMax(10);
    }
    
    public void graficarVisitas(){
        barraVis = new BarChartModel();
        
        ChartSeries series = new BarChartSeries();
        for(int i=0; i<productoEJB.visitas().size(); i++){    
            series.setLabel(productoEJB.visitas().get(i).getProductoidVisita().getTitulo());
            series.set(productoEJB.visitas().get(i).getProductoidVisita().getTitulo(), productoEJB.visitas().get(i).getProductoidVisita().getPrecio());
        }
        barraVis.addSeries(series);
        
        barraVis.setTitle("Visitas por Productos");
        barraVis.setLegendPosition("ne");
        barraVis.setAnimate(true);
        
        Axis xAxis = barraVis.getAxis(AxisType.X);
        xAxis.setLabel("Productos");
        Axis yAxis = barraVis.getAxis(AxisType.Y);
        yAxis.setLabel("Visitas");
        yAxis.setMin(0);
        yAxis.setMax(10);
    }
}
