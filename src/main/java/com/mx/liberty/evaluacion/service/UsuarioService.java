package com.mx.liberty.evaluacion.service;

import java.util.List;

import com.mx.liberty.evaluacion.dto.UsuarioDTO;
import com.mx.liberty.evaluacion.model.Usuario;

public interface UsuarioService {
	
	Usuario save(UsuarioDTO usuario);
	
	List<Usuario> getAll();
	
	boolean update(Usuario usuario);
	
	boolean desactiva(Integer usuarioId);
	
	Usuario getById(Integer usuario);
	
	

}
