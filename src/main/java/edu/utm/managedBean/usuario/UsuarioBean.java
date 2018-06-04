package edu.utm.managedBean.usuario;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import edu.utm.bd.domain.Usuario;
import edu.utm.service.usuario.UsuarioService;

@Named
public class UsuarioBean {
	@Inject
	UsuarioService usuarioService;
	private List<Usuario> usuarioList;
	
	public List<Usuario> getUsuarioList(){
		if(usuarioList == null)
			setUsuarioList(usuarioService.findAllUsuarios());
		return usuarioList;
	}
	
	private void setUsuarioList(List<Usuario> usuarioList){
		this.usuarioList = usuarioList;
	}
	
	public void onRowEdit(RowEditEvent event) {
		Usuario usuario = ((Usuario) event.getObject());
		System.out.println("Datos usuario: " + usuario.getIdusuario());
		usuarioService.updateUsuario(usuario);
		
		FacesMessage msg = new FacesMessage("Usuario editado", usuario.getIdusuario().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void onRwowCancel(RowEditEvent event){
		FacesMessage msg = new FacesMessage("Edición cancelada",((Usuario) event.getObject()).getIdusuario().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void onCellEdit(CellEditEvent event){
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		
		System.out.println("Verifica: " + newValue);
		if(newValue != null && !newValue.equals(oldValue)){
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Usuario modificado", "Antes: " + oldValue + ", Ahora "+ newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
}
