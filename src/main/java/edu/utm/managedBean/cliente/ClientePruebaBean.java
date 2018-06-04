package edu.utm.managedBean.cliente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import edu.utm.bd.domain.Cliente;
import edu.utm.service.cliente.ClienteService;

@Named
@ViewScoped
public class ClientePruebaBean implements Serializable{

		private static final long serialVersionUID = 6472377493921731094L;
		@Inject
		ClienteService clienteService;
		private List<Cliente> listaCliente;
		
		private Cliente cliente;
		
		@PostConstruct
		public void init(){
			if(listaCliente == null)
				listaCliente = new ArrayList<Cliente>();
			if(cliente == null)
				cliente = new Cliente();
			//se invoca el método del servicio para obtener lista
			setlistaCliente(clienteService.findAllClientes());
		}
		
		//metodo que registra nuevo cliente
		public void registrar(){
			System.out.println("Cliente con dirección !!!");
			//invocar el servicio
			clienteService.insertCliente(getCliente());
			//limpia los valores del objeto
			setCliente(new Cliente());
			//se actualiza los valores de la tabla
			setlistaCliente(clienteService.findAllClientes());
			getlistaCliente();
		}
		//setters y gtters
		public Cliente getCliente(){
			return cliente;
		}
		public void setCliente(Cliente cliente){
			this.cliente = cliente;
		}
		public List<Cliente> getlistaCliente(){
			return listaCliente;
		}
		public void setlistaCliente(List<Cliente> listaCliente){
			this.listaCliente = listaCliente;
		}
}
