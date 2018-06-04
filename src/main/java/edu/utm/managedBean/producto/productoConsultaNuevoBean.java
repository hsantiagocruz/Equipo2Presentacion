package edu.utm.managedBean.producto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import edu.utm.bd.domain.Producto;
import edu.utm.service.producto.ProductoService;

@Named
@ViewScoped
public class productoConsultaNuevoBean implements Serializable{

		private static final long serialVersionUID = 6472377493921731094L;
		@Inject
		ProductoService productoService;
		private List<Producto> listaProducto;
		
		private Producto producto;
		
		@PostConstruct
		public void init(){
			if(listaProducto == null)
				listaProducto = new ArrayList<Producto>();
			if(producto == null)
				producto = new Producto();
			//se invoca el método del servicio para obtener lista
			setlistaProducto(productoService.findAllProductos());
		}
		
		
		public void registrar(){
			System.out.println("Producto nuevo!!!");
			//invocar el servicio
			Producto producto= getProducto();
			productoService.insertProducto(producto);
			//limpia los valores del objeto
			setProducto(new Producto());
			//se actualiza los valores de la tabla
			setlistaProducto(productoService.findAllProductos());
			getlistaProducto();
		}
		
		public void eliminar(){
			productoService.deleteProducto(producto);
			//se actualiza los valores de la tabla
			setlistaProducto(productoService.findAllProductos());
			getlistaProducto();
		}
		
		public void actualizar(){
			productoService.updateProducto(producto);
			//se actualiza los valores de la tabla
			setlistaProducto(productoService.findAllProductos());
			getlistaProducto();
		}
		//setters y gtters
		public Producto getProducto(){
			return producto;
		}
		public void setProducto(Producto producto){
			this.producto = producto;
		}
		public List<Producto> getlistaProducto(){
			return listaProducto;
		}
		public void setlistaProducto(List<Producto> listaProducto){
			this.listaProducto = listaProducto;
		}
}
