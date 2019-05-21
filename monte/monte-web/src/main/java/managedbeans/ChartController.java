/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import EJB.ProductoFacadeLocal;
import entities.Producto;
import java.io.Serializable;
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




@Named(value = "ChartController")
@ViewScoped
public class ChartController implements Serializable {
    
   @EJB
   private ProductoFacadeLocal productoEJB;
   
   private List<Producto> listado;
   private BarChartModel barra;

    public ChartController() {
        
    }

    @PostConstruct
    public void init() {
        barra = new BarChartModel();
        
        for(int i=0; i<productoEJB.listar().size(); i++){
            ChartSeries series = new BarChartSeries();
            
            series.setLabel(productoEJB.listar().get(i).getTitulo());
            series.set(productoEJB.listar().get(i).getTitulo(), productoEJB.listar().get(i).getDisponible());
            barra.addSeries(series);
        }
        
        barra.setTitle("Productos Disponibles");
        barra.setLegendPosition("NE");
        barra.setAnimate(true);
        
        Axis xAxis = barra.getAxis(AxisType.X);
        xAxis.setLabel("Productos");
        Axis yAxis = barra.getAxis(AxisType.Y);
        yAxis.setLabel("Cantidad Disponible");
        yAxis.setMin(10);
        yAxis.setMax(800);
    }
    public List<Producto> getListado() {
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
        barra.setLegendPosition("NE");
        barra.setAnimate(true);
        
        Axis xAxis = barra.getAxis(AxisType.X);
        xAxis.setLabel("Productos");
        Axis yAxis = barra.getAxis(AxisType.Y);
        yAxis.setLabel("Cantidad Disponible");
        yAxis.setMin(10);
        yAxis.setMax(800);
    }
}
