/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import EJB.CompraFacadeLocal;
import EJB.ClienteFacadeLocal;
import EJB.TiendaFacadeLocal;
import EJB.ProductoFacadeLocal;
import entities.Cliente;
import entities.Compra;
import entities.Producto;
import entities.Tienda;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.ChartPoint;
import models.ChartSeriesData;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.*;
import utilidades.RandomUtilidad;

@Named(value = "StoreController")
@ViewScoped
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
    
    private Producto producto;
    private Tienda tienda;
    private List<Producto> productCol;
    private List<Compra> compraCol;
    @EJB
    private TiendaFacadeLocal TiendaEJB;
    @EJB
    private ProductoFacadeLocal ProductoEJB;
    @EJB
    private ClienteFacadeLocal ClienteEJB;
    @EJB
    private CompraFacadeLocal CompraEJB;
     
    public BarChartModel bar() {
        return barModel;
    }
    
    @PostConstruct
    public void init() {
        productCol = new ArrayList<Producto>();
        productCol = new ArrayList<Producto>();
        producto = new Producto();
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
 
    public void guardaProducto(){
        try {
            
            Producto prueba = new Producto();
            prueba.setDescripcion(producto.getDescripcion());
            prueba.setPublicado(new Date());
            prueba.setTitulo(producto.getTitulo());
            prueba.setDestacado(producto.getDestacado());
            prueba.setDisponible(producto.getDisponible());
            prueba.setStoreIdProducto(producto.getStoreIdProducto());
            
            FacesContext context = FacesContext.getCurrentInstance();
            Cliente c = (Cliente) context.getExternalContext().getSessionMap().get("usuario");
            tienda = c.getStoreCollection();
            Collection<Producto> ct =  tienda.getProductCol();
            ct.add(prueba);
            tienda.setProductCol(ct);
            TiendaEJB.edit(tienda);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso","Se a guardaro el producto"));

            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso","Error"));
        }
    }

    
    
    public void cargarlistado() {
        FacesContext context = FacesContext.getCurrentInstance();
        Cliente c = (Cliente) context.getExternalContext().getSessionMap().get("usuario");
        tienda = c.getStoreCollection();
        productCol = new ArrayList<Producto>(tienda.getProductCol());
    }
    
    public void cargarproducto() {
        producto = ProductoEJB.find(producto.getId());
    }
    public void cargarCompras() {
        FacesContext context = FacesContext.getCurrentInstance();
        Cliente c = (Cliente) context.getExternalContext().getSessionMap().get("usuario");
        compraCol = new ArrayList<Compra>(c.getCompraCol());
    }
    
    public void comprar() {
        FacesContext context = FacesContext.getCurrentInstance();
        Cliente c = (Cliente) context.getExternalContext().getSessionMap().get("usuario");
        if (c != null) {
            Collection<Compra> co = c.getCompraCol();
            Compra compra = new Compra();
            compra.setProductoidCompra(producto);
            compra.setUseridCompra(c);
            co.add(compra);
            c.setCompraCol(co);
            ClienteEJB.edit(c);
        } else {
            try {
                context.getExternalContext().redirect("/login?faces-redirect=true");
            } catch (IOException ex) {
                Logger.getLogger(StoreController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<Compra> getCompraCol() {
        return compraCol;
    }

    public void setCompraCol(List<Compra> compraCol) {
        this.compraCol = compraCol;
    }
    
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    public List<Producto> getProductCol() {
        return productCol;
    }

    public void setProductCol(List<Producto> productCol) {
        this.productCol = productCol;
    }

    
}
