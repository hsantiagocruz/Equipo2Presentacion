package edu.utm.managedBean.cliente;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import edu.utm.bd.domain.Cliente;
import edu.utm.service.cliente.ClienteService;

@Named
public class ClienteBean {
	@Inject
	ClienteService clienteService;
	private List<Cliente> clienteList;

	public List<Cliente> getClienteList() {
		if (clienteList == null)
			setClienteList(clienteService.findAllClientes());

		return clienteList;
	}

	private void setClienteList(List<Cliente> clienteList) {
		this.clienteList = clienteList;
	}

	public void onRowEdit(RowEditEvent event) {
		Cliente cliente = ((Cliente) event.getObject());
		System.out.println("Datos cliente: " + cliente.getIdcliente());
		clienteService.updateCliente(cliente);

		FacesMessage msg = new FacesMessage("Cliente editado", cliente.getIdcliente()
				.toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		Cliente cliente = ((Cliente) event.getObject());
		System.out.println("Datos cliente: " + cliente.getIdcliente());
		clienteService.deleteOneCliente(cliente);
		FacesMessage msg = new FacesMessage("Cliente eliminado",
				((Cliente) event.getObject()).getIdcliente().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		System.out.println("Verifica: " + newValue);

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente modificado", "Antes: " + oldValue + ", Ahora: "+ newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
}
