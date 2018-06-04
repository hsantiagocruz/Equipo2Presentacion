package edu.utm.managedBean.venta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import edu.utm.bd.domain.Venta;
import edu.utm.service.venta.VentaService;

@Named
@ViewScoped
public class VentaBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	VentaService ventaService;
	
	private List<Venta> listaVenta;
	private Venta venta;
	
	@PostConstruct
	public void init(){
		if(listaVenta == null)
			listaVenta = new ArrayList<Venta>();
		if(venta == null)
			venta = new Venta();
		//se invoca el método del servicio para obtener lista
		setlistaVenta(ventaService.findAllVentas());
	}
	public void registrar(){
		System.out.println("Registro de una venta !!!");
		//invocar el servicio
		ventaService.insertVenta(getVenta());
		//limpia los valores del objeto
		setVenta(new Venta());
		//se actualiza los valores de la tabla
		setlistaVenta(ventaService.findAllVentas());
		getlistaVenta();
	}
	//setters y gtters
	public Venta getVenta(){
		return venta;
	}
	public void setVenta(Venta venta){
		this.venta = venta;
	}
	public List<Venta> getlistaVenta(){
		return listaVenta;
	}
	public void setlistaVenta(List<Venta> listaVenta){
		this.listaVenta = listaVenta;
	}
}
