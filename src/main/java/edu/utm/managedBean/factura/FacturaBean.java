package edu.utm.managedBean.factura;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import edu.utm.bd.domain.Factura;
import edu.utm.service.factura.FacturaService;

@Named
public class FacturaBean {
	@Inject
	FacturaService facturaService;
	
	private List<Factura> facturaList;
	private Factura factura;
	/* metodo que busca todas las facturas*/
	public List<Factura> getFacturaList(){
		if (facturaList == null)
			setFacturaList(facturaService.findAllFacturas());
		System.out.print("getFacturaList");
		return facturaList;
	}
	private void setFacturaList(List<Factura> facturaList) {
		this.facturaList = facturaList;
	}
	/*metodo que busca una factura por Id*/
	public Factura getFacturaId( Factura fac ){
		if (factura == null)
			setFactura(facturaService.findOneFactura(fac));
		System.out.print("getFactura");
		return factura;
	}
	private void setFactura(Factura factura) {
		this.factura = factura;
	}
	/*metodo que busca una factura por fecha*/
	
	
	
	// para editar la tabla
	public void onRowEdit(RowEditEvent event) {
		Factura factura = ((Factura) event.getObject());
		System.out.println("Datos cliente: " + factura.getIdfactura());
		FacesMessage msg = new FacesMessage("Cliente editado", factura.getIdfactura().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRwowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edición cancelada",
				((Factura) event.getObject()).getIdfactura().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		System.out.println("Verifica: " + newValue);
		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Factura modificada", "Antes: " + oldValue + ", Ahora: "+ newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
}
