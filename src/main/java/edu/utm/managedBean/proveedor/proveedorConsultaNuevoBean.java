package edu.utm.managedBean.proveedor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import edu.utm.bd.domain.Proveedor;
import edu.utm.service.proveedor.ProveedorService;

@Named
@ViewScoped
public class proveedorConsultaNuevoBean implements Serializable {

	private static final long serialVersionUID = 6472377493921731094L;
	@Inject
	ProveedorService proveedorService;
	private List<Proveedor> listaProveedor;
	
	private Proveedor proveedor;
	
	@PostConstruct
	public void init(){
		System.out.println("123wrasadasd");
		if(listaProveedor == null)
			listaProveedor = new ArrayList<Proveedor>();
		if(proveedor == null)
			proveedor = new Proveedor();
		//se invoca el método del servicio para obtener lista
		setlistaProveedor(proveedorService.findAllProveedor());
	}
	
	//metodo que registra nuevo cliente
	public void registrar(){
		System.out.println("Proveedor !!!");
		//invocar el servicio
		Proveedor proveedor=getProveedor();
		proveedorService.insertProveedor(proveedor);
		//limpia los valores del objeto
		setProveedor(new Proveedor());
		//se actualiza los valores de la tabla
		setlistaProveedor(proveedorService.findAllProveedor());
		getlistaProveedor();
	}
	
	public void actualizar(){
		proveedorService.updateProveedor(proveedor);
		//se actualiza los valores de la tabla
		setlistaProveedor(proveedorService.findAllProveedor());
		getlistaProveedor();
	}
	
	public void eliminar(){
		proveedorService.deleteOneProveedor(proveedor);
		//se actualiza los valores de la tabla
		setlistaProveedor(proveedorService.findAllProveedor());
		getlistaProveedor();
	}
	//setters y gtters
	public Proveedor getProveedor(){
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor){
		
		this.proveedor = proveedor;
	}
	public List<Proveedor> getlistaProveedor(){
		return listaProveedor;
	}
	public void setlistaProveedor(List<Proveedor> listaProveedor){
		this.listaProveedor = listaProveedor;
	}
}
