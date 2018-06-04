package edu.utm.managedBean.usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import edu.utm.bd.domain.Usuario;
import edu.utm.service.usuario.UsuarioService;

@Named
@ViewScoped
public class usuarioConsultaNuevoBean implements Serializable{

		private static final long serialVersionUID = 6472377493921731094L;
		@Inject
		UsuarioService usuarioService;
		private List<Usuario> listaUsuario;
		
		private Usuario usuario;
		
		@PostConstruct
		public void init(){
			if(listaUsuario == null)
				listaUsuario = new ArrayList<Usuario>();
			if(usuario == null)
				usuario = new Usuario();
			//se invoca el método del servicio para obtener lista
			setlistaUsuario(usuarioService.findAllUsuarios());
		}
		
		//metodo que registra nuevo cliente
		public void registrar(){
			System.out.println("Usuario con password !!!");
			//invocar el servicio
			Usuario usuario=getUsuario();
			usuario.setUsuadmin(false);
			System.out.println("hehee");
			usuarioService.insertUsuario(usuario);
			//limpia los valores del objeto
			setUsuario(new Usuario());
			//se actualiza los valores de la tabla
			setlistaUsuario(usuarioService.findAllUsuarios());
			getlistaUsuario();
		}
		public void eliminar(){
			usuarioService.deleteOneUsuario(usuario);
			//se actualiza los valores de la tabla
			setlistaUsuario(usuarioService.findAllUsuarios());
			getlistaUsuario();
		}
		
		public void actualizar(){
			usuarioService.updateUsuario(usuario);
			//se actualiza los valores de la tabla
			setlistaUsuario(usuarioService.findAllUsuarios());
			getlistaUsuario();
		}
		//setters y gtters
		public Usuario getUsuario(){
			return usuario;
		}
		public void setUsuario(Usuario usuario){
			
			this.usuario = usuario;
		}
		public List<Usuario> getlistaUsuario(){
			return listaUsuario;
		}
		public void setlistaUsuario(List<Usuario> listaUsuario){
			this.listaUsuario = listaUsuario;
		}
}
