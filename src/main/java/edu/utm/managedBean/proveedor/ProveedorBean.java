package edu.utm.managedBean.proveedor;

import java.util.List;


import javax.inject.Inject;
import javax.inject.Named;


import edu.utm.bd.domain.Proveedor;
import edu.utm.service.proveedor.ProveedorService;

@Named
public class ProveedorBean {
	@Inject
	ProveedorService proveedorService;
	private List<Proveedor> proveedorList;

	public List<Proveedor> getProveedorList() {
		if (proveedorList == null)
			setProveedorList(proveedorService.findAllProveedor());

		return proveedorList;
	}

	private void setProveedorList(List<Proveedor> proveedorList) {
		this.proveedorList = proveedorList;
	}

}
