package edu.utm.managedBean.compra;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import edu.utm.bd.domain.Cliente;
import edu.utm.bd.domain.Compra;
import edu.utm.service.compra.CompraService;

@Named
public class CompraBean {
	@Inject
	CompraService compraService;
	private List<Compra> compraList;
	private Compra compra;
	
	/*metodo para obtener la lista de compras*/
	public List<Compra> getCompraList(){
		if (compraList == null)
			setCompraList(compraService.findAllCompras());
		System.out.print("getCompraList");
		return compraList;
	}
	private void setCompraList(List<Compra> compraList) {
		this.compraList = compraList;
	}
	
	/*metodo para la busqueda de una compra por Id*/
	private void buscaCompraId() {
		Compra compra = getCompra();
		setCompra(compraService.findOneCompra(compra));
		System.out.println("Compra encontrado con id" + compra.getIdcompra() + "con un total de " + compra.getComtotal());
	}
	private void setCompra(Compra compra2) {
		this.compra = compra2;
	}
	
	public Compra getCompra(){
		return compra;
	}
	
	
}
