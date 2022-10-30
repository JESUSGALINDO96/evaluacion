package com.mx.liberty.evaluacion.dao;

import java.util.List;

import com.mx.liberty.evaluacion.model.Usuario;

public interface UsuarioDAO {
	
	Usuario save(Usuario usuario);
	
	Usuario getById(Integer usuario);
	
	List<Usuario> getAll();

	boolean update (Usuario usuario);
	
//	boolean desactiva(Integer usuarioId);
}
